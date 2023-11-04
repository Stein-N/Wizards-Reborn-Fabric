package net.xstopho.wizards_reborn;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.ModInitializer;

import net.xstopho.wizards_reborn.registries.BlockRegistry;
import net.xstopho.wizards_reborn.registries.ItemGroupRegistry;
import net.xstopho.wizards_reborn.registries.ItemRegistry;
import net.xstopho.wizards_reborn.registries.WorldGenRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WizardsReborn implements ModInitializer {
	public static final String MOD_ID = "wizards_reborn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		WorldGenRegistry.init();

		BlockRegistry.init();
		ItemRegistry.init();
		ItemGroupRegistry.init();

		LOGGER.info("Hello Fabric world!");
	}
}