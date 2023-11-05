package net.xstopho.wizards_reborn.common.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.State;
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
import net.xstopho.wizards_reborn.utils.ColorUtils;
import net.xstopho.wizards_reborn.utils.PacketUtils;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class CrystalItem extends BlockItem {
    public CrystalItem(Block block, Settings settings) {
        super(block, settings);
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

    //TODO Crystal isn't saved in Crystal Block Inventory, priority to fix
    @Override
    public ActionResult place(ItemPlacementContext context) {
        if (!this.getBlock().isEnabled(context.getWorld().getEnabledFeatures())) {
            return ActionResult.FAIL;
        } else if (!context.canPlace()) {
            return ActionResult.FAIL;
        } else {
            ItemPlacementContext itemPlacementContext = this.getPlacementContext(context);
            if (itemPlacementContext == null) {
                return ActionResult.FAIL;
            } else {
                BlockState blockstate = this.getPlacementState(itemPlacementContext);
                if (blockstate == null) {
                    return ActionResult.FAIL;
                } else if (!this.place(itemPlacementContext, blockstate)) {
                    return ActionResult.FAIL;
                } else {
                    BlockPos pos = itemPlacementContext.getBlockPos();
                    World world = itemPlacementContext.getWorld();
                    PlayerEntity player = itemPlacementContext.getPlayer();
                    ItemStack itemstack = player.getActiveItem();
                    BlockState state = world.getBlockState(pos);
                    if (state.isOf(blockstate.getBlock())) {
                        state = this.updateBlockStateFromTag(pos, world, itemstack, state);
                        this.postPlacement(pos, world, player, itemstack, state);
                        world.setBlockState(pos, state);
                        if (player instanceof ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)player, pos, itemstack);
                        }
                    }

                    CrystalBlockEntity tile = (CrystalBlockEntity) world.getBlockEntity(pos);
                    tile.getInventory().setStack(0, itemstack.copy());


                    PacketUtils.SUpdateTileEntityPacket(tile);

                    BlockSoundGroup soundtype = state.getSoundGroup();
                    world.playSound(player, pos, this.getPlaceSound(state), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Emitter.of(player, state));
                    if (player == null || !player.getAbilities().creativeMode) {
                        itemstack.decrement(1);
                    }

                    return ActionResult.success(world.isClient);
                }
            }
        }
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
            world.setBlockState(pos, blockstate, 2);
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
