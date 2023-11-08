package net.xstopho.wizards_reborn.common.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.xstopho.wizards_reborn.client.particle.Particles;
import net.xstopho.wizards_reborn.registries.ParticleRegistry;

public class ArcaneWoodLeavesBlock extends LeavesBlock {
    public ArcaneWoodLeavesBlock(Settings settings) {
        super(settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.getBlockState(pos.down(1)).isAir()) {
            if (random.nextFloat() < 0.25) {
                Particles.create(ParticleRegistry.ARCANE_WOOD_LEAF_PARTICLE)
                        .addVelocity(((random.nextDouble() - 0.5D) / 12), ((random.nextDouble() - 1.1D) / 4), ((random.nextDouble() - 0.5D) / 12))
                        .setAlpha(1f, 1f).setScale(0.25f, 0f)
                        .setColor(1f, 1f, 1f)
                        .setLifetime(150)
                        .setSpin((0.1f * ((random.nextFloat() - 0.5f) * 2)))
                        .spawn(world, pos.getX() + 0.5F + ((random.nextFloat() - 0.5f) * 0.9f), pos.getY() - 0.05, pos.getZ() + 0.5F + ((random.nextFloat() - 0.5f * 0.9f)));
            }
        }
    }

    private void spawnRandomLeafParticle(DefaultParticleType type, World world, BlockPos pos, Random random) {
        world.addParticle(type,
                true,
                pos.getX() + 0.5d + ((random.nextDouble() - 0.5d) * 0.9d),
                pos.getY() - 0.05d,
                pos.getZ() + 0.5d + ((random.nextDouble() - 0.5d * 0.9d)),
                0, 0, 0);
    }
}
