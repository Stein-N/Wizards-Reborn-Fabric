package net.xstopho.wizards_reborn.common.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.api.crystal.CrystalType;
import net.xstopho.wizards_reborn.api.crystal.PolishingType;
import net.xstopho.wizards_reborn.client.particle.Particles;
import net.xstopho.wizards_reborn.client.particle.SparkleParticle;
import net.xstopho.wizards_reborn.common.entities.BlockSimpleInventory;
import net.xstopho.wizards_reborn.common.entities.CrystalBlockEntity;
import net.xstopho.wizards_reborn.registries.CrystalRegistry;
import net.xstopho.wizards_reborn.registries.ParticleRegistry;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Random;
import java.util.stream.Stream;

public class CrystalBlock extends Block implements BlockEntityProvider, Waterloggable {
    public PolishingType polishing;
    public CrystalType type;
    private static final Random rnd = new Random();

    private static final VoxelShape FACETED_SHAPE = Block.createCuboidShape(5, 0, 5, 11, 9, 11);
    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(5, 0, 5, 11, 9, 11),
            Block.createCuboidShape(3, 0, 4, 6, 3, 7),
            Block.createCuboidShape(9, 0, 3, 12, 3, 6),
            Block.createCuboidShape(9, 0, 10, 12, 3, 13),
            Block.createCuboidShape(4, 0, 9, 7, 3, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    public CrystalBlock(CrystalType type, PolishingType polishing, Settings settings) {
        super(settings);
        this.type = type;
        this.polishing = polishing;
        setDefaultState(getDefaultState().with(Properties.WATERLOGGED, false));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (polishing == CrystalRegistry.CRYSTAL_POLISHING_TYPE) return SHAPE;
        return FACETED_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        FluidState fluidState = context.getWorld().getFluidState(context.getBlockPos());
        return getDefaultState().with(Properties.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : Fluids.EMPTY.getDefaultState();
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(Properties.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        super.onSyncedBlockEvent(state, world, pos, type, data);
        BlockEntity entity = world.getBlockEntity(pos);
        return entity != null && entity.onSyncedBlockEvent(type, data);
    }

    public PistonBehavior getPistonBehavior() {
        return PistonBehavior.DESTROY;
    }

    protected static Direction getBlockConnected() {
        return Direction.UP;
    }


    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        if (polishing.hasParticle()) {
            Color color = polishing.getColor();
            float r = color.getRed() / 255f;
            float g = color.getGreen() / 255f;
            float b = color.getBlue() / 255f;

            Particles.create(ParticleRegistry.SPARKLE_PARTICLE)
                    .addVelocity(((random.nextDouble() - 0.5D) / 30), ((random.nextDouble() - 0.5D) / 30), ((random.nextDouble() - 0.5D) / 30))
                    .setAlpha(0.5f, 0).setScale(0.1f, 0)
                    .setColor(r, g, b)
                    .setLifetime(30)
                    .setSpin((0.5f * (float) ((random.nextDouble() - 0.5D) * 2)))
                    .spawn(world, pos.getX() + 0.5F + ((random.nextDouble() - 0.5D) * 0.5), pos.getY() + 0.5F + ((random.nextDouble() - 0.5D) * 0.5), pos.getZ() + 0.5F + ((random.nextDouble() - 0.5D) * 0.5));
        }
        super.randomDisplayTick(state, world, pos, random);
    }



    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof BlockSimpleInventory) {
                ItemScatterer.spawn(world, pos, ((BlockSimpleInventory) entity).getInventory());
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrystalBlockEntity(pos, state);
    }
}
