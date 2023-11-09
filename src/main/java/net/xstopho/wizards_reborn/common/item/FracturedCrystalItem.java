package net.xstopho.wizards_reborn.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.xstopho.wizards_reborn.api.crystal.CrystalStat;
import net.xstopho.wizards_reborn.api.crystal.CrystalType;
import net.xstopho.wizards_reborn.api.crystal.CrystalUtils;
import net.xstopho.wizards_reborn.client.particle.Particles;
import net.xstopho.wizards_reborn.registries.ParticleRegistry;
import net.xstopho.wizards_reborn.utils.ColorUtils;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class FracturedCrystalItem extends Item implements IParticleItem {
    private static Random random = new Random();
    public CrystalType type;

    public FracturedCrystalItem(CrystalType type, Settings settings) {
        super(settings);
        this.type = type;
    }

    public CrystalType getType() {
        return type;
    }

    public static int getStatLevel(ItemStack stack, CrystalStat stat) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int statlevel = 0;
        if (nbt.contains(stat.getID())) {
            statlevel = nbt.getInt(stat.getID());
        }
        return statlevel;
    }

    public static ItemStack creativeTabRandomStats(Item item) {
        ItemStack stack = item.getDefaultStack();
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putBoolean("random_stats", true);
        stack.setNbt(nbt);
        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            NbtCompound nbt = stack.getOrCreateNbt();
            if (nbt.contains("random_stats")) {
                nbt.remove("random_stats");
                CrystalUtils.createCrystalItemStats(stack, type, world, 4);
                stack.setNbt(nbt);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        CrystalType type = getType();
        Color color = type.getColor();
        for (CrystalStat stat : type.getStats()) {
            int statlevel = getStatLevel(stack, stat);
            int red = (int) MathHelper.lerp((float) statlevel / stat.getMaxLevel(), Color.GRAY.getRed(), color.getRed());
            int green = (int) MathHelper.lerp((float) statlevel / stat.getMaxLevel(), Color.GRAY.getGreen(), color.getGreen());
            int blue = (int) MathHelper.lerp((float) statlevel / stat.getMaxLevel(), Color.GRAY.getBlue(), color.getBlue());

            int packColor = ColorUtils.packColor(255, red, green, blue);
            tooltip.add(Text.translatable(stat.getTranslatedName()).append(": " + statlevel).setStyle(Style.EMPTY.withColor(packColor)));
        }
    }

    @Override
    public void addParticles(World world, ItemEntity entity) {
        CrystalType type = getType();

        if (random.nextFloat() < 0.01) {
            Color color = type.getColor();
            float r = color.getRed() / 255f;
            float g = color.getGreen() / 255f;
            float b = color.getBlue() / 255f;

            Particles.create(ParticleRegistry.SPARKLE_PARTICLE)
                    .addVelocity(((random.nextDouble() - 0.5D) / 50), ((random.nextDouble() - 0.5D) / 50), ((random.nextDouble() - 0.5D) / 50))
                    .setAlpha(0.5f, 0).setScale(0.1f, 0)
                    .setColor(r, g, b)
                    .setLifetime(30)
                    .setSpin((0.5f * (float) ((random.nextDouble() - 0.5D) * 2)))
                    .spawn(world, entity.getX() + ((random.nextDouble() - 0.5D) * 0.25), entity.getY() + 0.25F + ((random.nextDouble() - 0.5D) * 0.25), entity.getZ() + ((random.nextDouble() - 0.5D) * 0.25));
        }
    }
}
