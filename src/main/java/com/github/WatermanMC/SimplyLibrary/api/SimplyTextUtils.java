package com.github.WatermanMC.SimplyLibrary.api;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An interface for text utilities
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
public interface SimplyTextUtils {

    /**
     * Sends a message to anyone via {@link Audience}<br>
     * Anything is supported, it converts your message to {@link Component} and sends it to player
     *
     * @param target Target who you will send a message
     * @param messages The message will be sent
     */
    void sendMessage(@NotNull Audience target, @NotNull Object @NotNull ... messages);

    /**
     * @deprecated
     *
     * Sends a message to anyone via {@link Audience}<br>
     * Only supports legacy color codes using the ampersand symbol<br>
     * It is much more recommended to use {@link #sendMessage(Audience, Object...)} for modern alternative
     *
     * @param target Target who you will send a message
     * @param messages The message will be sent
     */
    @Deprecated
    void legacySendMessage(@NotNull Audience target, @NotNull String messages);

    /**
     * Combines and converts anything to {@link Component}
     *
     * @param input The objects to be joined and converted
     *
     * @return A single {@link Component} representing the input
     */
    Component convertToComponent(@NotNull Object @NotNull ... input);

    /**
     * Sends Action bar for specific duration in ticks
     *
     * @param plugin Plugin
     * @param target The one who receives the action bar
     * @param durationTicks Duration in ticks
     * @param message Message of action bar, supports {@link MiniMessage} only
     */
    void sendActionBar(@NotNull Plugin plugin,
                       @NotNull Player target,
                       long durationTicks,
                       @NotNull String message);

    /**
     * Sends Title and subtitle for specific duration in ticks
     *
     * @param plugin Plugin
     * @param target The one who receives the title and subtitle
     * @param durationTicks Duration in ticks
     * @param title Title, supports {@link MiniMessage} only
     * @param subtitle Subtitle (optional), supports {@link MiniMessage} only
     */
    void sendTitles(@NotNull Plugin plugin,
                    @NotNull Player target,
                    long durationTicks,
                    @NotNull String title,
                    @Nullable String subtitle);
}