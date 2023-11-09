package net.xstopho.wizards_reborn.utils;

import net.xstopho.wizards_reborn.registries.*;

public class initUtil {

    public static void priorityInit() {
        WorldGenRegistry.init();
        AttributeRegistry.init();
        EntityRegistry.init();
        CrystalRegistry.init();
        ParticleRegistry.init();
    }

    public static void normalInit() {
        BlockRegistry.init();
        ItemRegistry.init();
        ItemGroupRegistry.init();
    }
}
