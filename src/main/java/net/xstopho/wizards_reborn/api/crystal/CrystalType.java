package net.xstopho.wizards_reborn.api.crystal;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.registries.CrystalRegistry;

import java.awt.*;
import java.util.ArrayList;

public class CrystalType {
    public ArrayList<CrystalStat> stats = new ArrayList<>();
    public String id;

    public CrystalType(String id) {
        this.id = id;

        addStat(CrystalRegistry.FOCUS_CRYSTAL_STAT);
        addStat(CrystalRegistry.BALANCE_CRYSTAL_STAT);
        addStat(CrystalRegistry.ABSORPTION_CRYSTAL_STAT);
        addStat(CrystalRegistry.RESONANCE_CRYSTAL_STAT);
    }

    public Color getColor() {
        return new Color(255, 255, 255);
    }

    public Identifier getMiniIcon() {
        return new Identifier(WizardsReborn.MOD_ID, "textures/gui/spell/empty_mini_icon");
    }

    public Identifier getIcon() {
        return new Identifier(WizardsReborn.MOD_ID, "textures/gui/spell/empty_icon");
    }

    public ItemStack getFracturedCrystal() {
        return ItemStack.EMPTY;
    }

    public ItemStack getCrystal() {
        return ItemStack.EMPTY;
    }

    public void addStat(CrystalStat stat) {
        stats.add(stat);
    }

    public ArrayList<CrystalStat> getStats() {
        return stats;
    }

    public String getId() {
        return id;
    }

    public String getTranslatedName() {
        return getTranslatedName(id);
    }

    public static String getTranslatedName(String id) {
        int i = id.indexOf(":");
        String modId = id.substring(0, i);
        String crystalTpyeId = id.substring(i + 1);
        return "crystal_type."  + modId + "." + crystalTpyeId;
    }
}
