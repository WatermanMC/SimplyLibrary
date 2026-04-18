package com.github.WatermanMC.SimplyLibrary.api;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * A command builder interface for easier creating PaperMC commands
 * <p>
 * This interface allows for easy command creation by chaining configuration
 * methods and finally calling {@link #build()} to register the command
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
public interface SimplyCommandBuilder {

    /**
     * Sets a list of alternative names for the command
     *
     * @param aliases A list of strings representing command aliases
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder aliases(@NotNull List<String> aliases);

    /**
     * Sets alternative names for the command using varargs
     *
     * @param aliases One or more strings representing command aliases
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder aliases(@NotNull String... aliases);

    /**
     * Sets the namespace for the command (example, myplugin:mycommand)
     *
     * @param namespace The namespace string
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder namespace(@NotNull String namespace);

    /**
     * Sets the description of the command shown in the help menu.
     *
     * @param description A brief explanation of what the command does
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder description(@NotNull String description);

    /**
     * Sets the permission node required to execute this command.
     *
     * @param permission The permission string (e.g., permission.node)
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder permission(@NotNull String permission);

    /**
     * Toggles whether this command is restricted to players only
     *
     * @param onlyPlayers True if the console/command blocks should be blocked
     * @return This builder instance for chaining.
     */
    SimplyCommandBuilder onlyPlayers(boolean onlyPlayers);

    /**
     * Sets the message sent to non-player senders if {@link #onlyPlayers(boolean)} is true
     *
     * @param message The error message to display
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder onlyPlayersMessage(@NotNull String message);

    /**
     * Sets the message sent when a user lacks the required permission on {@link #permission(String)}
     *
     * @param message The No Permission message, {@link MiniMessage} only
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder permissionMessage(@NotNull String message);


    /**
     * Sets whether arguments are strictly required for the command to execute, {@link MiniMessage} only
     *
     * @param require True if the command should send {@link #usageMessage(String)} if without arguments
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder requireArgs(boolean require);

    /**
     * Sets the usage message shown when the command is used incorrectly
     *
     * @param message The usage message string
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder usageMessage(@NotNull String message);

    /**
     * The main execution logic on the command and arguments<br>
     * Use the Paper API for logic
     *
     * @param logic A {@link CommandExecutor} implementation or lambda
     * @return This builder instance for chaining
     */
    SimplyCommandBuilder execute(CommandExecutor logic);

    /**
     * Registers the command directly into the server's CommandMap
     * <p>
     * This method <b>must</b> be called on the main server thread
     * @throws ConcurrentModificationException When called asynchronously
     */
    void build();
}