package com.github.WatermanMC.SimplyLibrary;

import com.github.WatermanMC.SimplyLibrary.api.CommandExecutor;
import com.github.WatermanMC.SimplyLibrary.api.SimplyCommandBuilder;import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

class SimplyCommandBuilderImpl extends Command implements SimplyCommandBuilder {
    private String namespace = "simplylibrary";
    private String permission;
    private String permissionMessage = "<red>You don't have permission to use this command!";
    private String usageMessage = "<red>Invalid Usage!";
    private String onlyPlayersMessage = "<red>Only players can use this command!";
    private boolean requireArgs = false;
    private boolean onlyPlayers = false;
    private CommandExecutor commandExecutor;
    private static Field commandMapField;
    private final SimplyLibrary lib = SimplyLibrary.getInstance();

    static {
        try {
            commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
        } catch (Exception ignored) {
        }
    }

    SimplyCommandBuilderImpl(String name) {
        super(name);
        this.description = "A command made using SimplyCommandBuilder";
    }

    @Override
    public SimplyCommandBuilderImpl aliases(@NotNull List<String> aliases) {
        this.setAliases(aliases);
        return this;
    }

    public SimplyCommandBuilderImpl aliases(@NotNull String... aliases) {
        this.setAliases(Arrays.asList(aliases));
        return this;
    }

    @Override
    public SimplyCommandBuilderImpl namespace(@NotNull String namespace) {
        this.namespace = namespace.toLowerCase();
        return this;
    }

    @Override
    public SimplyCommandBuilderImpl description(@NotNull String description) {
        this.setDescription(description);
        return this;
    }

    @Override
    public SimplyCommandBuilderImpl permission(@NotNull String permission) {
        this.permission = permission;
        this.setPermission(permission);
        return this;
    }

    @Override
    public SimplyCommandBuilderImpl onlyPlayers(boolean onlyPlayers) {
        this.onlyPlayers = onlyPlayers;
        return this;
    }

    @Override
    public SimplyCommandBuilderImpl onlyPlayersMessage(@NotNull String message) {
        this.onlyPlayersMessage = message;
        return this;
    }

    @Override
    public SimplyCommandBuilderImpl permissionMessage(@NotNull String message) {
        this.permissionMessage = message;
        return this;
    }

    @Override
    public SimplyCommandBuilderImpl usageMessage(@NotNull String message) {
        this.usageMessage = message;
        return this;
    }

    @Override
    public SimplyCommandBuilderImpl requireArgs(boolean require) {
        this.requireArgs = require;
        return this;
    }

    @Override
    public SimplyCommandBuilder execute(CommandExecutor logic) {
        this.commandExecutor = logic;
        return this;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender,
                           @NotNull String label,
                           @NotNull String[] args) {

        if (onlyPlayers && !(sender instanceof Player)) {
            lib.getTextUtils().sendMessage(sender, onlyPlayersMessage);
            return true;
        }


        if (permission != null && !sender.hasPermission(permission)) {
            lib.getTextUtils().sendMessage(sender, permissionMessage);
            return true;
        }

        if (args.length == 0) {
            if (requireArgs) {
                lib.getTextUtils().sendMessage(sender, usageMessage);
            } else if (commandExecutor != null) {
                commandExecutor.execute(sender, (Player) sender, args);
            }
            return true;
        }

        if (commandExecutor != null) {
            commandExecutor.execute(sender, (Player) sender, args);
            return true;
        }

        lib.getTextUtils().sendMessage(sender, usageMessage);
        return true;
    }

    @Override
    public void build() {
        if (!Bukkit.isPrimaryThread())
            throw new ConcurrentModificationException("Attempted to build a command using SimplyCommandBuilder asynchronously! " +
                    "This is NOT a bug on SimplyLibrary side! " +
                    "'.build()' must be called on the main thread!");

        try {
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(this.namespace, this);
        } catch (Exception e) {
            Bukkit.getLogger().severe("SimplyLibrary: Failed to make command: " + getName());
        }
    }
}