package one.spectra.chest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AirBlockItem;
import one.spectra.chest.abstractions.Item;
import one.spectra.chest.abstractions.ItemStack;

public class Inventory {
    private one.spectra.chest.abstractions.Inventory inventory;
    private int skipSlots;
    private int size;

    public Inventory(net.minecraft.inventory.Inventory inventory) {
        this(new one.spectra.chest.abstractions.Inventory(inventory), 0, inventory.size());
    }

    public Inventory(PlayerEntity player) {
        this(new one.spectra.chest.abstractions.Inventory(player.getInventory()), 9, 27);
    }

    private Inventory(one.spectra.chest.abstractions.Inventory inventory, int skipSlots, int size) {
        this.inventory = inventory;
        this.skipSlots = skipSlots;
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public ItemStack getStack(int slot) {
        return slot + skipSlots <= this.size
                ? this.inventory.getStack(slot + skipSlots)
                : null;
    }

    public int moveToStack(int slot, int amount) {
        var stack = getStack(slot);
        var stackCount = stack.getCount();
        var maxItemCount = stack.getItem().getMaxCount();
        var freeSpace = maxItemCount - stackCount;
        var overflow = amount - freeSpace;
        if (overflow < 0) {
            stack.setCount(maxItemCount);
            return overflow;
        }

        stack.setCount(stackCount + amount);
        return 0;
    }

    public int getSlotWithFreeSpace(Item item) {
        for (var i = 0; i < this.getSize(); i++) {
            var toStack = getStack(i);
            if (toStack.getItem() == item && toStack.getCount() < item.getMaxCount()) {
                return i;
            }
        }

        return -1;
    }

    public int getEmptySlot() {
        for (var i = 0; i < this.getSize(); i++) {
            var toStack = getStack(i);
            if (toStack.getItem().isItem(AirBlockItem.class)) {
                return i;
            }
        }

        return -1;
    }
}
