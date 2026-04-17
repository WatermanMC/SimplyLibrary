package com.github.WatermanMC.SimplyLibrary.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * An interface for Minecraft skin utilities
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
public interface SimplyPlayerSkinUtils {
    /**
     * Gets player skin value
     *
     * @param target Target who you want to get their skin
     * @return Base64 skin in {@link String}
     */
    String getPlayerSkinValue(@NotNull Player target);

    /**
     * Gets player skin signature
     *
     * @param target Target who you want to get their skin
     * @return Base64 skin in {@link String}
     */
    String getPlayerSkinSignature(@NotNull Player target);

    /**
     * Sets player skin
     *
     * @param plugin Plugin
     * @param target Target who you want to change their skin
     * @param skinSignature Skin signature in Base64 string
     * @param skinValue Skin value in Base64 string
     */
    void setPlayerSkin(@NotNull Plugin plugin,
                       @NotNull Player target,
                       @NotNull String skinSignature,
                       @NotNull String skinValue);

    /**
     * Gets default fallback Minecraft skin value (Steve's skin)
     *
     * @return Base64 skin value in {@link String}
     */
    String getDefaultSkinValue();

    /**
     * Gets default fallback Minecraft skin signature (Steve's skin)
     *
     * @return Base64 skin signature in {@link String}
     */
    String getDefaultSkinSignature();
}
