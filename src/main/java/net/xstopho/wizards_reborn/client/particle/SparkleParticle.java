package net.xstopho.wizards_reborn.client.particle;

import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.world.ClientWorld;
public class SparkleParticle extends GenericParticle {

    public SparkleParticle(ClientWorld world, GenericParticleData data, double x, double y, double z, double vX, double vY, double vZ) {
        super(world, data, x, y, z, vX, vY, vZ);
    }

    @Override
    protected int getBrightness(float tint) {
        return 0xF000F0;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
}
