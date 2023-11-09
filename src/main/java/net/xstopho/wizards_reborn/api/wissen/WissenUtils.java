package net.xstopho.wizards_reborn.api.wissen;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.xstopho.wizards_reborn.registries.AttributeRegistry;

public class WissenUtils {
    public static int getAddWissenRemain(int current_wissen, int wissen, int max_wissen) {
        int wissen_remain = 0;
        if (max_wissen < (current_wissen + wissen)) {
            wissen_remain = (current_wissen + wissen) - max_wissen;
        }
        return wissen_remain;
    }

    public static int getRemoveWissenRemain(int current_wissen, int wissen) {
        int wissen_remain = 0;
        if (0 > (current_wissen - wissen)) {
            wissen_remain = -(current_wissen - wissen);
        }
        return wissen_remain;
    }

    public static boolean canAddWissen(int current_wissen, int wissen, int max_wissen) {
        return (max_wissen >= (current_wissen + wissen));
    }

    public static boolean canRemoveWissen(int current_wissen, int wissen) {
        return (0 <= (current_wissen - wissen));
    }

    public static float getWissenCostModifierWithSale(PlayerEntity player) {
        EntityAttributeInstance attr = player.getAttributeInstance(AttributeRegistry.WISSEN_SALE);
        return (float) (attr.getValue() / 100d);
    }
}
