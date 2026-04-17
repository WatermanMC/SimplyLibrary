package com.github.WatermanMC.SimplyLibrary.api;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * A command executor interface for creating the command logic in {@link SimplyCommandBuilder}
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
@FunctionalInterface
public interface CommandExecutor {
    /**
     * Used to execute the main command name (label) in {@link SimplyCommandBuilder}
     *
     * @param sender The {@link CommandSender}
     * @param args The command arguments
     */
    void execute(@NotNull CommandSender sender, @NotNull String[] args);
}