package com.github.WatermanMC.SimplyLibrary;

import com.github.WatermanMC.SimplyLibrary.api.SimplyCommandCompleterBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

class SimplyCommandCompleterBuilderImpl implements SimplyCommandCompleterBuilder {
    private final String commandExecutor;
    private final Map<Integer, List<String>> suggestions = new HashMap<>();
    private static Field commandMapField;

    static {
        try {
            commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
        } catch (Exception ignored) {
        }
    }

    SimplyCommandCompleterBuilderImpl(@NotNull String executor) {
        this.commandExecutor = executor;
    }

    @Override
    public SimplyCommandCompleterBuilderImpl arg(int index, String... options) {
        suggestions.put(index, Arrays.asList(options));
        return this;
    }

    @Override
    public void build() {
        if (!Bukkit.isPrimaryThread()) {
            throw new ConcurrentModificationException("Attempted to build a command completer using SimplyCommandCompleterBuilder asynchronously! " +
                    "This is NOT a bug on SimplyLibrary side! Read the javadoc: " +
                    "'build()' must be called on the main thread!");
        }

        try {
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            Command cmd = commandMap.getCommand(commandExecutor);
            if (cmd == null) return;

            commandMap.register(cmd.getLabel(), new Command(cmd.getName(), cmd.getDescription(), cmd.getUsage(), cmd.getAliases()) {

                @Override
                public boolean execute(@NotNull CommandSender sender,
                                       @NotNull String label,
                                       @NotNull String[] args) {
                    return cmd.execute(sender, label, args);
                }

                @NotNull
                @Override
                public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                    int index = args.length - 1;
                    List<String> options = suggestions.get(index);

                    if (options == null) {
                        return super.tabComplete(sender, alias, args);
                    }

                    String currentArg = args[index].toLowerCase();
                    return options.stream()
                            .filter(s -> s.toLowerCase().startsWith(currentArg))
                            .sorted(String.CASE_INSENSITIVE_ORDER)
                            .toList();
                }
            });

        } catch (Exception e) {
            Bukkit.getLogger().severe("SimplyLibrary: Failed to inject tab completer for command '" + commandExecutor + "'!");
        }
    }
}