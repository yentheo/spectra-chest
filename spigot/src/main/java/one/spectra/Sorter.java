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

        var merged = tempInventory.stream().sorted(comparator).toList();

        for (ItemStack stack : merged) {
            playerInventory.addItem(stack);
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

        var merged = tempInventory.stream().sorted(comparator).toList();

        for (ItemStack stack : merged) {
            inventory.addItem(stack);
        }
    }
}
