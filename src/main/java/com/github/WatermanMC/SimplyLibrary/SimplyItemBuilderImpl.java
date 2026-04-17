package com.github.WatermanMC.SimplyLibrary;

import com.github.WatermanMC.SimplyLibrary.api.SimplyItemBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

class SimplyItemBuilderImpl implements SimplyItemBuilder {
    private ItemStack item;
    private ItemMeta meta;
    private final MiniMessage mm = MiniMessage.miniMessage();

    @Override
    public SimplyItemBuilderImpl material(@NotNull Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
        return this;
    }

    @Override
    public SimplyItemBuilderImpl name(@NotNull String name) {
        if (meta != null)
            meta.displayName(mm.deserialize(name));
        return this;
    }

    @Override
    public SimplyItemBuilderImpl head(@NotNull Player target) {
        if (meta instanceof SkullMeta skullMeta)
            skullMeta.setOwningPlayer(target);
        return this;
    }

    @Override
    public SimplyItemBuilderImpl head(@NotNull String playerName) {
        if (meta instanceof SkullMeta skullMeta)
            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        return this;
    }

    @Override
    public SimplyItemBuilderImpl quantity(int quantity) {
        if (quantity > 0) {
            int finalQuantity = Math.min(quantity, 64);
            item.setAmount(finalQuantity);
        }
        return this;
    }

    @Override
    public SimplyItemBuilderImpl lore(@NotNull List<String> lore) {
        if (meta != null)
            meta.lore(lore.stream().map(mm::deserialize).toList());
        return this;
    }

    @Override
    public SimplyItemBuilderImpl meta(@NotNull Consumer<ItemMeta> metaConsumer) {
        if (meta != null) metaConsumer.accept(meta);
        return this;
    }

    @Override
    public ItemStack build() {
        if (!Bukkit.isPrimaryThread())
            throw new IllegalStateException("Attempted to build an item using SimplyItemBuilder asynchronously! " +
                    "This is NOT a bug on SimplyLibrary side! " +
                    "'.build()' must be called on the main thread!");

        if (meta != null) item.setItemMeta(meta);
        return item;
    }
}