package net.xstopho.wizards_reborn.registries;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.xstopho.wizards_reborn.WizardsReborn;

public class AttributeRegistry {

    public static final EntityAttribute WISSEN_SALE = register("wissen_sale",
            new ClampedEntityAttribute("attribute.name.wizards_reborn.wissen_sale", 0, 0, 75).setTracked(true));

    public static final EntityAttribute ENTITY_REACH = register("entity_reach",
            new ClampedEntityAttribute("attribute.name.wizards_reborn.entity_reach", 3, 0, 1024.0D).setTracked(true));

    private static EntityAttribute register(String id, EntityAttribute attribute) {
        return Registry.register(Registries.ATTRIBUTE, new Identifier(WizardsReborn.MOD_ID, id), attribute);
    }

    public static void init() {

    }
}
