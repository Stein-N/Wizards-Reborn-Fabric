package net.xstopho.wizards_reborn.registries;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.client.particle.ArcaneWoodLeafParticleType;
import net.xstopho.wizards_reborn.client.particle.GenericParticleData;
import net.xstopho.wizards_reborn.client.particle.SparkleParticleType;

public class ParticleRegistry {



    public static final ParticleType<GenericParticleData> ARCANE_WOOD_LEAF_PARTICLE =
            register("arcane_wood_leaf", FabricParticleTypes.complex(new ArcaneWoodLeafParticleType().getParametersFactory()));

    public static final ParticleType<GenericParticleData> SPARKLE_PARTICLE =
            register("sparkle", FabricParticleTypes.complex(new SparkleParticleType().getParametersFactory()));
    private static ParticleType<GenericParticleData> register(String name, ParticleType type) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(WizardsReborn.MOD_ID, name), type);
    }

    public static void init() {

    }
}
