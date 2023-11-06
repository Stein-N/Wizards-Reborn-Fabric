package net.xstopho.wizards_reborn.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.common.item.CrystalItem;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item ARCANE_LINEN_SEEDS = register("arcane_linen_seeds", new BlockItem(BlockRegistry.ARCANE_LINEN, new FabricItemSettings()));

    //TODO create FracturedCrystalItem Class
    public static final Item FRACTURED_EARTH_CRYSTAL = register("fractured_earth_crystal", new Item(new FabricItemSettings()));
    public static final Item FRACTURED_WATER_CRYSTAL = register("fractured_water_crystal", new Item(new FabricItemSettings()));
    public static final Item FRACTURED_AIR_CRYSTAL = register("fractured_air_crystal", new Item(new FabricItemSettings()));
    public static final Item FRACTURED_FIRE_CRYSTAL = register("fractured_fire_crystal", new Item(new FabricItemSettings()));
    public static final Item FRACTURED_VOID_CRYSTAL = register("fractured_void_crystal", new Item(new FabricItemSettings()));

    public static final Item EARTH_CRYSTAL = register("earth_crystal", new CrystalItem(BlockRegistry.EARTH_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item WATER_CRYSTAL = register("water_crystal", new CrystalItem(BlockRegistry.WATER_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item AIR_CRYSTAL = register("air_crystal", new CrystalItem(BlockRegistry.AIR_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item FIRE_CRYSTAL = register("fire_crystal", new CrystalItem(BlockRegistry.FIRE_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item VOID_CRYSTAL = register("void_crystal", new CrystalItem(BlockRegistry.VOID_CRYSTAL_BLOCK, new FabricItemSettings()));

    public static final Item FACETED_EARTH_CRYSTAL = register("faceted_earth_crystal", new CrystalItem(BlockRegistry.FACETED_EARTH_CRYTSAL_BLOCK, new FabricItemSettings()));
    public static final Item FACETED_WATER_CRYSTAL = register("faceted_water_crystal", new CrystalItem(BlockRegistry.FACETED_WATER_CRYTSAL_BLOCK, new FabricItemSettings()));
    public static final Item FACETED_AIR_CRYSTAL = register("faceted_air_crystal", new CrystalItem(BlockRegistry.FACETED_AIR_CRYTSAL_BLOCK, new FabricItemSettings()));
    public static final Item FACETED_FIRE_CRYSTAL = register("faceted_fire_crystal", new CrystalItem(BlockRegistry.FACETED_FIRE_CRYTSAL_BLOCK, new FabricItemSettings()));
    public static final Item FACETED_VOID_CRYSTAL = register("faceted_void_crystal", new CrystalItem(BlockRegistry.FACETED_VOID_CRYTSAL_BLOCK, new FabricItemSettings()));

    public static final Item ADVANCED_EARTH_CRYSTAL = register("advanced_earth_crystal", new CrystalItem(BlockRegistry.ADVANCED_EARTH_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item ADVANCED_WATER_CRYSTAL = register("advanced_water_crystal", new CrystalItem(BlockRegistry.ADVANCED_WATER_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item ADVANCED_AIR_CRYSTAL = register("advanced_air_crystal", new CrystalItem(BlockRegistry.ADVANCED_AIR_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item ADVANCED_FIRE_CRYSTAL = register("advanced_fire_crystal", new CrystalItem(BlockRegistry.ADVANCED_FIRE_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item ADVANCED_VOID_CRYSTAL = register("advanced_void_crystal", new CrystalItem(BlockRegistry.ADVANCED_VOID_CRYSTAL_BLOCK, new FabricItemSettings()));

    public static final Item MASTERFUL_EARTH_CRYSTAL = register("masterful_earth_crystal", new CrystalItem(BlockRegistry.MASTERFUL_EARTH_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item MASTERFUL_WATER_CRYSTAL = register("masterful_water_crystal", new CrystalItem(BlockRegistry.MASTERFUL_WATER_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item MASTERFUL_AIR_CRYSTAL = register("masterful_air_crystal", new CrystalItem(BlockRegistry.MASTERFUL_AIR_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item MASTERFUL_FIRE_CRYSTAL = register("masterful_fire_crystal", new CrystalItem(BlockRegistry.MASTERFUL_FIRE_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item MASTERFUL_VOID_CRYSTAL = register("masterful_void_crystal", new CrystalItem(BlockRegistry.MASTERFUL_VOID_CRYSTAL_BLOCK, new FabricItemSettings()));

    public static final Item PURE_EARTH_CRYSTAL = register("pure_earth_crystal", new CrystalItem(BlockRegistry.PURE_EARTH_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item PURE_WATER_CRYSTAL = register("pure_water_crystal", new CrystalItem(BlockRegistry.PURE_WATER_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item PURE_AIR_CRYSTAL = register("pure_air_crystal", new CrystalItem(BlockRegistry.PURE_AIR_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item PURE_FIRE_CRYSTAL = register("pure_fire_crystal", new CrystalItem(BlockRegistry.PURE_FIRE_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item PURE_VOID_CRYSTAL = register("pure_void_crystal", new CrystalItem(BlockRegistry.PURE_VOID_CRYSTAL_BLOCK, new FabricItemSettings()));

    private static Item register(String id, Item item) {
        ITEMS.add(item);
        return Registry.register(Registries.ITEM, new Identifier(WizardsReborn.MOD_ID, id), item);
    }

    public static void init() {

    }
}
