package net.xstopho.wizards_reborn.registries;

import com.mojang.serialization.Codec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.common.world.tree.ArcaneWoodTrunkPlacer;
import net.xstopho.wizards_reborn.common.world.tree.SupplierBlockStateProvider;

public class WorldGenRegistry {
    public static RegistryKey<ConfiguredFeature<?, ?>> ARCANE_WOOD_TREE = registerConfiguredFeature("arcane_wood");
    public static RegistryKey<ConfiguredFeature<?, ?>> FANCY_ARCANE_WOOD_TREE = registerConfiguredFeature("fancy_arcane_wood");
    public static RegistryKey<ConfiguredFeature<?, ?>> TALL_MOR = registerConfiguredFeature("tall_mor");
    public static RegistryKey<ConfiguredFeature<?, ?>> TALL_ELDER_MOR = registerConfiguredFeature("tall_elder_mor");
    public static RegistryKey<ConfiguredFeature<?, ?>> HUGE_MOR = registerConfiguredFeature("huge_mor");
    public static RegistryKey<ConfiguredFeature<?, ?>> HUGE_ELDER_MOR = registerConfiguredFeature("huge_elder_mor");

    public static final BlockStateProviderType<SupplierBlockStateProvider> AN_STATEPROVIDER = registerBlockStateProviderType("an_stateprovider", SupplierBlockStateProvider.CODEC);

    public static final TrunkPlacerType<ArcaneWoodTrunkPlacer> ARCANE_WOOD_TRUNK_PLACER = registerTrunkPlacer("arcane_wood_trunk_placer", ArcaneWoodTrunkPlacer.CODEC);

    private static RegistryKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(WizardsReborn.MOD_ID, id));
    }

    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String id, Codec<P> codec) {
        return Registry.register(Registries.TRUNK_PLACER_TYPE, new Identifier(WizardsReborn.MOD_ID, id), new TrunkPlacerType<P>(codec));
    }

    private static <P extends BlockStateProvider> BlockStateProviderType<P> registerBlockStateProviderType(String id, Codec<P> codec) {
        return Registry.register(Registries.BLOCK_STATE_PROVIDER_TYPE, new Identifier(WizardsReborn.MOD_ID, id), new BlockStateProviderType<P>(codec));
    }

    public static void init() {

    }
}
