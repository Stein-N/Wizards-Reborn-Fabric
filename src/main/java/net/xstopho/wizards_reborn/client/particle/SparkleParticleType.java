package net.xstopho.wizards_reborn.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleType;

public class SparkleParticleType extends ParticleType<GenericParticleData> {
    public SparkleParticleType() {
        super(true, GenericParticleData.DESERIALIZER);
    }

    @Override
    public Codec<GenericParticleData> getCodec() {
        return GenericParticleData.codecFor(this);
    }

    public static class Factory implements ParticleFactory<GenericParticleData> {
        private final SpriteProvider sprite;

        public Factory(SpriteProvider sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(GenericParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
            SparkleParticle ret = new SparkleParticle(world, data, x, y, z, mx, my, mz);
            ret.setSprite(sprite);
            return ret;
        }
    }
}
