package net.xstopho.wizards_reborn.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item ARCANE_LINEN_SEEDS = register("arcane_linen_seeds", new AliasedBlockItem(BlockRegistry.ARCANE_LINEN, new FabricItemSettings()));

    private static Item register(String id, Item item) {
        ITEMS.add(item);
        return Registry.register(Registries.ITEM, new Identifier(WizardsReborn.MOD_ID, id), item);
    }

    public static void init() {

    }
}
