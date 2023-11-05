package net.xstopho.wizards_reborn.common.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.xstopho.wizards_reborn.api.crystal.CrystalType;
import net.xstopho.wizards_reborn.common.entities.CrystalGrowthBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Random;
import java.util.stream.Stream;

public class CrystalGrowthBlock extends BlockWithEntity implements BlockEntityProvider, Waterloggable {
    public CrystalType type;
    private static Random random = new Random();

    public static final IntProperty AGE = Properties.AGE_4;

    private static final VoxelShape SHAPE_1 = Block.createCuboidShape(6, 0, 6, 9, 3, 9);
    private static final VoxelShape SHAPE_2 = Block.createCuboidShape(6, 0, 6, 10, 3, 10);

    private static final VoxelShape SHAPE_3 = Stream.of(
            Block.createCuboidShape(6, 0, 6, 10, 5, 10),
            Block.createCuboidShape(5, 0, 5, 8, 3, 8),
            Block.createCuboidShape(9, 0, 9, 12, 3, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_4 = Stream.of(
            Block.createCuboidShape(6, 0, 6, 11, 7, 11),
            Block.createCuboidShape(4, 0, 4, 8, 5, 8),
            Block.createCuboidShape(9, 0, 9, 13, 3, 13),
            Block.createCuboidShape(9, 0, 5, 12, 3, 8)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_5 = Stream.of(
            Block.createCuboidShape(5, 0, 5, 11, 9, 11),
            Block.createCuboidShape(3, 0, 3, 8, 7, 8),
            Block.createCuboidShape(9, 0, 9, 13, 5, 13),
            Block.createCuboidShape(8, 0, 4, 12, 3, 8),
            Block.createCuboidShape(3, 0, 9, 6, 3, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {SHAPE_1, SHAPE_2, SHAPE_3, SHAPE_4, SHAPE_5};

    public CrystalGrowthBlock(CrystalType type, Settings settings) {
        super(settings);
        this.type = type;
        setDefaultState(getDefaultState().with(Properties.WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_BY_AGE[state.get(getAgeProperty())];
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.WATERLOGGED).add(AGE);
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

        return getBlockConnected().getOpposite() == direction && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
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

    public IntProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 4;
    }

    protected int getAge(BlockState state) {
        return state.get(AGE);
    }

    public BlockState withAge(int age) {
        return getDefaultState().with(AGE, age);
    }

    public boolean isMature(BlockState state) {
        return state.get(getAgeProperty()) >= getMaxAge();
    }

    public boolean isRandomlyTicking(BlockState state) {
        return !isMature(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        if (!isAreaLoaded(pos, 1, world)) return;
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = 5f;
                if (random.nextInt(5) == 0) {
                    world.setBlockState(pos, withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
        }
    }

    private boolean isAreaLoaded(BlockPos pos, int range, WorldView view) {
        return view.isRegionLoaded(pos.add(-range, -range, -range), pos.add(range, range, range));
    }

    public void grow(World world, BlockPos pos, BlockState state) {
        int i = this.getAge(state);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        world.setBlockState(pos, this.withAge(i), 2);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (world.isClient() && !player.isCreative() && !player.isSpectator()) {
            Color color = type.getColor();
            float r = color.getRed() / 255f;
            float g = color.getGreen() / 255f;
            float b = color.getBlue() / 255f;

            //TODO Adding Particles -> Complete Particle System is needed
        }

        super.onBreak(world, pos, state, player);
    }



    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrystalGrowthBlockEntity(pos, state);
    }
}
