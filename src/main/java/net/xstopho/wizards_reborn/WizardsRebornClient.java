package net.xstopho.wizards_reborn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.client.particle.ArcaneWoodLeafParticle;
import net.xstopho.wizards_reborn.client.particle.ArcaneWoodLeafParticleType;
import net.xstopho.wizards_reborn.client.particle.SparkleParticle;
import net.xstopho.wizards_reborn.client.particle.SparkleParticleType;
import net.xstopho.wizards_reborn.registries.BlockRegistry;
import net.xstopho.wizards_reborn.registries.ParticleRegistry;

public class WizardsRebornClient implements ClientModInitializer {

	public static ShaderProgram GLOWING_SHADER, GLOWING_SPRITE_SHADER, GLOWING_PARTICLES_SHADER, SPRITE_PARTICLE_SHADER;

	@Override
	public void onInitializeClient() {
		registerShader();

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				BlockRegistry.ARCANE_WOOD_DOOR, BlockRegistry.ARCANE_WOOD_SAPLING, BlockRegistry.POTTED_ARCANE_WOOD_SAPLING,
				BlockRegistry.ARCANE_LINEN, BlockRegistry.MOR, BlockRegistry.ELDER_MOR);

		ParticleFactoryRegistry.getInstance().register(ParticleRegistry.ARCANE_WOOD_LEAF_PARTICLE, ArcaneWoodLeafParticleType.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ParticleRegistry.SPARKLE_PARTICLE, SparkleParticleType.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ParticleRegistry.WISP_PARTICLE, SparkleParticleType.Factory::new);

	}

	private void registerShader() {
		CoreShaderRegistrationCallback.EVENT.register(context -> {
			Identifier id = new Identifier(WizardsReborn.MOD_ID, "glowing");
			context.register(id, VertexFormats.POSITION_COLOR, shaderProgram -> GLOWING_SHADER = shaderProgram);
		});

		CoreShaderRegistrationCallback.EVENT.register(context -> {
			Identifier id = new Identifier(WizardsReborn.MOD_ID, "glowing_sprite");
			context.register(id, VertexFormats.POSITION_COLOR, shaderProgram -> GLOWING_SPRITE_SHADER = shaderProgram);
		});

		CoreShaderRegistrationCallback.EVENT.register(context -> {
			Identifier id = new Identifier(WizardsReborn.MOD_ID, "glowing_particle");
			context.register(id, VertexFormats.POSITION_TEXTURE_COLOR_LIGHT, shaderProgram -> GLOWING_PARTICLES_SHADER = shaderProgram);
		});

		CoreShaderRegistrationCallback.EVENT.register(context -> {
			Identifier id = new Identifier(WizardsReborn.MOD_ID, "sprite_particle");
			context.register(id, VertexFormats.POSITION_COLOR, shaderProgram -> SPRITE_PARTICLE_SHADER = shaderProgram);
		});
	}

	public static ShaderProgram getGlowingShader() {
		return GLOWING_SHADER;
	}

	public static ShaderProgram getGlowingSpriteShader() {
		return GLOWING_SPRITE_SHADER;
	}

	public static ShaderProgram getGlowingParticleShader() {
		return GLOWING_PARTICLES_SHADER;
	}

	public static ShaderProgram getSpriteParticleShader() {
		return SPRITE_PARTICLE_SHADER;
	}
}