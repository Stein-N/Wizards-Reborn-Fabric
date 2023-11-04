package net.xstopho.wizards_reborn.common.world.tree;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;

import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractSupplierBlockStateProvider extends BlockStateProvider {

    public static <T extends AbstractSupplierBlockStateProvider> Codec<T> codecBuilder(Function<Identifier, T> builder) {
        return Identifier.CODEC.fieldOf("key").xmap(builder, (provider) -> provider.key).codec();
    }

    protected final Identifier key;
    protected BlockState state = null;

    public AbstractSupplierBlockStateProvider(String namespace, String path) {
        this(new Identifier(namespace, path));
    }

    public AbstractSupplierBlockStateProvider(Identifier key) {
        this.key = key;
    }

    @Override
    protected abstract BlockStateProviderType<?> getType();

    @Override
    public BlockState get(Random random, BlockPos pos) {
        if (state == null) {
            Block block = Registries.BLOCK.get(key);
            state = Objects.requireNonNullElse(block, Blocks.AIR).getDefaultState();
        }
        return state;
    }
}
