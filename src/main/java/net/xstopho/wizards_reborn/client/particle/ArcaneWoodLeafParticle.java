package net.xstopho.wizards_reborn.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;

import java.util.Random;


public class ArcaneWoodLeafParticle extends GenericParticle {

    protected ArcaneWoodLeafParticle(ClientWorld clientWorld, GenericParticleData data, double x, double y, double z, double vX, double vY, double vZ) {
        super(clientWorld, data, x, y, z, vX, vY, vZ);
    }

    @Override
    protected int getBrightness(float tint) {
        return 0xF000F0;
    }


    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }
}
