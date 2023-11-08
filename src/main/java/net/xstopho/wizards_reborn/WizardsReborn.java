package net.xstopho.wizards_reborn;

import net.fabricmc.api.ModInitializer;
import net.xstopho.wizards_reborn.registries.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WizardsReborn implements ModInitializer {
	public static final String MOD_ID = "wizards_reborn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		WorldGenRegistry.init();
		EntityRegistry.init();
		CrystalRegistry.init();

		BlockRegistry.init();
		ItemRegistry.init();
		ItemGroupRegistry.init();

		ParticleRegistry.init();

		LOGGER.info("Hello Fabric world!");
	}
}