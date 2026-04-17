package com.github.WatermanMC.SimplyLibrary.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * A simplified economy management interface using Vault API built-in
 * <p>
 * This interface allows for basic financial transactions including transfers, balance
 * adjustments, and balance lookups
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
public interface SimplyEconomy {

    /**
     * Transfers a specific amount of currency from one player to another
     *
     * @param sender   The player sending the money
     * @param receiver The player receiving the money
     * @param amount   The total amount to transfer. Must be positive
     * @return A {@link TransactionResult} indicating the transaction status
     */
    TransactionResult pay(@NotNull Player sender,
                          @NotNull Player receiver,
                          double amount);

    /**
     * Removes a specific amount of currency from a player's account
     *
     * @param target The player whose taken with amount of money
     * @param amount The amount to withdraw
     * @return A {@link TransactionResult} indicating the withdrawal status
     */
    TransactionResult withdraw(@NotNull Player target, double amount);

    /**
     * Adds a specific amount of currency to a player's account
     *
     * @param target The player whose given with amount of money
     * @param amount The amount to deposit
     * @return A {@link TransactionResult} indicating the deposit status
     */
    TransactionResult deposit(@NotNull Player target, double amount);

    /**
     * Gets the current account balance of the target player
     *
     * @param target The player whose balance is being queried
     * @return The current balance as a double, or 0 if the economy provider is not found
     */
    double getBalance(@NotNull Player target);
}