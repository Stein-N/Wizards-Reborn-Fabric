package net.xstopho.wizards_reborn.registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;

public class ItemGroupRegistry {

    //TODO change icon
    public static final ItemGroup WIZARDS_REBORN = Registry.register(Registries.ITEM_GROUP, new Identifier(WizardsReborn.MOD_ID, "item_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.wizards_reborn_mod_tab")).icon(() -> new ItemStack(Items.STICK)).entries((displayContext, entries) -> {

                for (Block block : BlockRegistry.BLOCKS) {
                    if (!(block instanceof FlowerPotBlock)) {
                        entries.add(block);
                    }
                }

                for (Item item : ItemRegistry.ITEMS) {
                    entries.add(item);
                }

            }).build());

    public static void init() {

    }
}
