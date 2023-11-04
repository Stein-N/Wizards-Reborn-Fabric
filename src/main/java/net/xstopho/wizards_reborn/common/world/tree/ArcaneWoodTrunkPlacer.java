package net.xstopho.wizards_reborn.common.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.xstopho.wizards_reborn.registries.WorldGenRegistry;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.BiConsumer;

public class ArcaneWoodTrunkPlacer extends TrunkPlacer {
    public ArcaneWoodTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    public static final Codec<ArcaneWoodTrunkPlacer> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                    Codec.intRange(0, 24).fieldOf("height_rand_a").forGetter(placer -> placer.firstRandomHeight),
                            Codec.intRange(0, 24).fieldOf("height_rand_b").forGetter(placer -> placer.secondRandomHeight))
                    .apply(builder, ArcaneWoodTrunkPlacer::new));

    @Override
    protected TrunkPlacerType<?> getType() {
        return WorldGenRegistry.ARCANE_WOOD_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();
        int x = startPos.getX();
        int y = startPos.getY();
        int z = startPos.getZ();

        int numBranches = 0;
        int lastBranch = 0;
        double branchChance = 1;

        for (int i = 0; i < height; i++) {
            if (i == height - 2) {
                branchChance = 0.9;
            }
            boolean northB = random.nextFloat() >= branchChance;
            boolean southB = random.nextFloat() >= branchChance;
            boolean eastB = random.nextFloat() >= branchChance;
            boolean westB = random.nextFloat() >= branchChance;
            branchChance = 0.45;

            int j2 = y + i;
            BlockPos blockPos1 = new BlockPos(x, j2, z);
            if (TreeFeature.isAirOrLeaves(world, blockPos1)) {
                getAndSetState(world, replacer, random, blockPos1, config);
            }

            if (i > height - 5) {
                setBlock(world, startPos.up(i).north(), config.foliageProvider.get(random, startPos.up(i).north()), replacer);
                setBlock(world, startPos.up(i).south(), config.foliageProvider.get(random, startPos.up(i).south()), replacer);
                setBlock(world, startPos.up(i).west(), config.foliageProvider.get(random, startPos.up(i).west()), replacer);
                setBlock(world, startPos.up(i).east(), config.foliageProvider.get(random, startPos.up(i).east()), replacer);

                if (random.nextFloat() < 0.5f) setBlock(world, startPos.up(i).north(2), config.foliageProvider.get(random, startPos.up(i).north()), replacer);
                if (random.nextFloat() < 0.5f) setBlock(world, startPos.up(i).south(2), config.foliageProvider.get(random, startPos.up(i).south()), replacer);
                if (random.nextFloat() < 0.5f) setBlock(world, startPos.up(i).west(2), config.foliageProvider.get(random, startPos.up(i).west()), replacer);
                if (random.nextFloat() < 0.5f) setBlock(world, startPos.up(i).east(2), config.foliageProvider.get(random, startPos.up(i).east()), replacer);
            }

            if (i == height -1) {
                setBlock(world, startPos.up(i+1), config.foliageProvider.get(random, startPos.up(i+1)), replacer);
                setBlock(world, startPos.up(i+2), config.foliageProvider.get(random, startPos.up(i+2)), replacer);
            }

            if ((i > 1 && i > lastBranch) || (i == height - 2)) {
                if (northB) {
                    addBranch(world, startPos, i, Direction.NORTH, random, config, replacer);
                    lastBranch = i;
                    numBranches++;
                    northB = false;
                } else if (southB) {
                    addBranch(world, startPos, i, Direction.SOUTH, random, config, replacer);
                    lastBranch = i;
                    numBranches++;
                    southB = false;
                } else if (eastB) {
                    addBranch(world, startPos, i, Direction.EAST, random, config, replacer);
                    lastBranch = i;
                    numBranches++;
                    eastB = false;
                } else if (westB) {
                    addBranch(world, startPos, i, Direction.WEST, random, config, replacer);
                    lastBranch = i;
                    numBranches++;
                    westB = false;
                } else if (numBranches == 0) {
                    addBranch(world, startPos, i, Direction.NORTH, random, config, replacer);
                    lastBranch = i;
                    numBranches++;

                    addBranch(world, startPos, i, Direction.SOUTH, random, config, replacer);
                    numBranches++;
                }
            }
        }

        addBlock(world, startPos, config.trunkProvider.get(random, startPos), replacer);

        list.add(new FoliagePlacer.TreeNode(new BlockPos(x, y, z), 0, true));
        return list;
    }

    public void addBranch(TestableWorld world, BlockPos pos, int height, Direction d, Random random, TreeFeatureConfig config, BiConsumer<BlockPos, BlockState> replacer) {
        pos = pos.up(height);

        addLog(world, pos.offset(d).up(1), random, config, replacer);
        addLog(world, pos.offset(d).up(2), random, config, replacer);
        addLog(world, pos.offset(d, 2).up(2), random, config, replacer);
        addLog(world, pos.offset(d, 3).up(2), random, config, replacer);
        addLog(world, pos.offset(d, 3).up(1), random, config, replacer);
        addLog(world, pos.offset(d, 3).up(0), random, config, replacer);

        addLineLeaves(world, pos.offset(d).up(1), d, 3, random, config, replacer);
        addLineLeaves(world, pos.offset(d).up(2), d, 3, random, config, replacer);
        addLineLeaves(world, pos.offset(d).up(3), d, 3, random, config, replacer);

        for (int j = 1; j < 4; j++) {
            addLineLeaves(world, pos.offset(d, j).up(3), d, 3, random, config, 0.9f, replacer);
            addLineLeaves(world, pos.offset(d, j).up(2), d, 3, random, config, 0.9f, replacer);
            addLineLeaves(world, pos.offset(d, j).up(4), d, 3, random, config, .1f, replacer);
        }

        for (int i = 0; i < 2; i++) {
            addHollowLine(world, pos.offset(d, 2 + i).up(1), d, 2, random, config, replacer);
            addHollowLine(world, pos.offset(d, 2 + i).up(2), d, 2, random, config, replacer);
            addHollowLine(world, pos.offset(d, 2 + i).up(1), d, 3, random, config, 0.1f, replacer);
            addHollowLine(world, pos.offset(d, 2 + i).up(2), d, 3, random, config, 0.1f, replacer);
        }

        addLineLeaves(world, pos.offset(d, 4).up(1), d, 3, random, config, replacer);
        addLineLeaves(world, pos.offset(d, 4).up(2), d, 3, random, config, replacer);

        addLineLeaves(world, pos.offset(d, 5).up(1), d, 3, random, config, 0.1f, replacer);
        addLineLeaves(world, pos.offset(d, 5).up(2), d, 3, random, config, 0.1f, replacer);
    }

    public void addLineLeaves(TestableWorld world, BlockPos pos, Direction d, int length, Random rand, TreeFeatureConfig config, BiConsumer<BlockPos, BlockState> consumer) {
        if (length % 2 == 0)
            addLineLeavesEven(world, pos, d, length, rand, config, 1.0f, consumer);
        else
            addLineLeavesOdd(world, pos, d, length, rand, config, 1.0f, consumer);
    }

    public void addLineLeaves(TestableWorld world, BlockPos pos, Direction d, int length, Random rand, TreeFeatureConfig config, float chance, BiConsumer<BlockPos, BlockState> consumer) {
        if (length % 2 == 0)
            addLineLeavesEven(world, pos, d, length, rand, config, chance, consumer);
        else
            addLineLeavesOdd(world, pos, d, length, rand, config, chance, consumer);
    }

    public void addLineLeavesEven(TestableWorld world, BlockPos pos, Direction d, int length, Random rand, TreeFeatureConfig config, float chance, BiConsumer<BlockPos, BlockState> consumer) {
        Direction left = d.rotateYClockwise();
        Direction right = left.getOpposite();

        for (int i = 0; i < length; i++) {
            if (rand.nextFloat() <= chance && TreeFeature.canReplace(world, pos.offset(left, i - length / 3))) {
                setBlock(world, pos.offset(left, i - length / 3), config.foliageProvider.get(rand, pos.offset(left, i - length / 3)), consumer);
            }
        }
    }

    public void addLineLeavesOdd(TestableWorld world, BlockPos pos, Direction d, int length, Random rand, TreeFeatureConfig config, float chance, BiConsumer<BlockPos, BlockState> consumer) {
        Direction left = d.rotateYClockwise();
        Direction right = left.getOpposite();
        length += 2;
        for (int i = 0; i < (length - 1) / 2; i++) {
            if (rand.nextFloat() <= chance && TreeFeature.canReplace(world, pos.offset(left, i))) {
                setBlock(world, pos.offset(left, i), config.foliageProvider.get(rand, pos.offset(left, i)), consumer);
            }

            if (rand.nextFloat() <= chance && TreeFeature.canReplace(world, pos.offset(right, i))) {
                setBlock(world, pos.offset(right, i), config.foliageProvider.get(rand, pos.offset(right, i)), consumer);
            }
        }
    }

    public void addHollowLine(TestableWorld world, BlockPos pos, Direction d, int length, Random rand, TreeFeatureConfig config, BiConsumer<BlockPos, BlockState> consumer) {
        addHollowLine(world, pos, d, length, rand, config, 1.0f, consumer);
    }

    public void addHollowLine(TestableWorld world, BlockPos pos, Direction d, int length, Random rand, TreeFeatureConfig baseTreeFeatureConfig, float chance, BiConsumer<BlockPos, BlockState> consumer) {
        Direction left = d.rotateYClockwise();
        Direction right = left.getOpposite();

        if (rand.nextFloat() <= chance && TreeFeature.canReplace(world, pos.offset(left, length))) {
            setBlock(world, pos.offset(left, length), baseTreeFeatureConfig.foliageProvider.get(rand, pos.offset(left, length)), consumer);
        }
        if (rand.nextFloat() <= chance && TreeFeature.canReplace(world, pos.offset(right, length))) {
            setBlock(world, pos.offset(right, length), baseTreeFeatureConfig.foliageProvider.get(rand, pos.offset(right, length)), consumer);
        }
    }
    
    public boolean addLog(TestableWorld world, BlockPos pos, Random random, TreeFeatureConfig baseTreeFeatureConfig, BiConsumer<BlockPos, BlockState> consumer) {
        return addBlock(world, pos, baseTreeFeatureConfig.trunkProvider.get(random, pos), consumer);
    }

    public boolean addBlock(TestableWorld world, BlockPos pos, BlockState state, BiConsumer<BlockPos, BlockState> consumer) {
        if (TreeFeature.canReplace(world, pos)) {
            setBlock(world, pos, state, consumer);
            return true;
        }
        return false;
    }
    
    public void setBlock(TestableWorld world, BlockPos pos, BlockState state, BiConsumer<BlockPos, BlockState> consumer) {
        consumer.accept(pos, state);
    }
}
