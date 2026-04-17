package com.github.WatermanMC.SimplyLibrary;

import com.github.WatermanMC.SimplyLibrary.api.SimplyTextUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

class SimplyTextUtilsImpl implements SimplyTextUtils {
    private final MiniMessage mm;

    SimplyTextUtilsImpl() {
        this.mm = MiniMessage.miniMessage();
    }

    @Override
    public void sendMessage(@NotNull Audience target, @NotNull Object @NotNull ... messages) {
        if (messages.length == 0) return;
        List<Component> parts = new ArrayList<>();

        for (Object msg : messages) {
            if (msg instanceof Component comp) {
                parts.add(comp);
            } else if (msg instanceof String str) {
                parts.add(mm.deserialize(str));
            } else {
                parts.add(Component.text(String.valueOf(msg)));
            }
        }
        target.sendMessage(Component.join(JoinConfiguration.noSeparators(), parts));
    }

    @Override
    @Deprecated
    public void legacySendMessage(@NotNull Audience target, @NotNull String messages) {
        target.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(messages));
    }

    @Override
    public Component convertToComponent(@NotNull Object @NotNull ... input) {
        if (input.length == 0) return Component.empty();

        Component result = Component.empty();
        for (Object msg : input) {
            if (msg instanceof Component comp) {
                result = result.append(comp);
            } else if (msg instanceof String str) {
                result = result.append(mm.deserialize(str));
            } else {
                result = result.append(Component.text(String.valueOf(msg)));
            }
        }
        return result;
    }

    @Override
    public void sendActionBar(@NotNull Plugin plugin,
                              @NotNull Audience target,
                              long durationTicks,
                              @NotNull String message) {
        if (durationTicks == 0) return;
        if (message.isBlank()) return;

        target.sendActionBar(mm.deserialize(message));

        new BukkitRunnable() {
            long elapsed = 0;

            @Override
            public void run() {
                elapsed += 40;
                if (elapsed >= durationTicks) {
                    this.cancel();
                    return;
                }
                target.sendActionBar(mm.deserialize(message));
            }
        }.runTaskTimer(plugin, 40L, 40L);
    }

    @Override
    public void sendTitles(@NotNull Plugin plugin,
                          @NotNull Audience target,
                          long durationTicks,
                          @NotNull String title,
                          @Nullable String subtitle) {
        if (durationTicks == 0) return;
        if (title.isBlank()) return;
        if (subtitle == null || subtitle.isBlank()) subtitle = "";

        Title titles = Title.title(mm.deserialize(title), mm.deserialize(subtitle));
        target.showTitle(titles);

        new BukkitRunnable() {
            long elapsed = 0;

            @Override
            public void run() {
                elapsed += 40;
                if (elapsed >= durationTicks) {
                    this.cancel();
                    target.clearTitle();
                    return;
                }
                target.showTitle(titles);
            }
        }.runTaskTimer(plugin, 40L, 40L);
    }
}
