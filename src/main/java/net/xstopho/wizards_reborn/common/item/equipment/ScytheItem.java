package net.xstopho.wizards_reborn.common.item.equipment;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.xstopho.wizards_reborn.registries.AttributeRegistry;

import java.util.UUID;

public class ScytheItem extends SwordItem {
    public static final UUID BASE_ENTITY_REACH_UUID = UUID.fromString("DB0F1F0B-7DF7-4D45-BA75-9BA60DABCCCD");
    public final int radius;

    public ScytheItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, int radius) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.radius = radius;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> atts = ImmutableMultimap.builder();
        atts.putAll(super.getAttributeModifiers(slot));
        atts.put(AttributeRegistry.ENTITY_REACH, new EntityAttributeModifier(BASE_ENTITY_REACH_UUID, "bonus", 1, EntityAttributeModifier.Operation.ADDITION));
        return slot == EquipmentSlot.MAINHAND ? atts.build() : super.getAttributeModifiers(slot);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        assert context.getPlayer() != null;
        PlayerEntity player = context.getPlayer();

        if (player.isSpectator() || player.isCreative()) {
            return ActionResult.PASS;
        }

        BlockPos clickedPos = context.getBlockPos();
        BlockState clickedState = context.getWorld().getBlockState(clickedPos);

        if (clickedState.getBlock() instanceof CropBlock clickedCrop && clickedCrop.isMature(clickedState)) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos crop = clickedPos.add(x, 0, z);
                    BlockState cropState = context.getWorld().getBlockState(crop);
                    if (cropState.getBlock() instanceof CropBlock replantCrop && replantCrop.isMature(cropState)) {
                        context.getWorld().breakBlock(crop, true, player);
                        context.getWorld().setBlockState(crop, replantCrop.withAge(0));
                    }
                }
            }
            context.getStack().damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(player.getActiveHand()));
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
