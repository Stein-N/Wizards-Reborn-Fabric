package net.xstopho.wizards_reborn.common.world.tree;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.xstopho.wizards_reborn.registries.WorldGenRegistry;
import org.jetbrains.annotations.Nullable;

public class ArcaneWoodTree extends LargeTreeSaplingGenerator {
    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
        return WorldGenRegistry.FANCY_ARCANE_WOOD_TREE;
    }

    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return WorldGenRegistry.ARCANE_WOOD_TREE;
    }
}
