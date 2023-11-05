package net.xstopho.wizards_reborn.common.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.xstopho.wizards_reborn.registries.EntityRegistry;

public class CrystalGrowthBlockEntity extends BlockEntity {
    public CrystalGrowthBlockEntity(BlockPos pos, BlockState state) {
        super(EntityRegistry.CRYSTAL_GROWTH_BLOCK_ENTITY, pos, state);
    }
}
