package net.xstopho.wizards_reborn.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.TextureManager;
import net.xstopho.wizards_reborn.WizardsRebornClient;
import org.lwjgl.opengl.GL11;

public class SpriteParticleRenderType implements ParticleTextureSheet {
    public static final SpriteParticleRenderType INSTANCE = new SpriteParticleRenderType();

    private static void beginRenderCommon(BufferBuilder bufferBuilder, TextureManager textureManager) {
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShader(WizardsRebornClient::getSpriteParticleShader);
        RenderSystem.setShaderTexture(0, SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE);
        //TODO WorldRenderHandler.particleMVMatrix = RenderSystem.getModelViewMatrix();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR_LIGHT);
    }

    private static void endRenderCommon() {
        MinecraftClient.getInstance().getTextureManager().getTexture(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE);
        RenderSystem.depthMask(true);
    }



    @Override
    public void begin(BufferBuilder b, TextureManager tex) {
        beginRenderCommon(b, tex);
    }

    @Override
    public void draw(Tessellator tessellator) {
        tessellator.draw();
        RenderSystem.enableDepthTest();
        endRenderCommon();
    }
}
