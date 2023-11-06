package net.xstopho.wizards_reborn.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.common.block.*;
import net.xstopho.wizards_reborn.common.world.tree.ArcaneWoodTree;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block ARCANE_GOLD_BLOCK = registerWithItem("arcane_gold_block", new Block(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK)));
    public static final Block ARCANE_GOLD_ORE = registerWithItem("arcane_gold_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.GOLD_ORE), UniformIntProvider.create(1, 3)));
    public static final Block DEEPSLATE_ARCANE_GOLD_ORE = registerWithItem("deepslate_arcane_gold_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_GOLD_ORE), UniformIntProvider.create(1, 3)));
    public static final Block NETHER_ARCANE_GOLD_ORE = registerWithItem("nether_arcane_gold_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHER_GOLD_ORE), UniformIntProvider.create(1, 3)));
    public static final Block RAW_ARCANE_GOLD_BLOCK = registerWithItem("raw_arcane_gold_block", new Block(FabricBlockSettings.copyOf(Blocks.RAW_GOLD_BLOCK)));

    public static final Block ARCANUM_BLOCK = registerWithItem("arcanum_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)));
    public static final Block ARCANUM_ORE = registerWithItem("arcanum_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE), UniformIntProvider.create(1, 3)));
    public static final Block DEEPSLATE_ARACNUM_ORE = registerWithItem("deepslate_arcanum_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_DIAMOND_ORE), UniformIntProvider.create(1, 3)));

    public static final Block ARCANE_WOOD_LOG = registerWithItem("arcane_wood_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block ARCANE_WOOD = registerWithItem("arcane_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_ARCANE_WOOD_LOG = registerWithItem("stripped_arcane_wood_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_ARCANE_WOOD = registerWithItem("stripped_arcane_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));

    public static final Block ARCANE_WOOD_PLANKS = registerWithItem("arcane_wood_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block ARCANE_WOOD_STAIRS = registerWithItem("arcane_wood_stairs", new StairsBlock(ARCANE_WOOD_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block ARCANE_WOOD_SLAB = registerWithItem("arcane_wood_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block ARCANE_WOOD_FENCE = registerWithItem("arcane_wood_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block ARCANE_WOOD_FENCE_GATE = registerWithItem("arcane_wood_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.OAK));
    public static final Block ARCANE_WOOD_DOOR = registerWithItem("arcane_wood_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR), BlockSetType.OAK));
    public static final Block ARCANE_WOOD_TRAPDOOR = registerWithItem("arcane_wood_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));
    public static final Block ARCANE_WOOD_PRESSURE_PLATE = registerWithItem("arcane_wood_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final Block ARCANE_WOOD_BUTTON = registerWithItem("arcane_wood_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.OAK, 10, true));
    //TODO Add all Sign types
    public static final Block ARCANE_WOOD_LEAVES = registerWithItem("arcane_wood_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    public static final Block ARCANE_WOOD_SAPLING = registerWithItem("arcane_wood_sapling", new SaplingBlock(new ArcaneWoodTree(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block POTTED_ARCANE_WOOD_SAPLING = register("potted_arcane_wood_sapling", new FlowerPotBlock(ARCANE_WOOD_SAPLING, FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));

    public static final Block ARCANE_LINEN = register("arcane_linen", new ArcaneLinenBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block ARCANE_LINES_HAY = registerWithItem("arcane_linen_hay", new HayBlock(FabricBlockSettings.copyOf(Blocks.HAY_BLOCK)));

    public static final Block MOR = registerWithItem("mor", new MorBlock(FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM)));
    public static final Block POTTED_MOR = register("potted_mor", new FlowerPotBlock(MOR, FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));
    public static final Block MOR_BLOCK = registerWithItem("mor_block", new MushroomBlock(FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM_BLOCK)));
    public static final Block ELDER_MOR = registerWithItem("elder_mor", new MorBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM)));
    public static final Block POTTED_ELDER_MOR = register("potted_elder_mor", new FlowerPotBlock(ELDER_MOR, FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));
    public static final Block ELDER_MOR_BLOCK = registerWithItem("elder_mor_block", new MushroomBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM_BLOCK)));

    public static final Block EARTH_CRYSTAL_SEED_BLOCK = registerWithItem("earth_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block WATER_CRYSTAL_SEED_BLOCK = registerWithItem("water_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block AIR_CRYSTAL_SEED_BLOCK = registerWithItem("air_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block FIRE_CRYSTAL_SEED_BLOCK = registerWithItem("fire_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block VOID_CRYSTAL_SEED_BLOCK = registerWithItem("void_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    public static final Block EARTH_CRYSTAL_GROWTH = registerWithItem("earth_crystal_growth", new CrystalGrowthBlock(CrystalRegistry.EARTH_CRYSTAL_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block WATER_CRYSTAL_GROWTH = registerWithItem("water_crystal_growth", new CrystalGrowthBlock(CrystalRegistry.WATER_CRYSTAL_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block AIR_CRYSTAL_GROWTH = registerWithItem("air_crystal_growth", new CrystalGrowthBlock(CrystalRegistry.AIR_CRYSTAL_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block FIRE_CRYSTAL_GROWTH = registerWithItem("fire_crystal_growth", new CrystalGrowthBlock(CrystalRegistry.FIRE_CRYSTAL_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block VOID_CRYSTAL_GROWTH = registerWithItem("void_crystal_growth", new CrystalGrowthBlock(CrystalRegistry.VOID_CRYSTAL_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    public static final Block EARTH_CRYSTAL_BLOCK = register("earth_crystal", new CrystalBlock(CrystalRegistry.EARTH_CRYSTAL_TYPE, CrystalRegistry.CRYSTAL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block WATER_CRYSTAL_BLOCK = register("water_crystal", new CrystalBlock(CrystalRegistry.WATER_CRYSTAL_TYPE, CrystalRegistry.CRYSTAL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block AIR_CRYSTAL_BLOCK = register("air_crystal", new CrystalBlock(CrystalRegistry.AIR_CRYSTAL_TYPE, CrystalRegistry.CRYSTAL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block FIRE_CRYSTAL_BLOCK = register("fire_crystal", new CrystalBlock(CrystalRegistry.FIRE_CRYSTAL_TYPE, CrystalRegistry.CRYSTAL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block VOID_CRYSTAL_BLOCK = register("void_crystal", new CrystalBlock(CrystalRegistry.VOID_CRYSTAL_TYPE, CrystalRegistry.CRYSTAL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    public static final Block FACETED_EARTH_CRYTSAL_BLOCK = register("faceted_earth_crystal", new CrystalBlock(CrystalRegistry.EARTH_CRYSTAL_TYPE, CrystalRegistry.FACETED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block FACETED_WATER_CRYTSAL_BLOCK = register("faceted_water_crystal", new CrystalBlock(CrystalRegistry.WATER_CRYSTAL_TYPE, CrystalRegistry.FACETED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block FACETED_AIR_CRYTSAL_BLOCK = register("faceted_air_crystal", new CrystalBlock(CrystalRegistry.AIR_CRYSTAL_TYPE, CrystalRegistry.FACETED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block FACETED_FIRE_CRYTSAL_BLOCK = register("faceted_fire_crystal", new CrystalBlock(CrystalRegistry.FIRE_CRYSTAL_TYPE, CrystalRegistry.FACETED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block FACETED_VOID_CRYTSAL_BLOCK = register("faceted_void_crystal", new CrystalBlock(CrystalRegistry.VOID_CRYSTAL_TYPE, CrystalRegistry.FACETED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    public static final Block ADVANCED_EARTH_CRYSTAL_BLOCK = register("advanced_earth_crystal", new CrystalBlock(CrystalRegistry.EARTH_CRYSTAL_TYPE, CrystalRegistry.ADVANCED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block ADVANCED_WATER_CRYSTAL_BLOCK = register("advanced_water_crystal", new CrystalBlock(CrystalRegistry.WATER_CRYSTAL_TYPE, CrystalRegistry.ADVANCED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block ADVANCED_AIR_CRYSTAL_BLOCK = register("advanced_air_crystal", new CrystalBlock(CrystalRegistry.AIR_CRYSTAL_TYPE, CrystalRegistry.ADVANCED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block ADVANCED_FIRE_CRYSTAL_BLOCK = register("advanced_fire_crystal", new CrystalBlock(CrystalRegistry.FIRE_CRYSTAL_TYPE, CrystalRegistry.ADVANCED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block ADVANCED_VOID_CRYSTAL_BLOCK = register("advanced_void_crystal", new CrystalBlock(CrystalRegistry.VOID_CRYSTAL_TYPE, CrystalRegistry.ADVANCED_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    public static final Block MASTERFUL_EARTH_CRYSTAL_BLOCK = register("masterful_earth_crystal", new CrystalBlock(CrystalRegistry.EARTH_CRYSTAL_TYPE, CrystalRegistry.MASTERFUL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block MASTERFUL_WATER_CRYSTAL_BLOCK = register("masterful_water_crystal", new CrystalBlock(CrystalRegistry.WATER_CRYSTAL_TYPE, CrystalRegistry.MASTERFUL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block MASTERFUL_AIR_CRYSTAL_BLOCK = register("masterful_air_crystal", new CrystalBlock(CrystalRegistry.AIR_CRYSTAL_TYPE, CrystalRegistry.MASTERFUL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block MASTERFUL_FIRE_CRYSTAL_BLOCK = register("masterful_fire_crystal", new CrystalBlock(CrystalRegistry.FIRE_CRYSTAL_TYPE, CrystalRegistry.MASTERFUL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block MASTERFUL_VOID_CRYSTAL_BLOCK = register("masterful_void_crystal", new CrystalBlock(CrystalRegistry.VOID_CRYSTAL_TYPE, CrystalRegistry.MASTERFUL_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    public static final Block PURE_EARTH_CRYSTAL_BLOCK = register("pure_earth_crystal", new CrystalBlock(CrystalRegistry.EARTH_CRYSTAL_TYPE, CrystalRegistry.PURE_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block PURE_WATER_CRYSTAL_BLOCK = register("pure_water_crystal", new CrystalBlock(CrystalRegistry.WATER_CRYSTAL_TYPE, CrystalRegistry.PURE_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block PURE_AIR_CRYSTAL_BLOCK = register("pure_air_crystal", new CrystalBlock(CrystalRegistry.AIR_CRYSTAL_TYPE, CrystalRegistry.PURE_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block PURE_FIRE_CRYSTAL_BLOCK = register("pure_fire_crystal", new CrystalBlock(CrystalRegistry.FIRE_CRYSTAL_TYPE, CrystalRegistry.PURE_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block PURE_VOID_CRYSTAL_BLOCK = register("pure_void_crystal", new CrystalBlock(CrystalRegistry.VOID_CRYSTAL_TYPE, CrystalRegistry.PURE_POLISHING_TYPE, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    private static Block register(String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(WizardsReborn.MOD_ID, id), block);
    }

    private static Block registerWithItem(String id, Block block) {
        BLOCKS.add(block);
        Registry.register(Registries.ITEM, new Identifier(WizardsReborn.MOD_ID, id), new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, new Identifier(WizardsReborn.MOD_ID, id), block);
    }

    public static void init() {

    }
}
