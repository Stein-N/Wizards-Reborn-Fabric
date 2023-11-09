package net.xstopho.wizards_reborn.common.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.xstopho.wizards_reborn.client.particle.Particles;
import net.xstopho.wizards_reborn.registries.ParticleRegistry;

import java.awt.*;
import java.util.Random;

public class ArcaneLumosBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(6, 6, 6, 10, 10, 10);
    private static Random random = new Random();

    public enum Colors {
        WHITE,
        ORANGE,
        MAGENTA,
        LIGHT_BLUE,
        YELLOW,
        LIME,
        PINK,
        GRAY,
        LIGHT_GRAY,
        CYAN,
        PURPLE,
        BLUE,
        BROWN,
        GREEN,
        RED,
        BLACK,
        RAINBOW,
        COSMIC
    }

    public Colors color;

    public ArcaneLumosBlock(Colors color, Settings settings) {
        super(settings);
        this.color = color;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        Color color = getColor(this.color);
        float r = color.getRed() / 255f;
        float g = color.getGreen() / 255f;
        float b = color.getBlue() / 255f;

        Particles.create(ParticleRegistry.WISP_PARTICLE)
                .addVelocity(((random.nextDouble() - 0.5D) / 30), ((random.nextDouble() - 0.5D) / 30), ((random.nextDouble() - 0.5D) / 30))
                .setAlpha(0.5f, 0).setScale(0.3f, 0)
                .setColor(r, g, b)
                .setLifetime(20)
                .spawn(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
        if (random.nextFloat() < 0.5) {
            Particles.create(ParticleRegistry.SPARKLE_PARTICLE)
                    .addVelocity(((random.nextDouble() - 0.5D) / 30), ((random.nextDouble() - 0.5D) / 30), ((random.nextDouble() - 0.5D) / 30))
                    .setAlpha(0.75f, 0).setScale(0.1f, 0)
                    .setColor(r, g, b)
                    .setLifetime(30)
                    .setSpin((0.5f * (float) ((random.nextDouble() - 0.5D) * 2)))
                    .spawn(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
        }

        if (this.color == Colors.COSMIC) {
            if (random.nextFloat() < 0.3) {
                Particles.create(ParticleRegistry.SPARKLE_PARTICLE)
                        .addVelocity(((random.nextDouble() - 0.5D) / 50), ((random.nextDouble() - 0.5D) / 50), ((random.nextDouble() - 0.5D) / 50))
                        .setAlpha(0.75f, 0).setScale(0.1f, 0)
                        .setColor(r, g, b)
                        .setLifetime(5)
                        .spawn(world, pos.getX() + 0.5F + ((random.nextDouble() - 0.5D) / 2), pos.getY() + 0.5F + ((random.nextDouble() - 0.5D) / 2), pos.getZ() + 0.5F + ((random.nextDouble() - 0.5D) / 2));
            }
            if (random.nextFloat() < 0.3) {
                Particles.create(ParticleRegistry.SPARKLE_PARTICLE)
                        .addVelocity(((random.nextDouble() - 0.5D) / 50), ((random.nextDouble() - 0.5D) / 50), ((random.nextDouble() - 0.5D) / 50))
                        .setAlpha(0.75f, 0).setScale(0.1f, 0)
                        .setColor(1f, 1f, 1f)
                        .setLifetime(5)
                        .spawn(world, pos.getX() + 0.5F + ((random.nextDouble() - 0.5D) / 2), pos.getY() + 0.5F + ((random.nextDouble() - 0.5D) / 2), pos.getZ() + 0.5F + ((random.nextDouble() - 0.5D) / 2));
            }
        }
    }


    public static Color getColor(Colors color) {
        switch (color) {
            case WHITE:
                return getDyeColor(DyeColor.WHITE);
            case ORANGE:
                return getDyeColor(DyeColor.ORANGE);
            case MAGENTA:
                return getDyeColor(DyeColor.MAGENTA);
            case LIGHT_BLUE:
                return getDyeColor(DyeColor.LIGHT_BLUE);
            case YELLOW:
                return getDyeColor(DyeColor.YELLOW);
            case LIME:
                return getDyeColor(DyeColor.LIME);
            case PINK:
                return getDyeColor(DyeColor.PINK);
            case GRAY:
                return getDyeColor(DyeColor.GRAY);
            case LIGHT_GRAY:
                return getDyeColor(DyeColor.LIGHT_GRAY);
            case CYAN:
                return getDyeColor(DyeColor.CYAN);
            case PURPLE:
                return getDyeColor(DyeColor.PURPLE);
            case BLUE:
                return getDyeColor(DyeColor.BLUE);
            case BROWN:
                return getDyeColor(DyeColor.BROWN);
            case GREEN:
                return getDyeColor(DyeColor.GREEN);
            case RED:
                return getDyeColor(DyeColor.RED);
            case BLACK:
                return getDyeColor(DyeColor.BLACK);
            case RAINBOW:
                return new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
            case COSMIC:
                return new Color(254, 181, 178);
        }

        return getDyeColor(DyeColor.WHITE);
    }

    public static Color getDyeColor(DyeColor color) {
        return new Color(color.getMapColor().color);
    }
}
