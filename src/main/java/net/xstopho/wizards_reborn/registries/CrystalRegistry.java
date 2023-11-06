package net.xstopho.wizards_reborn.registries;

import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.api.crystal.CrystalStat;
import net.xstopho.wizards_reborn.api.crystal.CrystalType;
import net.xstopho.wizards_reborn.api.crystal.Crystals;
import net.xstopho.wizards_reborn.api.crystal.PolishingType;
import net.xstopho.wizards_reborn.common.crystal.*;

public class CrystalRegistry {

    public static final CrystalStat FOCUS_CRYSTAL_STAT = new CrystalStat(WizardsReborn.MOD_ID + ":focus", 3);
    public static final CrystalStat BALANCE_CRYSTAL_STAT = new CrystalStat(WizardsReborn.MOD_ID + ":balance", 3);
    public static final CrystalStat ABSORPTION_CRYSTAL_STAT = new CrystalStat(WizardsReborn.MOD_ID + ":absorption", 3);
    public static final CrystalStat RESONANCE_CRYSTAL_STAT = new CrystalStat(WizardsReborn.MOD_ID + ":resonance", 3);

    public static final CrystalType EARTH_CRYSTAL_TYPE = new EarthCrystalType(WizardsReborn.MOD_ID + ":earth");
    public static final CrystalType WATER_CRYSTAL_TYPE = new WaterCrystalType(WizardsReborn.MOD_ID + ":water");
    public static final CrystalType AIR_CRYSTAL_TYPE = new AirCrystalType(WizardsReborn.MOD_ID + ":air");
    public static final CrystalType FIRE_CRYSTAL_TYPE = new FireCrystalType(WizardsReborn.MOD_ID + ":fire");
    public static final CrystalType VOID_CRYSTAL_TYPE = new VoidCrystalType(WizardsReborn.MOD_ID + ":void");

    public static final PolishingType CRYSTAL_POLISHING_TYPE  = new CrystalPolishingType(WizardsReborn.MOD_ID + ":crystal");
    public static final PolishingType FACETED_POLISHING_TYPE  = new FacetedPolishingType(WizardsReborn.MOD_ID + ":faceted");
    public static final PolishingType ADVANCED_POLISHING_TYPE  = new AdvancedPolishingType(WizardsReborn.MOD_ID + ":advanced");
    public static final PolishingType MASTERFUL_POLISHING_TYPE  = new MasterfulPolishingType(WizardsReborn.MOD_ID + ":masterful");
    public static final PolishingType PURE_POLISHING_TYPE  = new PurePolishingType(WizardsReborn.MOD_ID + ":pure");

    public static void init() {
        Crystals.registerPolishing(CRYSTAL_POLISHING_TYPE);
        Crystals.registerPolishing(FACETED_POLISHING_TYPE);
        Crystals.registerPolishing(ADVANCED_POLISHING_TYPE);
        Crystals.registerPolishing(MASTERFUL_POLISHING_TYPE);
        Crystals.registerPolishing(PURE_POLISHING_TYPE);

        Crystals.registerType(EARTH_CRYSTAL_TYPE);
        Crystals.registerType(WATER_CRYSTAL_TYPE);
        Crystals.registerType(AIR_CRYSTAL_TYPE);
        Crystals.registerType(FIRE_CRYSTAL_TYPE);
        Crystals.registerType(VOID_CRYSTAL_TYPE);
    }
}
