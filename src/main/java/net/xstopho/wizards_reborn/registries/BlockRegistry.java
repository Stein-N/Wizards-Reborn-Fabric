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
import net.xstopho.wizards_reborn.common.block.ArcaneLinenBlock;
import net.xstopho.wizards_reborn.common.block.CrystalSeedBlock;
import net.xstopho.wizards_reborn.common.block.MorBlock;
import net.xstopho.wizards_reborn.common.world.tree.ArcaneWoodTree;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block ARCANE_GOLD_BLOCK = register("arcane_gold_block", new Block(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK)));
    public static final Block ARCANE_GOLD_ORE = register("arcane_gold_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.GOLD_ORE), UniformIntProvider.create(1, 3)));
    public static final Block DEEPSLATE_ARCANE_GOLD_ORE = register("deepslate_arcane_gold_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_GOLD_ORE), UniformIntProvider.create(1, 3)));
    public static final Block NETHER_ARCANE_GOLD_ORE = register("nether_arcane_gold_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHER_GOLD_ORE), UniformIntProvider.create(1, 3)));
    public static final Block RAW_ARCANE_GOLD_BLOCK = register("raw_arcane_gold_block", new Block(FabricBlockSettings.copyOf(Blocks.RAW_GOLD_BLOCK)));

    public static final Block ARCANUM_BLOCK = register("arcanum_block", new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)));
    public static final Block ARCANUM_ORE = register("arcanum_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE), UniformIntProvider.create(1, 3)));
    public static final Block DEEPSLATE_ARACNUM_ORE = register("deepslate_arcanum_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_DIAMOND_ORE), UniformIntProvider.create(1, 3)));

    public static final Block ARCANE_WOOD_LOG = register("arcane_wood_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block ARCANE_WOOD = register("arcane_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_ARCANE_WOOD_LOG = register("stripped_arcane_wood_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_ARCANE_WOOD = register("stripped_arcane_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));

    public static final Block ARCANE_WOOD_PLANKS = register("arcane_wood_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block ARCANE_WOOD_STAIRS = register("arcane_wood_stairs", new StairsBlock(ARCANE_WOOD_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block ARCANE_WOOD_SLAB = register("arcane_wood_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block ARCANE_WOOD_FENCE = register("arcane_wood_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block ARCANE_WOOD_FENCE_GATE = register("arcane_wood_fence_gate", new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.OAK));
    public static final Block ARCANE_WOOD_DOOR = register("arcane_wood_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR), BlockSetType.OAK));
    public static final Block ARCANE_WOOD_TRAPDOOR = register("arcane_wood_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));
    public static final Block ARCANE_WOOD_PRESSURE_PLATE = register("arcane_wood_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final Block ARCANE_WOOD_BUTTON = register("arcane_wood_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.OAK, 10, true));
    //TODO Add all Sign types
    public static final Block ARCANE_WOOD_LEAVES = register("arcane_wood_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    public static final Block ARCANE_WOOD_SAPLING = register("arcane_wood_sapling", new SaplingBlock(new ArcaneWoodTree(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block POTTED_ARCANE_WOOD_SAPLING = register("potted_arcane_wood_sapling", new FlowerPotBlock(ARCANE_WOOD_SAPLING, FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));

    public static final Block ARCANE_LINEN = Registry.register(Registries.BLOCK, new Identifier(WizardsReborn.MOD_ID, "arcane_linen"), new ArcaneLinenBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block ARCANE_LINES_HAY = register("arcane_linen_hay", new HayBlock(FabricBlockSettings.copyOf(Blocks.HAY_BLOCK)));

    public static final Block MOR = register("mor", new MorBlock(FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM)));
    public static final Block POTTED_MOR = register("potted_mor", new FlowerPotBlock(MOR, FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));
    public static final Block MOR_BLOCK = register("mor_block", new MushroomBlock(FabricBlockSettings.copyOf(Blocks.BROWN_MUSHROOM_BLOCK)));
    public static final Block ELDER_MOR = register("elder_mor", new MorBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM)));
    public static final Block POTTED_ELDER_MOR = register("potted_elder_mor", new FlowerPotBlock(ELDER_MOR, FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));
    public static final Block ELDER_MOR_BLOCK = register("elder_mor_block", new MushroomBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM_BLOCK)));

    public static final Block EARTH_CRYSTAL_SEED_BLOCK = register("earth_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block WATER_CRYSTAL_SEED_BLOCK = register("water_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block AIR_CRYSTAL_SEED_BLOCK = register("air_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block FIRE_CRYSTAL_SEED_BLOCK = register("fire_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));
    public static final Block VOID_CRYSTAL_SEED_BLOCK = register("void_crystal_seed", new CrystalSeedBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    private static Block register(String id, Block block) {
        BLOCKS.add(block);
        registerItem(id, block);
        return Registry.register(Registries.BLOCK, new Identifier(WizardsReborn.MOD_ID, id), block);
    }

    private static void registerItem(String id, Block block) {
        Registry.register(Registries.ITEM, new Identifier(WizardsReborn.MOD_ID, id), new BlockItem(block, new FabricItemSettings()));
    }

    public static void init() {

    }
}
