package one.spectra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import one.spectra.inventory.SpectraInventory;
import one.spectra.inventory.fillers.DefaultFiller;
import one.spectra.inventory.fillers.InventoryFiller;
import one.spectra.inventory.fillers.VerticalFiller;

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

        var merged = new Merger().merge(tempInventory).stream().sorted(comparator).toList();

        var spectraInventory = new SpectraInventory(playerInventory);
        var filler = getFiller(merged, spectraInventory);
        filler.fill(merged, spectraInventory);
    }

    public void Sort(Inventory inventory) {
        var inventorySize = inventory.getSize();

        ArrayList<ItemStack> tempInventory = new ArrayList<ItemStack>();

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

        var merged = new Merger().merge(tempInventory).stream().sorted(comparator).toList();

        var spectraInventory = new SpectraInventory(inventory);
        var filler = getFiller(merged, spectraInventory);
        filler.fill(merged, spectraInventory);
    }

    private InventoryFiller getFiller(List<ItemStack> stacks, SpectraInventory inventory) {
        var fillers = new ArrayList<InventoryFiller>();
        fillers.add(new VerticalFiller());

        var filler = fillers.stream().filter(x -> x.canFill(stacks, inventory)).findFirst();
        return filler.isPresent() ? filler.get() : new DefaultFiller();
    }
}
