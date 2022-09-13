package one.spectra.chest;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Mover {
    public void Move(Inventory from, Inventory to, int skipFrom, int skipTo, int fromSize, int toSize) {
        for (var i = skipFrom; i < fromSize; i++) {
            var stack = from.getStack(i);
            var stackItem = stack.getItem();
            var slotIndexWithFreeSpace = GetSlotIndexWithFreeSpace(to, stackItem, skipTo, toSize);
            if (slotIndexWithFreeSpace >= 0) {
                var toStackWithFreeSpace = to.getStack(slotIndexWithFreeSpace);
                if (toStackWithFreeSpace.getItem() instanceof AirBlockItem) {
                    stack = from.removeStack(i);
                    to.setStack(slotIndexWithFreeSpace, stack);
                } else {
                    var overflow = 0;
                    do {
                        overflow = Move(stack, toStackWithFreeSpace);
                        stack.setCount(overflow);
                        slotIndexWithFreeSpace = GetSlotIndexWithFreeSpace(to, stackItem, skipTo, toSize);
                        if (slotIndexWithFreeSpace >= 0) {
                            toStackWithFreeSpace = to.getStack(slotIndexWithFreeSpace);
                        }
                    } while (overflow != 0 && slotIndexWithFreeSpace >= 0);

                    if (overflow == 0) {
                        from.removeStack(i);
                    }
                }
            }
        }
    }

    private int Move(ItemStack from, ItemStack to) {
        var freeSpace = to.getMaxCount() - to.getCount();
        var countToMove = from.getCount();

        if (freeSpace >= countToMove) {
            to.setCount(to.getCount() + countToMove);
            return 0;
        } else {
            to.setCount(to.getMaxCount());
            var overflow = countToMove - freeSpace;
            return overflow;
        }
    }

    private int GetSlotIndexWithFreeSpace(Inventory to, Item item, int skipTo, int toSize) {
        for (var i = skipTo; i < toSize; i++) {
            var toStack = to.getStack(i);
            if (toStack.getItem() == item && toStack.getCount() < item.getMaxCount()) {
                return i;
            }
        }
        for (var i = skipTo; i < toSize; i++) {
            var toStack = to.getStack(i);
            if (toStack.getItem() instanceof AirBlockItem) {
                return i;
            }
        }
        return -1;
    }
}
