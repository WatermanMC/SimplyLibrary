package com.github.WatermanMC.SimplyLibrary;

import com.github.WatermanMC.SimplyLibrary.api.*;

/**
 * SimplyLibrary API
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
public class SimplyLibrary {
    private static SimplyLibrary instance;
    private final SimplyEconomyImpl economy;
    private final SimplyTimersImpl timers;
    private final SimplyTextUtilsImpl textUtils;
    private final SimplyPlayerSkinUtilsImpl playerSkinUtils;

    SimplyLibrary(Main plugin) {
        this.economy = new SimplyEconomyImpl(plugin);
        this.timers = new SimplyTimersImpl(plugin);
        this.textUtils = new SimplyTextUtilsImpl();
        this.playerSkinUtils = new SimplyPlayerSkinUtilsImpl();
    }

    /**
     * Access the main instance
     *
     * @return Access to all parts of SimplyLibrary
     */
    public static SimplyLibrary getInstance() {
        return instance;
    }

    /**
     * Access the economy
     *
     * @return Access to economy
     */
    public SimplyEconomy getEconomy() {
        return this.economy;
    }

    /**
     * Access the timers
     *
     * @return Access to timers
     */
    public SimplyTimers getTimers() {
        return timers;
    }

    /**
     * Access the text utilities
     *
     * @return Access to text utilities
     */
    public SimplyTextUtils getTextUtils() {
        return textUtils;
    }

    /**
     * Access the player skin utilities
     *
     * @return Access to player skin utilities
     */
    public SimplyPlayerSkinUtils getPlayerSkinUtils() {
        return playerSkinUtils;
    }

    /**
     * Access the item builder
     *
     * @return Access to item builder
     */
    public SimplyItemBuilder getItemBuilder() {
        return new SimplyItemBuilderImpl();
    }

    /**
     * Access Command builder
     *
     * @param commandExecutor Sets the primary label (name) of the command
     * @return Access to Command builder
     */
    public SimplyCommandBuilder getCommandBuilder(String commandExecutor) {
        return new SimplyCommandBuilderImpl(commandExecutor);
    }

    /**
     * Access Command tab completer builder
     *
     * @param commandExecutor Access the command name (label)
     * @return Access to Command tab completer builder
     */
    public SimplyCommandCompleterBuilder getCommandCompleterBuilder(String commandExecutor) {
        return new SimplyCommandCompleterBuilderImpl(commandExecutor);
    }
}