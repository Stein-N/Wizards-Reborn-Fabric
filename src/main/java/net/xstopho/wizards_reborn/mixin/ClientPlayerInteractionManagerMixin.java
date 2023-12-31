package net.xstopho.wizards_reborn.mixin;

import com.google.common.collect.Multimap;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.xstopho.wizards_reborn.WizardsReborn;
import net.xstopho.wizards_reborn.common.item.equipment.ScytheItem;
import net.xstopho.wizards_reborn.registries.AttributeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Environment(EnvType.CLIENT)
    @Inject(method = "getReachDistance", at = @At("HEAD"), cancellable = true)
    public void getReachDistance(CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        ItemStack handStack = player.getMainHandStack();
        Multimap<EntityAttribute, EntityAttributeModifier> attributes = handStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

        if (attributes.containsKey(AttributeRegistry.ENTITY_REACH)) {
            float range = (float) (attributes.get(AttributeRegistry.ENTITY_REACH).stream().mapToDouble(EntityAttributeModifier::getValue).sum() + 3.0f);
            cir.setReturnValue(range);
        }
    }
}
