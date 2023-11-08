package net.xstopho.wizards_reborn.client.particle;

import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class GenericParticle extends SpriteBillboardParticle {

    GenericParticleData data;
    float[] hsv1 = new float[3], hsv2 = new float[3];

    public GenericParticle(ClientWorld world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
        super(world, x, y, z, vx, vy, vz);
        this.setPos(x, y, z);
        this.data = data;
        this.velocityX = vx;
        this.velocityY = vy;
        this.velocityZ = vz;
        this.setMaxAge(data.lifetime);
        this.gravityStrength = data.gravity ? 1 : 0;
        Color.RGBtoHSB((int)(255 * Math.min(1.0f, data.r1)), (int)(255 * Math.min(1.0f, data.g1)), (int)(255 * Math.min(1.0f, data.b1)), hsv1);
        Color.RGBtoHSB((int)(255 * Math.min(1.0f, data.r2)), (int)(255 * Math.min(1.0f, data.g2)), (int)(255 * Math.min(1.0f, data.b2)), hsv2);
        updateTraits();
    }

    protected float getCoeff() {
        return (float)this.age / this.maxAge;
    }

    protected void updateTraits() {
        float coeff = getCoeff();
        scale = MathHelper.lerp(coeff, data.scale1, data.scale2);
        float h = MathHelper.lerpAngleDegrees(coeff, 360 * hsv1[0], 360 * hsv2[0]) / 360;
        float s = MathHelper.lerp(coeff, hsv1[1], hsv2[1]);
        float v = MathHelper.lerp(coeff, hsv1[2], hsv2[2]);
        int packed = Color.HSBtoRGB(h, s, v);
        float r = ColorHelper.Argb.getRed(packed) / 255.0f;
        float g = ColorHelper.Argb.getGreen(packed) / 255.0f;
        float b = ColorHelper.Argb.getBlue(packed) / 255.0f;
        setColor(r, g, b);
        setAlpha(MathHelper.lerp(coeff, data.a1, data.a2));
        prevAngle = angle;
        angle += data.spin;
    }

    @Override
    public void tick() {
        updateTraits();
        super.tick();
    }

    @Override
    public ParticleTextureSheet getType() {
        return SpriteParticleRenderType.INSTANCE;
    }
}
