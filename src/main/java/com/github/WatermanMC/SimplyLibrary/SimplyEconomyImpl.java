package com.github.WatermanMC.SimplyLibrary;

import com.github.WatermanMC.SimplyLibrary.api.SimplyEconomy;
import com.github.WatermanMC.SimplyLibrary.api.TransactionResult;import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

class SimplyEconomyImpl implements SimplyEconomy {
    private Economy vaultProvider = null;

    SimplyEconomyImpl(Main plugin) {
        setupVault();
    }

    protected void setupVault() {
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
            if (rsp != null) vaultProvider = rsp.getProvider(); {}
        }
    }

    @Override
    public @NotNull TransactionResult pay(@NotNull Player sender,
                                          @NotNull Player receiver,
                                          double amount) {
        if (vaultProvider == null) return TransactionResult.PROVIDER_NOT_FOUND;

        if (amount <= 0 || Double.isNaN(amount) || Double.isInfinite(amount))
            return TransactionResult.INVALID_NUMBER;

        if (!vaultProvider.has(sender, amount))
            return TransactionResult.SENDER_INSUFFICIENT_FUNDS;

        EconomyResponse withdraw = vaultProvider.withdrawPlayer(sender, amount);
        if (withdraw.transactionSuccess()) {
            EconomyResponse deposit = vaultProvider.depositPlayer(receiver, amount);
            if (deposit.transactionSuccess()) {
                return TransactionResult.SUCCESS;
            } else {
                vaultProvider.depositPlayer(sender, amount);
                return TransactionResult.GENERIC_FAILED;
            }
        }
        return TransactionResult.GENERIC_FAILED;
    }

    @Override
    public @NotNull TransactionResult withdraw(@NotNull Player target, double amount) {
        if (vaultProvider == null) return TransactionResult.PROVIDER_NOT_FOUND;

        if (amount <= 0 || Double.isNaN(amount) || Double.isInfinite(amount))
            return TransactionResult.INVALID_NUMBER;

        EconomyResponse response = vaultProvider.withdrawPlayer(target, amount);
        return response.transactionSuccess() ? TransactionResult.SUCCESS : TransactionResult.GENERIC_FAILED;
    }

    @Override
    public @NotNull TransactionResult deposit(@NotNull Player target, double amount) {
        if (vaultProvider == null) return TransactionResult.PROVIDER_NOT_FOUND;

        if (amount <= 0 || Double.isNaN(amount) || Double.isInfinite(amount))
            return TransactionResult.INVALID_NUMBER;

        EconomyResponse response = vaultProvider.depositPlayer(target, amount);
        return response.transactionSuccess() ? TransactionResult.SUCCESS : TransactionResult.GENERIC_FAILED;
    }

    @Override
    public double getBalance(@NotNull Player target) {
        if (vaultProvider == null) return 0;
        return vaultProvider.getBalance(target);
    }
}