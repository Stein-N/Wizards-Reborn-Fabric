package net.xstopho.wizards_reborn.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.xstopho.wizards_reborn.utils.RenderUtils;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

public class WorldRenderHandler {

    @Environment(EnvType.CLIENT)
    public static Matrix4f particleMVMatrix = null;

    /*public static void onRenderWorldLast(RenderLevelStageEvent event) {
        if (ClientConfig.BETTER_LAYERING.get()) {
            if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_LEVEL) {
                RenderSystem.getModelViewStack().pushPose();
                RenderSystem.getModelViewStack().setIdentity();
                if (particleMVMatrix != null) RenderSystem.getModelViewStack().mulPoseMatrix(particleMVMatrix);
                RenderSystem.applyModelViewMatrix();
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                getDelayedRender().endBatch(RenderUtils.DELAYED_PARTICLE);
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                getDelayedRender().endBatch(RenderUtils.GLOWING_PARTICLE);
                RenderSystem.getModelViewStack().popPose();
                RenderSystem.applyModelViewMatrix();

                getDelayedRender().endBatch(RenderUtils.GLOWING_SPRITE);
                getDelayedRender().endBatch(RenderUtils.GLOWING);
            }
        }
    }*/

    static VertexConsumerProvider.Immediate DELAYED_RENDER = null;

    public static VertexConsumerProvider.Immediate getDelayedRender() {
        if (DELAYED_RENDER == null) {
            Map<RenderLayer, BufferBuilder> buffers = new HashMap<>();
            for (RenderLayer type : new RenderLayer[]{
                    RenderUtils.DELAYED_PARTICLE,
                    RenderUtils.GLOWING_PARTICLE,
                    RenderUtils.GLOWING,
                    RenderUtils.GLOWING_SPRITE}) {
                buffers.put(type, new BufferBuilder(FabricLoader.getInstance().isModLoaded("sodium") ? 32768 : type.getExpectedBufferSize()));
            }
            DELAYED_RENDER = VertexConsumerProvider.immediate(buffers, new BufferBuilder(128));
        }
        return DELAYED_RENDER;
    }
}
