package net.xstopho.wizards_reborn.common.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.registries.WorldGenRegistry;

public class SupplierBlockStateProvider extends AbstractSupplierBlockStateProvider {

    public SupplierBlockStateProvider(String path) {
        this(new Identifier(WizardsReborn.MOD_ID, path));
    }

    public SupplierBlockStateProvider(Identifier path) {
        super(path);
    }

    public static final Codec<SupplierBlockStateProvider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("key").forGetter(d -> d.key.getPath())).apply(instance, SupplierBlockStateProvider::new));

    @Override
    protected BlockStateProviderType<?> getType() {
        return WorldGenRegistry.AN_STATEPROVIDER;
    }
}
