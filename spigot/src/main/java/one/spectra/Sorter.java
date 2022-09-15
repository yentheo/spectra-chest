package one.spectra;

import java.util.ArrayList;
import java.util.Comparator;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Sorter {
    public void Sort(Player player) {

        PlayerInventory playerInventory = player.getInventory();

        ArrayList<ItemStack> tempInventory = new ArrayList<ItemStack>();

        // skip hotbar, start at 9
        for (int i = 9; i < 36; i++) {
            ItemStack stack = playerInventory.getItem(i);

            playerInventory.clear(i);
            if (stack != null && !stack.getType().isAir()) {
                tempInventory.add(stack);
            }
        }
        Comparator<ItemStack> comparator = Comparator.comparing(itemStack -> itemStack.getType());
        Comparator<ItemStack> countComparison = Comparator.comparing(itemStack -> itemStack.getAmount());

        comparator = comparator.thenComparing(countComparison.reversed());

        var merged = MergeStacks(tempInventory).stream().sorted(comparator).toList();

        for (var i = 0; i < merged.size(); i++) {
            playerInventory.setItem(i + 9, merged.get(i));
        }
    }

    public void Sort(Inventory inventory) {
        var inventorySize = inventory.getSize();

        ArrayList<ItemStack> tempInventory = new ArrayList<ItemStack>();

        // skip hotbar, start at 9
        for (int i = 0; i < inventorySize; i++) {
            ItemStack stack = inventory.getItem(i);
            inventory.clear(i);
            if (stack != null && !stack.getType().isAir()) {
                tempInventory.add(stack);
            }
        }

        Comparator<ItemStack> comparator = Comparator.comparing(itemStack -> itemStack.getType());
        Comparator<ItemStack> countComparison = Comparator.comparing(itemStack -> itemStack.getAmount());

        comparator = comparator.thenComparing(countComparison.reversed());

        var merged = MergeStacks(tempInventory).stream().sorted(comparator).toList();

        for (var i = 0; i < merged.size(); i++) {
            inventory.setItem(i, merged.get(i));
        }
    }

    private ArrayList<ItemStack> MergeStacks(ArrayList<ItemStack> original) {
        var merged = new ArrayList<ItemStack>();
        for (ItemStack itemStack : original) {
            if (IsEligibleForMerging(itemStack)) {
                var optionalFirstFreeStack = merged.stream().filter(x -> IsEligibleForMerging(x))
                        .filter(x -> x.getType().compareTo(itemStack.getType()) == 0).findFirst();
                if (optionalFirstFreeStack.isPresent()) {
                    var firstFreeStack = optionalFirstFreeStack.get();
                    var currentAmount = firstFreeStack.getAmount();
                    var amountToAdd = itemStack.getAmount();
                    var newAmount = currentAmount + amountToAdd;
                    var overflow = newAmount - itemStack.getMaxStackSize();
                    if (overflow > 0) {
                        firstFreeStack.setAmount(itemStack.getMaxStackSize());
                        itemStack.setAmount(overflow);
                        merged.add(itemStack);
                    } else {
                        firstFreeStack.setAmount(newAmount);
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

    private boolean IsEligibleForMerging(ItemStack itemStack) {
        return itemStack.getMaxStackSize() > 1
                && !itemStack.getItemMeta().hasDisplayName()
                && itemStack.getType() != Material.PLAYER_HEAD;
    }
}
