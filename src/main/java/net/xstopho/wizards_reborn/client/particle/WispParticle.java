package net.xstopho.wizards_reborn.client.particle;

import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.world.ClientWorld;

public class WispParticle extends GenericParticle {
    public WispParticle(ClientWorld world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
        super(world, data, x, y, z, vx, vy, vz);
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