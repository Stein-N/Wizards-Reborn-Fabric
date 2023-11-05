package net.xstopho.wizards_reborn.utils;


import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class PacketUtils {
    public static void SUpdateTileEntityPacket(BlockEntity tile) {
        if (tile.getWorld() instanceof ServerWorld) {
            Packet<?> packet = tile.toUpdatePacket();
            if (packet != null) {
                BlockPos pos = tile.getPos();
                ((ServerChunkManager) tile.getWorld().getChunkManager()).threadedAnvilChunkStorage
                        .getPlayersWatchingChunk(new ChunkPos(pos), false)
                        .forEach(e -> e.networkHandler.sendPacket(packet));
            }
        }
    }
}
