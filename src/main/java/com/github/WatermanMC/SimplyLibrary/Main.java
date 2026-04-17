package com.github.WatermanMC.SimplyLibrary;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @hidden
 */
public class Main extends JavaPlugin {
    private SimplyTimersImpl simplyTimersImpl;

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            getLogger().severe("Vault is plugin missing. Please download it here: https://www.spigotmc.org/resources/vault.34315/");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            new SimplyLibrary(this);
            getLogger().info("SimplyLibrary v" + getPluginMeta().getVersion() + " has been enabled!");
        }
    }

    @Override
    public void onDisable() {
        this.simplyTimersImpl.clearAll();
        getLogger().info("SimplyLibrary v" + getPluginMeta().getVersion() + " has been disabled!");
    }
}