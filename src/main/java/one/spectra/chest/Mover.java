package one.spectra.chest;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Mover {
    public void Move(Inventory from, Inventory to, int skipFrom, int skipTo) {
        var fromSize = from.size();

        for (var i = skipFrom; i < fromSize; i++) {
            var stack = from.getStack(i);
            var stackItem = stack.getItem();
            var toStackWithFreeSpace = GetStackWithFreeSpace(to, stackItem, skipTo);
            if (toStackWithFreeSpace.getItem() instanceof AirBlockItem) {
                to.setStack(i, stack);
            } else {
                var overflow = 0;
                do {
                    overflow = Move(stack, toStackWithFreeSpace);
                    stack.setCount(overflow);
                    toStackWithFreeSpace = GetStackWithFreeSpace(to, stackItem, skipTo);
                } while (overflow != 0 && toStackWithFreeSpace != null);

                if (overflow == 0) {
                    from.removeStack(i);
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

    private ItemStack GetStackWithFreeSpace(Inventory to, Item item, int skipTo) {
        var toSize = to.size();
        for (var i = skipTo; i < toSize; i++) {
            var toStack = to.getStack(i);
            if (toStack.getItem() == item && toStack.getCount() < item.getMaxCount()) {
                return toStack;
            }
        }
        for (var i = skipTo; i < toSize; i++) {
            var toStack = to.getStack(i);
            if (toStack.getItem() instanceof AirBlockItem) {
                return toStack;
            }
        }
        return null;
    }
}
