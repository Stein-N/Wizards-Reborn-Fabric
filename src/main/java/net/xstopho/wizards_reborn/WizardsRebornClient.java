package net.xstopho.wizards_reborn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.xstopho.wizards_reborn.registries.BlockRegistry;

public class WizardsRebornClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				BlockRegistry.ARCANE_WOOD_DOOR, BlockRegistry.ARCANE_WOOD_SAPLING, BlockRegistry.POTTED_ARCANE_WOOD_SAPLING,
				BlockRegistry.ARCANE_LINEN);
	}
}