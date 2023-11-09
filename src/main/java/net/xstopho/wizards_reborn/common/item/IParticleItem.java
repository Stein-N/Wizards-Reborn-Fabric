package net.xstopho.wizards_reborn.common.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;

public interface IParticleItem {
    void addParticles(World world, ItemEntity entity);
}
