package net.xstopho.wizards_reborn.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.xstopho.wizards_reborn.registries.BlockRegistry;
import net.xstopho.wizards_reborn.registries.WorldGenRegistry;

import java.util.Optional;

public class MorBlock extends MushroomPlantBlock {
    public MorBlock(Settings settings) {
        super(settings, WorldGenRegistry.TALL_MOR);
    }

    @Override
    public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        RegistryKey<ConfiguredFeature<?, ?>> configuredFeature;
        if (random.nextFloat() < 0.4) {
            configuredFeature = (this == BlockRegistry.MOR) ? WorldGenRegistry.TALL_MOR : WorldGenRegistry.TALL_ELDER_MOR;
        } else {
            configuredFeature = (this == BlockRegistry.MOR) ? WorldGenRegistry.HUGE_MOR : WorldGenRegistry.HUGE_ELDER_MOR;
        }

        Optional<RegistryEntry.Reference<ConfiguredFeature<?, ?>>> optional = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(configuredFeature);
        if (optional.isEmpty()) {
            return false;
        }
        world.removeBlock(pos, false);
        if (((ConfiguredFeature<?, ?>)((RegistryEntry<?>)optional.get()).value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos)) {
            return true;
        }
        world.setBlockState(pos, state, Block.NOTIFY_ALL);
        return false;
    }
}
