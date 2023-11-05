package net.xstopho.wizards_reborn.common.crystal;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.api.crystal.CrystalType;
import net.xstopho.wizards_reborn.registries.BlockRegistry;
import net.xstopho.wizards_reborn.registries.ItemRegistry;

import java.awt.*;

public class WaterCrystalType extends CrystalType {
    public WaterCrystalType(String id) {
        super(id);
    }

    @Override
    public Color getColor() {
        return new Color(152, 180, 223);
    }

    @Override
    public Identifier getMiniIcon() {
        return new Identifier(WizardsReborn.MOD_ID, "textures/gui/spell/water_mini_icon");
    }

    @Override
    public Identifier getIcon() {
        return new Identifier(WizardsReborn.MOD_ID, "textures/gui/spell/water_icon");
    }

    @Override
    public ItemStack getFracturedCrystal() {
        return ItemRegistry.FRACTURED_WATER_CRYSTAL.getDefaultStack();
    }

    @Override
    public ItemStack getCrystal() {
        return ItemRegistry.WATER_CRYSTAL.getDefaultStack();
    }
}
