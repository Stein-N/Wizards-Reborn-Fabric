package net.xstopho.wizards_reborn.common.crystal;


import net.xstopho.wizards_reborn.api.crystal.PolishingType;

import java.awt.*;

public class MasterfulPolishingType extends PolishingType {
    public MasterfulPolishingType(String id) {
        super(id);
    }

    public boolean hasParticle() {
        return true;
    }

    public Color getColor() {
        return new Color(178, 227, 148);
    }
}
