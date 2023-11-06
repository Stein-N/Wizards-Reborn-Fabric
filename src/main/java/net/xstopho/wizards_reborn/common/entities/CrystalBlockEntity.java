package net.xstopho.wizards_reborn.common.entities;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.xstopho.wizards_reborn.registries.EntityRegistry;
import org.jetbrains.annotations.Nullable;

public class CrystalBlockEntity extends BlockSimpleInventory {

    public CrystalBlockEntity(BlockPos pos, BlockState state) {
        super(EntityRegistry.CRYSTAL_BLOCK_ENTITY, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    protected SimpleInventory createInventory() {
        return new SimpleInventory(1) {
            @Override
            public int getMaxCountPerStack() {
                return 1;
            }
        };
    }
}
