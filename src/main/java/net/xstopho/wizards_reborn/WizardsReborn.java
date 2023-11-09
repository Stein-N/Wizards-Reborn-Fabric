package net.xstopho.wizards_reborn;

import net.fabricmc.api.ModInitializer;
import net.xstopho.wizards_reborn.registries.*;
import net.xstopho.wizards_reborn.utils.initUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WizardsReborn implements ModInitializer {
	public static final String MOD_ID = "wizards_reborn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//TODO Port RenderUtils and WorldRenderHandler

	@Override
	public void onInitialize() {
		initUtil.priorityInit();
		initUtil.normalInit();

		int e = 3;
		LOGGER.info("e= " + e);
		LOGGER.info("e *= e =  " + String.valueOf(e *= e));

		LOGGER.info("Initialisation Finished!");
	}
}