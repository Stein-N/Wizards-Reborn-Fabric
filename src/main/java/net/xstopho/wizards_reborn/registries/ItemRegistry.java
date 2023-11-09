package net.xstopho.wizards_reborn.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.common.item.CrystalItem;
import net.xstopho.wizards_reborn.common.item.FracturedCrystalItem;
import net.xstopho.wizards_reborn.common.item.equipment.CustomItemToolMaterial;
import net.xstopho.wizards_reborn.common.item.equipment.ScytheItem;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item ARCAEN_GOLD_INGOT = register("arcane_gold_ingot", new Item(new FabricItemSettings()));
    public static final Item ARCANE_GOLD_NUGGET = register("arcane_gold_nugget", new Item(new FabricItemSettings()));
    public static final Item RAW_ARCANE_GOLD = register("raw_arcane_gold", new Item(new FabricItemSettings()));

    public static final Item ARCANE_GOLD_SWORD = register("arcane_gold_sword", new SwordItem(CustomItemToolMaterial.ARCANE_GOLD, 3, -2.4f, new FabricItemSettings()));
    public static final Item ARCANE_GOLD_PICKAXE = register("arcane_gold_pickaxe", new PickaxeItem(CustomItemToolMaterial.ARCANE_GOLD, 1, -2.8f, new FabricItemSettings()));
    public static final Item ARCANE_GOLD_AXE = register("arcane_gold_axe", new AxeItem(CustomItemToolMaterial.ARCANE_GOLD, 6, -3.1f, new FabricItemSettings()));
    public static final Item ARCANE_GOLD_SHOVEL = register("arcane_gold_shovel", new ShovelItem(CustomItemToolMaterial.ARCANE_GOLD, 1.5f, -3f, new FabricItemSettings()));
    public static final Item ARCANE_GOLD_HOE = register("arcane_gold_hoe", new HoeItem(CustomItemToolMaterial.ARCANE_GOLD, -2, -2.8f, new FabricItemSettings()));
    public static final Item ARCANE_GOLD_SCYTHE = register("arcane_gold_scythe", new ScytheItem(CustomItemToolMaterial.ARCANE_GOLD, 4, -2.8f, new FabricItemSettings(), 1));

    public static final Item ARCANE_LINEN = register("arcane_linen", new Item(new FabricItemSettings()));
    public static final Item ARCANE_LINEN_SEEDS = register("arcane_linen_seeds", new BlockItem(BlockRegistry.ARCANE_LINEN, new FabricItemSettings()));

    public static final Item FRACTURED_EARTH_CRYSTAL = register("fractured_earth_crystal", new FracturedCrystalItem(CrystalRegistry.EARTH_CRYSTAL_TYPE, new FabricItemSettings()));
    public static final Item FRACTURED_WATER_CRYSTAL = register("fractured_water_crystal", new FracturedCrystalItem(CrystalRegistry.WATER_CRYSTAL_TYPE, new FabricItemSettings()));
    public static final Item FRACTURED_AIR_CRYSTAL = register("fractured_air_crystal", new FracturedCrystalItem(CrystalRegistry.AIR_CRYSTAL_TYPE, new FabricItemSettings()));
    public static final Item FRACTURED_FIRE_CRYSTAL = register("fractured_fire_crystal", new FracturedCrystalItem(CrystalRegistry.FIRE_CRYSTAL_TYPE, new FabricItemSettings()));
    public static final Item FRACTURED_VOID_CRYSTAL = register("fractured_void_crystal", new FracturedCrystalItem(CrystalRegistry.VOID_CRYSTAL_TYPE, new FabricItemSettings()));

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
