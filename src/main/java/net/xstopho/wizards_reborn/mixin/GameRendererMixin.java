package net.xstopho.wizards_reborn.mixin;

import com.google.common.collect.Multimap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.xstopho.wizards_reborn.common.item.equipment.ScytheItem;
import net.xstopho.wizards_reborn.registries.AttributeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @ModifyConstant(method = "updateTargetedEntity", constant = @Constant(doubleValue = 3.0))
    public double injected(double constant) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.getMainHandStack().getItem() instanceof ScytheItem) {
            ItemStack handStack = player.getMainHandStack();
            Multimap<EntityAttribute, EntityAttributeModifier> map = handStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

            if (map.containsKey(AttributeRegistry.ENTITY_REACH)) {
                return map.get(AttributeRegistry.ENTITY_REACH).stream().mapToDouble(EntityAttributeModifier::getValue).sum() + 3;
            }
        }
        return 3.0;
    }
}
