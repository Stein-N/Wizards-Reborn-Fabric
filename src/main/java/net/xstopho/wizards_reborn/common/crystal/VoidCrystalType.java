package net.xstopho.wizards_reborn.common.crystal;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.api.crystal.CrystalType;
import net.xstopho.wizards_reborn.registries.BlockRegistry;
import net.xstopho.wizards_reborn.registries.ItemRegistry;

import java.awt.*;

public class VoidCrystalType extends CrystalType {
    public VoidCrystalType(String id) {
        super(id);
    }

    @Override
    public Color getColor() {
        return new Color(175, 140, 194);
    }

    @Override
    public Identifier getMiniIcon() {
        return new Identifier(WizardsReborn.MOD_ID, "textures/gui/spell/void_mini_icon");
    }

    @Override
    public Identifier getIcon() {
        return new Identifier(WizardsReborn.MOD_ID, "textures/gui/spell/void_icon");
    }

    @Override
    public ItemStack getFracturedCrystal() {
        return ItemRegistry.FRACTURED_VOID_CRYSTAL.getDefaultStack();
    }

    @Override
    public ItemStack getCrystal() {
        return ItemRegistry.VOID_CRYSTAL.getDefaultStack();
    }
}
