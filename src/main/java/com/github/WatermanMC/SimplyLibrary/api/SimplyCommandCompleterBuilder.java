package com.github.WatermanMC.SimplyLibrary.api;

/**
 * A command builder interface for easier creating PaperMC command completer
 * <p>
 * This interface allows for easy command completer creation by chaining configuration
 * methods and finally calling {@link #build()} to create the completer for the command
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
public interface SimplyCommandCompleterBuilder {

    /**
     * Adds specific logic for an argument at a given index
     *
     * @param index The zero-based index of the argument.
     * @param options The suggestions for that specific argument
     * @return This builder instance for chaining.
     */
    SimplyCommandCompleterBuilder arg(int index, String... options);
    /**
     * Registers the command directly into the server's CommandMap
     *
     * @throws java.util.ConcurrentModificationException When called asynchronously
     */
    void build();
}