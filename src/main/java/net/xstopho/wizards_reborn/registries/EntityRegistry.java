package net.xstopho.wizards_reborn.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.common.entities.CrystalBlockEntity;
import net.xstopho.wizards_reborn.common.entities.CrystalGrowthBlockEntity;

public class EntityRegistry {

    public static final BlockEntityType<CrystalGrowthBlockEntity> CRYSTAL_GROWTH_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, new Identifier(WizardsReborn.MOD_ID, "crystal_growth_block_entity"),
            FabricBlockEntityTypeBuilder.create(CrystalGrowthBlockEntity::new, BlockRegistry.EARTH_CRYSTAL_GROWTH,
                    BlockRegistry.WATER_CRYSTAL_GROWTH, BlockRegistry.AIR_CRYSTAL_GROWTH, BlockRegistry.FIRE_CRYSTAL_GROWTH,
                    BlockRegistry.VOID_CRYSTAL_GROWTH).build(null)
    );

    public static final BlockEntityType<CrystalBlockEntity> CRYSTAL_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(WizardsReborn.MOD_ID, "crystal"), FabricBlockEntityTypeBuilder.create(CrystalBlockEntity::new,
            BlockRegistry.EARTH_CRYSTAL_BLOCK, BlockRegistry.WATER_CRYSTAL_BLOCK, BlockRegistry.AIR_CRYSTAL_BLOCK,
                    BlockRegistry.FIRE_CRYSTAL_BLOCK, BlockRegistry.VOID_CRYSTAL_GROWTH).build(null));

    public static void init() {

    }
}
