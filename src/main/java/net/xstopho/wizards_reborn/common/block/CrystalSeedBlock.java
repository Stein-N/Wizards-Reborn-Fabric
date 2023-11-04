package net.xstopho.wizards_reborn.common.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;


public class CrystalSeedBlock extends Block implements Waterloggable {
    private static final VoxelShape SHAPE = Block.createCuboidShape(7, 0, 7, 9, 2, 9);

    public CrystalSeedBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        FluidState state = context.getWorld().getFluidState(context.getBlockPos());
        return this.getDefaultState().with(Properties.WATERLOGGED, state.getFluid() == Fluids.WATER);
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
        return getBlockConnected(state).getOpposite() == direction && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    public boolean canSurvive(BlockState state, WorldView worldView, BlockPos pos) {
        Direction direction = getBlockConnected(state).getOpposite();
        return Block.sideCoversSmallSquare(worldView, pos.offset(direction), direction.getOpposite());
    }

    protected static Direction getBlockConnected(BlockState state) {
        return Direction.UP;
    }
}
