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
import net.xstopho.wizards_reborn.common.block.CrystalGrowthBlock;
import net.xstopho.wizards_reborn.common.entities.CrystalGrowthBlockEntity;
import net.xstopho.wizards_reborn.common.item.CrystalItem;
import net.xstopho.wizards_reborn.common.item.FracturedCrystalItem;

public class ItemGroupRegistry {

    //TODO change icon
    public static final ItemGroup WIZARDS_REBORN = Registry.register(Registries.ITEM_GROUP, new Identifier(WizardsReborn.MOD_ID, "item_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.wizards_reborn_mod_tab")).icon(() -> new ItemStack(Items.STICK)).entries((displayContext, entries) -> {

                for (Block block : BlockRegistry.BLOCKS) {
                    entries.add(block);
                }

                for (Item item : ItemRegistry.ITEMS) {
                    if (item instanceof FracturedCrystalItem || item instanceof CrystalItem) {
                        entries.add(item instanceof FracturedCrystalItem ? FracturedCrystalItem.creativeTabRandomStats(item) : CrystalItem.creativeTabRandomStats(item));
                    }else {
                        entries.add(item);
                    }
                }

            }).build());

    public static void init() {

    }
}
