package net.xstopho.wizards_reborn.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleType;
import org.jetbrains.annotations.Nullable;

public class ArcaneWoodLeafParticleType extends ParticleType<GenericParticleData> {


    public ArcaneWoodLeafParticleType() {
        super(true, GenericParticleData.DESERIALIZER);
    }

    @Override
    public Codec<GenericParticleData> getCodec() {
        return GenericParticleData.codecFor(this);
    }

    public static class Factory implements ParticleFactory<GenericParticleData> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(GenericParticleData parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            ArcaneWoodLeafParticle ret = new ArcaneWoodLeafParticle(world, parameters, x, y, z, velocityX, velocityY, velocityZ);
            ret.setSprite(sprites);
            return ret;
        }
    }
}
