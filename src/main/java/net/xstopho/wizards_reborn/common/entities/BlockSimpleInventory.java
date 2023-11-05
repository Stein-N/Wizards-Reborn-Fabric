package net.xstopho.wizards_reborn.common.entities;

import com.google.common.base.Preconditions;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public abstract class BlockSimpleInventory extends BlockEntity {

    private final SimpleInventory inventory = createInventory();

    public BlockSimpleInventory(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory.addListener(i -> markDirty());
    }

    private static void copyToInv(DefaultedList<ItemStack> src, Inventory inv) {
        Preconditions.checkArgument(src.size() == inv.size());
        for (int i = 0; i < src.size(); i++) {
            inv.setStack(i, src.get(i));
        }
    }

    private static DefaultedList<ItemStack> copyFromInv(Inventory inv) {
        DefaultedList<ItemStack> stacks = DefaultedList.ofSize(inv.size(), ItemStack.EMPTY);
        for (int i = 0; i < inv.size(); i++) {
            stacks.set(i, inv.getStack(i));
        }
        return stacks;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        DefaultedList<ItemStack> stacks = DefaultedList.ofSize(inventorySize(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, stacks);
        copyToInv(stacks, inventory);
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, copyFromInv(inventory));
    }

    public final int inventorySize() {
        return getInventory().size();
    }

    protected abstract SimpleInventory createInventory();

    public final Inventory getInventory() {
        return inventory;
    }
}
