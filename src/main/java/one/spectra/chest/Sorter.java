package one.spectra.chest;

import java.util.ArrayList;
import java.util.Comparator;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class Sorter {
    public void Sort(ServerPlayerEntity player) {
        
        var playerInventory = player.getInventory();

        var inventorySize = playerInventory.size();

        var tempInventory = new ArrayList<ItemStack>();

        // skip hotbar, start at 9
        for (var i = 9; i < inventorySize; i++) {
            var stack = playerInventory.removeStack(i);
            if (!(stack.getItem() instanceof AirBlockItem)) {
                tempInventory.add(stack);
            }
        }

        Comparator<ItemStack> comparator = Comparator.comparing(itemStack -> Item.getRawId(itemStack.getItem()));
        Comparator<ItemStack> countComparison = Comparator.comparing(itemStack -> itemStack.getCount());
        
        comparator = comparator.thenComparing(countComparison.reversed());

        var merged = MergeStacks(tempInventory).stream().sorted(comparator).toList();

        for (var i = 0; i < merged.size(); i++) {
            playerInventory.setStack(i + 9, merged.get(i));
        }
    }

    public void Sort(Inventory inventory) {
        var inventorySize = inventory.size();

        var tempInventory = new ArrayList<ItemStack>();

        for (var i = 0; i < inventorySize; i++) {
            var stack = inventory.removeStack(i);
            if (!(stack.getItem() instanceof AirBlockItem)) {
                tempInventory.add(stack);
            }
        }

        Comparator<ItemStack> comparator = Comparator.comparing(itemStack -> Item.getRawId(itemStack.getItem()));
        Comparator<ItemStack> countComparison = Comparator.comparing(itemStack -> itemStack.getCount());
        
        comparator = comparator.thenComparing(countComparison.reversed());

        var merged = MergeStacks(tempInventory).stream().sorted(comparator).toList();

        for (var i = 0; i < merged.size(); i++) {
            inventory.setStack(i, merged.get(i));
        }
    }

    private ArrayList<ItemStack> MergeStacks(ArrayList<ItemStack> original) {
        var merged = new ArrayList<ItemStack>();
        for (ItemStack itemStack : original) {
            if (itemStack.isStackable()) {
                var firstFreeStack = merged.stream()
                        .filter(x -> x.isOf(itemStack.getItem()) && x.getCount() < x.getMaxCount()).findFirst();
                if (firstFreeStack.isPresent()) {
                    var currentAmount = firstFreeStack.get().getCount();
                    var amountToAdd = itemStack.getCount();
                    var newAmount = currentAmount + amountToAdd;
                    var overflow = newAmount - itemStack.getMaxCount();
                    if (overflow > 0) {
                        firstFreeStack.get().setCount(itemStack.getMaxCount());
                        itemStack.setCount(overflow);
                        merged.add(itemStack);
                    } else {
                        firstFreeStack.get().setCount(newAmount);
                    }
                } else {
                    merged.add(itemStack);
                }
            } else {
                merged.add(itemStack);
            }
        }
        return merged;
    }
}
