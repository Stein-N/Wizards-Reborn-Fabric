package net.xstopho.wizards_reborn.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import me.shedaniel.rei.api.client.util.SpriteRenderer;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.WizardsRebornClient;
import net.xstopho.wizards_reborn.client.event.ClientTickHandler;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.awt.*;
import java.util.Random;

public class RenderUtils {

    public static float blitOffset = 0;
    private static final float ROOT_3 = (float)(Math.sqrt(3.0D) / 2.0D);

    public static final RenderPhase.Transparency ADDITIVE_TRANSPARENCY = new RenderPhase.Transparency("lightning_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });

    public static final RenderPhase.Transparency NORMAL_TRANSPARENCY = new RenderPhase.Transparency("lightning_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });

    public static final RenderLayer GLOWING_SPRITE = RenderLayer.of(
            WizardsReborn.MOD_ID + ":glowing_sprite",
            VertexFormats.POSITION_COLOR,
            VertexFormat.DrawMode.QUADS, 256, true, false,
            RenderLayer.MultiPhaseParameters.builder()
                    .writeMaskState(new RenderPhase.WriteMaskState(true, false))
                    .lightmap(new RenderPhase.Lightmap(false))
                    .transparency(ADDITIVE_TRANSPARENCY)
                    .texture(new RenderPhase.Texture(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, false, false))
                    .program(new RenderPhase.ShaderProgram(WizardsRebornClient::getGlowingSpriteShader))
                    .build(false)
    );

    public static final RenderLayer GLOWING = RenderLayer.of(
            WizardsReborn.MOD_ID + ":glowing",
            VertexFormats.POSITION_COLOR,
            VertexFormat.DrawMode.QUADS, 256, true, false,
            RenderLayer.MultiPhaseParameters.builder()
                    .writeMaskState(new RenderPhase.WriteMaskState(true, false))
                    .lightmap(new RenderPhase.Lightmap(false))
                    .transparency(ADDITIVE_TRANSPARENCY)
                    .program(new RenderPhase.ShaderProgram(WizardsRebornClient::getGlowingShader))
                    .build(false)
    );

    public static RenderLayer GLOWING_PARTICLE = RenderLayer.of(
            WizardsReborn.MOD_ID + ":glowing_particle",
            VertexFormats.POSITION_TEXTURE_COLOR_LIGHT,
            VertexFormat.DrawMode.QUADS, 256, true, false,
            RenderLayer.MultiPhaseParameters.builder()
                    .writeMaskState(new RenderPhase.WriteMaskState(true, false))
                    .lightmap(new RenderPhase.Lightmap(false))
                    .transparency(ADDITIVE_TRANSPARENCY)
                    .texture(new RenderPhase.Texture(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE, false, false))
                    .program(new RenderPhase.ShaderProgram(WizardsRebornClient::getGlowingParticleShader))
                    .build(false));

    public static RenderLayer DELAYED_PARTICLE = RenderLayer.of(
            WizardsReborn.MOD_ID + ":delayed_particle",
            VertexFormats.POSITION_TEXTURE_COLOR_LIGHT,
            VertexFormat.DrawMode.QUADS, 256, true, false,
            RenderLayer.MultiPhaseParameters.builder()
                    .writeMaskState(new RenderPhase.WriteMaskState(true, false))
                    .transparency(NORMAL_TRANSPARENCY)
                    .texture(new RenderPhase.Texture(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE, false, false))
                    .program(new RenderPhase.ShaderProgram(WizardsRebornClient::getSpriteParticleShader))
                    .build(false));

    public static void renderItemModelInGui(ItemStack stack, int x, int y, float xSize, float ySize, float zSize) {
        renderItemModelInGui(stack, x, y, xSize, ySize, zSize, 0, 0, 0);
    }

    public static void renderItemModelInGui(ItemStack stack, int x, int y, float xSize, float ySize, float zSize, float xRot, float yRot, float zRot) {
        BakedModel bakedmodel = MinecraftClient.getInstance().getItemRenderer().getModel(stack, (World)null, (LivingEntity)null, 0);

        MatrixStack posestack = RenderSystem.getModelViewStack();
        posestack.push();
        posestack.translate(x, y, (100.0F));
        posestack.translate((double) xSize / 2, (double) ySize / 2, 0.0D);
        posestack.scale(1.0F, -1.0F, 1.0F);
        posestack.scale(xSize, ySize, zSize);
        posestack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(xRot));
        posestack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yRot));
        posestack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(zRot));
        RenderSystem.applyModelViewMatrix();
        MatrixStack posestack1 = new MatrixStack();
        VertexConsumerProvider.Immediate multibuffersource$buffersource = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        boolean flag = !bakedmodel.useAmbientOcclusion();
        if (flag) {
            DiffuseLighting.disableGuiDepthLighting();
        }

        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GUI, false, posestack1, multibuffersource$buffersource, 15728880, OverlayTexture.DEFAULT_UV, bakedmodel);

        RenderSystem.disableDepthTest();
        multibuffersource$buffersource.draw();
        RenderSystem.enableDepthTest();

        if (flag) {
            DiffuseLighting.enableGuiDepthLighting();
        }

        posestack.pop();
        RenderSystem.applyModelViewMatrix();
    }

    public static Vec3d followBodyRotation(LivingEntity living) {
        Vec3d rotate = new Vec3d(0, 0, 0);
        EntityRenderer<? super LivingEntity> render = MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(living);
        if(render instanceof LivingEntityRenderer) {
            LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>> livingRenderer = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
            EntityModel<LivingEntity> entityModel = livingRenderer.getModel();
            if (entityModel instanceof BipedEntityModel<LivingEntity>) {
                BipedEntityModel<LivingEntity> bipedModel = (BipedEntityModel<LivingEntity>) entityModel;
                rotate = new Vec3d(bipedModel.body.pitch, bipedModel.body.yaw, bipedModel.body.roll);
            }
        }
        return rotate;
    }

    public static void renderBoxBlockOutline(MatrixStack matrixStack, VertexConsumerProvider bufferIn, VoxelShape voxelShape, double originX, double originY, double originZ, Color color) {
        VertexConsumer builder = bufferIn.getBuffer(RenderLayer.getLines());
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int alpha = color.getAlpha();

        voxelShape.forEachEdge((x0, y0, z0, x1, y1, z1) -> {
            builder.vertex(matrix4f, (float)(x0 + originX), (float)(y0 + originY), (float)(z0 + originZ)).color(red, green, blue, alpha).next();
            builder.vertex(matrix4f, (float)(x1 + originX), (float)(y1 + originY), (float)(z1 + originZ)).color(red, green, blue, alpha).next();
        });
    }

    public static void renderLine(MatrixStack matrixStack, VertexConsumerProvider bufferIn, double x1, double y1, double z1, double x2, double y2, double z2, Color color) {
        VertexConsumer builder = bufferIn.getBuffer(RenderLayer.getLines());
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int alpha = color.getAlpha();

        builder.vertex(matrix4f, (float) (x1), (float) (y1), (float) (z1)).color(red, green, blue, alpha).next();
        builder.vertex(matrix4f, (float) (x2), (float) (y2), (float) (z2)).color(red, green, blue, alpha).next();
    }

    public static void renderFloatingItemModelIntoGUI(ItemStack stack, int x, int y, float ticks, float ticksUp) {
        BakedModel bakedmodel = MinecraftClient.getInstance().getItemRenderer().getModel(stack, (World) null, (LivingEntity) null, 0);

        blitOffset += 50.0F;

        MatrixStack posestack = RenderSystem.getModelViewStack();
        posestack.push();
        posestack.translate(x, y, (100.0F + blitOffset));
        posestack.translate(8.0D, 8.0D, 0.0D);
        posestack.scale(1.0F, -1.0F, 1.0F);
        posestack.scale(16.0F, 16.0F, 16.0F);
        RenderSystem.getModelViewStack().multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ticks));
        posestack.translate(0.0D, Math.sin(Math.toRadians(ticksUp)) * 0.03125F, 0.0D);
        RenderSystem.applyModelViewMatrix();
        MatrixStack posestack1 = new MatrixStack();
        VertexConsumerProvider.Immediate multibuffersource$buffersource = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        boolean flag = !bakedmodel.useAmbientOcclusion();
        if (flag) {
            DiffuseLighting.disableGuiDepthLighting();

            Quaternionf ax = RotationAxis.POSITIVE_Y.rotationDegrees(ticks);

            RenderSystem.setShaderLights(new Vector3f(ax.x, ax.y, ax.z), new Vector3f(ax.x, ax.y, ax.z));

            Matrix4f matrix4f = (new Matrix4f()).scaling(1.0F, -1.0F, 1.0F).rotateY((float) Math.toRadians(ticks)).rotateX(2.3561945F);
            RenderSystem.setupLevelDiffuseLighting(new Vector3f(ax.x, ax.y, ax.z), new Vector3f(ax.x, ax.y, ax.z), matrix4f);
        } else {

            Quaternionf ax = RotationAxis.POSITIVE_Y.rotationDegrees(ticks);

            RenderSystem.setShaderLights(new Vector3f(ax.x, ax.y, ax.z), new Vector3f(ax.x, ax.y, ax.z));
        }

        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GUI, false, posestack1, multibuffersource$buffersource, 15728880, OverlayTexture.DEFAULT_UV, bakedmodel);
        RenderSystem.disableDepthTest();
        multibuffersource$buffersource.draw();
        RenderSystem.enableDepthTest();

        if (flag) {
            DiffuseLighting.enableGuiDepthLighting();
        }
        DiffuseLighting.enableGuiDepthLighting();

        posestack.pop();
        RenderSystem.applyModelViewMatrix();

        blitOffset -= 50.0F;
    }

    public static void dragon(MatrixStack mStack, VertexConsumerProvider buf, double x, double y, double z, float radius, float partialTicks, float r, float g, float b, float randomF) {
        float f5 = 0.5f;
        float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
        Random random = new Random((long) (432L + randomF));
        VertexConsumer builder = buf.getBuffer(GLOWING);
        mStack.push();
        mStack.translate(x, y, z);

        float rotation = (ClientTickHandler.ticksInGame + partialTicks) / 200;

        for(int i = 0; (float)i < (f5 + f5 * f5) / 2.0F * 60.0F; ++i) {
            mStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextFloat() * 360.0F + rotation * 90.0F));
            float f3 = random.nextFloat() * 20.0F + 5.0F + f7 * 10.0F;
            float f4 = random.nextFloat() * 2.0F + 1.0F + f7 * 2.0F;
            f3 *= 0.05f * radius;
            f4 *= 0.05f * radius;
            Matrix4f mat = mStack.peek().getPositionMatrix();
            float alpha = 1 - f7;

            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).next();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).next();
            builder.vertex(mat, -ROOT_3 * f4, f3, -0.5F * f4).color(r, g, b, 0).next();
            builder.vertex(mat, ROOT_3 * f4, f3, -0.5F * f4).color(r, g, b, 0).next();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).next();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).next();
            builder.vertex(mat, ROOT_3 * f4, f3, -0.5F * f4).color(r, g, b, 0).next();
            builder.vertex(mat, 0.0F, f3, f4).color(r, g, b, 0).next();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).next();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).next();
            builder.vertex(mat, 0.0F, f3, f4).color(r, g, b, 0).next();
            builder.vertex(mat, -ROOT_3 * f4, f3, -0.5F * f4).color(r, g, b, 0).next();
        }

        mStack.pop();
    }

    public static void ray(MatrixStack mStack, VertexConsumerProvider buf, float width, float height, float endOffset, float r, float g, float b, float a) {
        ray(mStack, buf, width, height, endOffset, r, g, b, a, r, g, b, a);
    }

    public static void ray(MatrixStack mStack, VertexConsumerProvider buf, float width, float height, float endOffset, float r1, float g1, float b1, float a1, float r2, float g2, float b2, float a2) {
        VertexConsumer builder = buf.getBuffer(GLOWING);

        Matrix4f mat = mStack.peek().getPositionMatrix();

        builder.vertex(mat, -width, width, -width).color(r1, g1, b1, a1).next();
        builder.vertex(mat, height, width * endOffset, -width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, height, -width * endOffset, -width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, -width, -width, -width).color(r1, g1, b1, a1).next();

        builder.vertex(mat, height, width * endOffset, width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, -width, width, width).color(r1, g1, b1, a1).next();
        builder.vertex(mat, -width, -width, width).color(r1, g1, b1, a1).next();
        builder.vertex(mat, height, -width * endOffset, width * endOffset).color(r2, g2, b2, a2).next();

        builder.vertex(mat, height, -width * endOffset, width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, height, -width * endOffset, -width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, height, width * endOffset, -width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, height, width * endOffset, width * endOffset).color(r2, g2, b2, a2).next();

        builder.vertex(mat, -width, -width, -width).color(r1, g1, b1, a1).next();
        builder.vertex(mat, -width, -width, width).color(r1, g1, b1, a1).next();
        builder.vertex(mat, -width, width, width).color(r1, g1, b1, a1).next();
        builder.vertex(mat, -width, width, -width).color(r1, g1, b1, a1).next();

        builder.vertex(mat, -width, width, width).color(r1, g1, b1, a1).next();
        builder.vertex(mat, height, width * endOffset, width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, height, width * endOffset, -width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, -width, width, -width).color(r1, g1, b1, a1).next();

        builder.vertex(mat, -width, -width, -width).color(r1, g1, b1, a1).next();
        builder.vertex(mat, height, -width * endOffset, -width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, height, -width * endOffset, width * endOffset).color(r2, g2, b2, a2).next();
        builder.vertex(mat, -width, -width, width).color(r1, g1, b1, a1).next();
    }

    public static void litQuad(MatrixStack mStack, VertexConsumerProvider buf, float x, float y, float width, float height, float r, float g, float b, float a) {
        VertexConsumer builder = buf.getBuffer(GLOWING);

        Matrix4f mat = mStack.peek().getPositionMatrix();
        builder.vertex(mat, x, y + height, 0).color(r, g, b, a).next();
        builder.vertex(mat, x + width, y + height, 0).color(r, g, b, a).next();
        builder.vertex(mat, x + width, y, 0).color(r, g, b, a).next();
        builder.vertex(mat, x, y, 0).color(r, g, b, a).next();
    }

    public static void quadCrystallType(MatrixStack mStack, VertexConsumerProvider buf, float x, float y, float width, float height, float r, float g, float b, float a) {
        VertexConsumer builder = buf.getBuffer(GLOWING);

        Matrix4f mat = mStack.peek().getPositionMatrix();
        builder.vertex(mat, x, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x + width, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x + width, y, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x, y, 0).color(r, g, b, 0).next();

        builder.vertex(mat, x, y + height, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y + height, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x, y + (height / 2), 0).color(r, g, b, a).next();


        builder.vertex(mat, x - (width / 8), y + (height / 2), 0).color(r, g, b, 0).next();
        builder.vertex(mat, x, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x, y, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x - (width / 8), y, 0).color(r, g, b, 0).next();

        builder.vertex(mat, x, y + height, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x - (width / 8), y + (height / 2), 0).color(r, g, b, 0).next();
        builder.vertex(mat, x - (width / 8), y + height, 0).color(r, g, b, 0).next();


        builder.vertex(mat, x + width + (width / 8), y + (height / 2), 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width + (width / 8), y, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y + (height / 2), 0).color(r, g, b, a).next();

        builder.vertex(mat, x + width + (width / 8), y + (height / 2), 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x + width, y + height, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width + (width / 8), y + height, 0).color(r, g, b, 0).next();
    }

    public static void quadMonogram(MatrixStack mStack, VertexConsumerProvider buf, float x, float y, float width, float height, float r, float g, float b, float a) {
        VertexConsumer builder = buf.getBuffer(GLOWING);

        Matrix4f mat = mStack.peek().getPositionMatrix();
        builder.vertex(mat, x, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x + width, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x + width, y, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x, y, 0).color(r, g, b, 0).next();

        builder.vertex(mat, x, y + height, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y + height, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x, y + (height / 2), 0).color(r, g, b, a).next();


        builder.vertex(mat, x - (width / 8), y + (height / 2), 0).color(r, g, b, 0).next();
        builder.vertex(mat, x, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x, y, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x - (width / 8), y, 0).color(r, g, b, 0).next();

        builder.vertex(mat, x, y + height, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x - (width / 8), y + (height / 2), 0).color(r, g, b, 0).next();
        builder.vertex(mat, x - (width / 8), y + height, 0).color(r, g, b, 0).next();


        builder.vertex(mat, x + width + (width / 8), y + (height / 2), 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width + (width / 8), y, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y + (height / 2), 0).color(r, g, b, a).next();

        builder.vertex(mat, x + width + (width / 8), y + (height / 2), 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width, y + (height / 2), 0).color(r, g, b, a).next();
        builder.vertex(mat, x + width, y + height, 0).color(r, g, b, 0).next();
        builder.vertex(mat, x + width + (width / 8), y + height, 0).color(r, g, b, 0).next();
    }
}
