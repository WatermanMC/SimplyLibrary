package com.github.WatermanMC.SimplyLibrary.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

/**
 * An item builder interface for easier creating items and player heads
 * <p>
 * This interface allows for easy item creation by chaining configuration<br>
 * methods. Call {@link #material(Material)} first before going anywhere else<br>
 * Call {@link #build()} to finish creating the item
 *
 * @since 1.0-BETA
 * @author WatermanMC
 */
public interface SimplyItemBuilder {
    /**
     * The material for the item
     *
     * @param material The material to be used to create the item
     * @return This builder instance for chaining
     */
    SimplyItemBuilder material(@NotNull Material material);
    /**
     * The name for the item
     *
     * @param name The name of the item. MiniMessage tags supported
     * @return This builder instance for chaining
     */
    SimplyItemBuilder name(@NotNull String name);
    /**
     * Gets the head of the player
     *
     * @param target The player targeted to get its head
     * @return This builder instance for chaining
     */
    SimplyItemBuilder head(@NotNull Player target);
    /**
     * Gets the head of the player via player name<br>
     * Allows support for Offline players
     *
     * @param playerName The player targeted to get its head
     * @return This builder instance for chaining
     */
    SimplyItemBuilder head(@NotNull String playerName);
    /**
     * The item quantity<br>
     * Maximum is up to 64 only
     *
     * @param quantity The quantity of the item
     * @return This builder instance for chaining
     */
    SimplyItemBuilder quantity(int quantity);
    /**
     * The item lores. MiniMessage tags supported
     *
     * @param lore The lore in the item
     * @return This builder instance for chaining
     */
    SimplyItemBuilder lore(@NotNull List<String> lore);
    /**
     * Allows you to set custom item meta using Paper API
     *
     * @param metaConsumer The ItemMeta consumer
     * @return This builder instance for chaining
     */
    SimplyItemBuilder meta(@NotNull Consumer<ItemMeta> metaConsumer);
    /**
     * This method <b>must</b> be called on the main server thread
     *
     * @throws IllegalStateException When called asynchronously
     * @return A new {@link ItemStack} containing the configured properties
     */
    ItemStack build();
}