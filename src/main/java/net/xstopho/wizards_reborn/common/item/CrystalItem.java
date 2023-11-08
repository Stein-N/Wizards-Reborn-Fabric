package net.xstopho.wizards_reborn.common.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.xstopho.wizards_reborn.api.crystal.CrystalStat;
import net.xstopho.wizards_reborn.api.crystal.CrystalType;
import net.xstopho.wizards_reborn.api.crystal.CrystalUtils;
import net.xstopho.wizards_reborn.api.crystal.PolishingType;
import net.xstopho.wizards_reborn.common.block.CrystalBlock;
import net.xstopho.wizards_reborn.common.entities.CrystalBlockEntity;
import net.xstopho.wizards_reborn.registries.ParticleRegistry;
import net.xstopho.wizards_reborn.utils.ColorUtils;
import net.xstopho.wizards_reborn.utils.PacketUtils;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class CrystalItem extends BlockItem {
    public CrystalItem(Block block, Settings settings) {
        super(block, settings.maxCount(1));
    }

    public CrystalType getType() {
        if (getBlock() instanceof CrystalBlock) {
            CrystalBlock block = (CrystalBlock) getBlock();
            return block.type;
        }
        return new CrystalType("");
    }

    public PolishingType getPolishing() {
        if (getBlock() instanceof CrystalBlock) {
            CrystalBlock block = (CrystalBlock) getBlock();
            return block.polishing;
        }

        return new PolishingType("");
    }

    public static int getStatLevel(ItemStack stack, CrystalStat stat) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int statlevel = 0;
        if (nbt.contains(stat.getID())) {
            statlevel = nbt.getInt(stat.getID());
        }
        return statlevel;
    }

    public static ItemStack creativeTabRandomStats(Item item) {
        ItemStack stack = item.getDefaultStack();
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putBoolean("random_stats", true);
        stack.setNbt(nbt);
        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            NbtCompound nbt = stack.getOrCreateNbt();
            if (nbt.contains("random_stats")) {
                nbt.remove("random_stats");
                CrystalUtils.createCrystalItemStats(stack, getType(), world, 6);
                stack.setNbt(nbt);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        CrystalType type = getType();
        Color color = type.getColor();
        for (CrystalStat stat : type.getStats()) {
            int statlevel = getStatLevel(stack, stat);
            int red = (int) MathHelper.lerp((float) statlevel / stat.getMaxLevel(), Color.GRAY.getRed(), color.getRed());
            int green = (int) MathHelper.lerp((float) statlevel / stat.getMaxLevel(), Color.GRAY.getGreen(), color.getGreen());
            int blue = (int) MathHelper.lerp((float) statlevel / stat.getMaxLevel(), Color.GRAY.getBlue(), color.getBlue());

            int packColor = ColorUtils.packColor(255, red, green, blue);
            tooltip.add(Text.translatable(stat.getTranslatedName()).append(": " + statlevel).setStyle(Style.EMPTY.withColor(packColor)));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        if (!this.getBlock().isEnabled(context.getWorld().getEnabledFeatures())) {
            return ActionResult.FAIL;
        }
        if (!context.canPlace()) {
            return ActionResult.FAIL;
        }
        ItemPlacementContext itemPlacementContext = this.getPlacementContext(context);
        if (itemPlacementContext == null) {
            return ActionResult.FAIL;
        }
        BlockState blockState = this.getPlacementState(itemPlacementContext);
        if (blockState == null) {
            return ActionResult.FAIL;
        }
        if (!this.place(itemPlacementContext, blockState)) {
            return ActionResult.FAIL;
        }
        BlockPos blockPos = itemPlacementContext.getBlockPos();
        World world = itemPlacementContext.getWorld();
        PlayerEntity playerEntity = itemPlacementContext.getPlayer();
        ItemStack itemStack = itemPlacementContext.getStack();
        BlockState blockState2 = world.getBlockState(blockPos);
        if (blockState2.isOf(blockState.getBlock())) {
            blockState2 = this.updateBlockStateFromTag(blockPos, world, itemStack, blockState2);
            this.postPlacement(blockPos, world, playerEntity, itemStack, blockState2);
            blockState2.getBlock().onPlaced(world, blockPos, blockState2, playerEntity, itemStack);
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
            }
        }

        CrystalBlockEntity entity = (CrystalBlockEntity) world.getBlockEntity(blockPos);
        entity.getInventory().setStack(0, itemStack.copy());
        world.updateListeners(blockPos, blockState, blockState2, Block.NOTIFY_LISTENERS);

        BlockSoundGroup blockSoundGroup = blockState2.getSoundGroup();
        world.playSound(playerEntity, blockPos, this.getPlaceSound(blockState2), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0f) / 2.0f, blockSoundGroup.getPitch() * 0.8f);
        world.emitGameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Emitter.of(playerEntity, blockState2));
        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return ActionResult.success(world.isClient);
    }

    private BlockState updateBlockStateFromTag(BlockPos pos, World world, ItemStack stack, BlockState state) {
        BlockState blockstate = state;
        NbtCompound compoundtag = stack.getNbt();
        if (compoundtag != null) {
            NbtCompound compoundtag1 = compoundtag.getCompound("BlockStateTag");
            StateManager<Block, BlockState> stateManager = state.getBlock().getStateManager();

            for(String s : compoundtag1.getKeys()) {
                Property<?> property = stateManager.getProperty(s);
                if (property != null) {
                    String s1 = compoundtag1.get(s).asString();
                    blockstate = updateState(blockstate, property, s1);
                }
            }
        }

        if (blockstate != state) {
            world.setBlockState(pos, blockstate, Block.NOTIFY_LISTENERS);
        }

        return blockstate;
    }

    private static <T extends Comparable<T>> BlockState updateState(BlockState pState, Property<T> pProperty, String pValueIdentifier) {
        return pProperty.parse(pValueIdentifier).map((p_40592_) -> {
            return pState.with(pProperty, p_40592_);
        }).orElse(pState);
    }

    //TODO addParticles method missing
}
