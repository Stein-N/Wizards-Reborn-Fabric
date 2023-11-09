package net.xstopho.wizards_reborn.api.wissen;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class WissenItemUtils {
    public static int getWissen(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt.getInt("wissen");
    }

    public static void setWissen(ItemStack stack, int wissen) {
        NbtCompound nbt = stack.getNbt();
        nbt.putInt("wissen", wissen);
    }

    public static void addWissen(ItemStack stack, int wissen, int max_wissen) {
        NbtCompound nbt = stack.getNbt();
        nbt.putInt("wissen", nbt.getInt("wissen") + wissen);
        if (max_wissen < nbt.getInt("wissen")) {
            nbt.putInt("wissen", max_wissen);
        }
    }

    public static int addWissenRemain(ItemStack stack, int wissen) {
        int wissen_remain = 0;
        NbtCompound nbt = stack.getNbt();
        int max_wissen = nbt.getInt("max_wissen");
        nbt.putInt("wissen", nbt.getInt("wissen") + wissen);
        if (max_wissen < nbt.getInt("wissen")) {
            wissen_remain = nbt.getInt("wissen") - max_wissen;
            nbt.putInt("wissen", max_wissen);
        }
        return wissen_remain;
    }

    public static void removeWissen(ItemStack stack, int wissen) {
        NbtCompound nbt = stack.getNbt();
        nbt.putInt("wissen", nbt.getInt("wissen") - wissen);
        if (nbt.getInt("wissen") < 0) {
            nbt.putInt("wissen", 0);
        }
    }

    public static int getAddWissenRemain(ItemStack stack, int wissen, int max_wissen) {
        int wissen_remain = 0;
        NbtCompound nbt = stack.getNbt();
        if (max_wissen < nbt.getInt("wissen") + wissen) {
            wissen_remain = (nbt.getInt("wissen") + wissen) - max_wissen;
        }
        return wissen_remain;
    }

    public static int getRemoveWissenRemain(ItemStack stack, int wissen) {
        int wissen_remain = 0;
        NbtCompound nbt = stack.getNbt();
        if (0 < nbt.getInt("wissen") - wissen) {
            wissen_remain = -(nbt.getInt("wissen") - wissen);
        }
        return wissen_remain;
    }

    public static boolean canAddWissen(ItemStack stack, int wissen, int max_wissen) {
        NbtCompound nbt = stack.getNbt();
        return (max_wissen >= nbt.getInt("wissen") + wissen);
    }

    public static boolean canRemoveWissen(ItemStack stack, int wissen) {
        NbtCompound nbt = stack.getNbt();
        return (0 <= nbt.getInt("wissen") - wissen);
    }

    public static void existWissen(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("wissen")) {
            nbt.putInt("wissen", 0);
        }
    }
}
