package net.xstopho.wizards_reborn.api.crystal;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrystalUtils {
    public static void createCrystalItemStats(ItemStack stack, CrystalType type, World level, int count) {
        int maxCount = 0;
        Map<CrystalStat, Integer> intStats = new HashMap<CrystalStat, Integer>();
        ArrayList<CrystalStat> stats = new ArrayList<CrystalStat>();

        for (CrystalStat stat : type.getStats()) {
            maxCount = maxCount + stat.getMaxLevel();
            intStats.put(stat, 0);
            stats.add(stat);
        }
        if (count > maxCount) {
            count = maxCount;
        }

        for (int i = 0; i < count; i++) {
            int id = MathHelper.nextInt(level.random, 0, stats.size() - 1);
            CrystalStat stat = stats.get(id);
            intStats.put(stat, intStats.get(stat) + 1);
            if (intStats.get(stat) >= stat.getMaxLevel()) {
                stats.remove(stat);
            }
        }

        NbtCompound nbt = stack.getOrCreateNbt();
        for (CrystalStat stat : type.getStats()) {
            nbt.putInt(stat.getID(), intStats.get(stat));
        }
        stack.setNbt(nbt);
    }

    public static int getStatLevel(NbtCompound nbt, CrystalStat stat) {
        int statlevel = 0;
        if (nbt.contains(stat.getID())) {
            statlevel = nbt.getInt(stat.getID());
        }
        return statlevel;
    }
}
