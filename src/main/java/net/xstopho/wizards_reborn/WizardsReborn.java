package net.xstopho.wizards_reborn;

import net.fabricmc.api.ModInitializer;
import net.xstopho.wizards_reborn.utils.InitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WizardsReborn implements ModInitializer {
	public static final String MOD_ID = "wizards_reborn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		InitUtils.priorityInit();
		InitUtils.normalInit();


		LOGGER.info("Initialisation Finished!");
	}
}