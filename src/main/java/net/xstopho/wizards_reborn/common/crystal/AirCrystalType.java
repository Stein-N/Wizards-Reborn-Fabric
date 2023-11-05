package net.xstopho.wizards_reborn.common.crystal;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.api.crystal.CrystalType;
import net.xstopho.wizards_reborn.registries.BlockRegistry;
import net.xstopho.wizards_reborn.registries.ItemRegistry;

import java.awt.*;

public class AirCrystalType extends CrystalType {
    public AirCrystalType(String id) {
        super(id);
    }

    @Override
    public Color getColor() {
        return new Color(230, 173, 134);
    }

    @Override
    public Identifier getMiniIcon() {
        return new Identifier(WizardsReborn.MOD_ID, "textures/gui/spell/air_mini_icon");
    }

    @Override
    public Identifier getIcon() {
        return new Identifier(WizardsReborn.MOD_ID, "textures/gui/spell/air_icon");
    }

    @Override
    public ItemStack getFracturedCrystal() {
        return ItemRegistry.FRACTURED_AIR_CRYSTAL.getDefaultStack();
    }

    @Override
    public ItemStack getCrystal() {
        return ItemRegistry.AIR_CRYSTAL.getDefaultStack();
    }
}
