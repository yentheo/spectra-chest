package one.spectra.inventory.fillers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import one.spectra.inventory.SpectraInventory;

public class HorizontalFiller implements InventoryFiller {

    @Override
    public boolean canFill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        var groups = itemStacks.stream()
                .collect(Collectors.groupingBy(x -> x.getType().getKey().toString() + x.getItemMeta().getAsString(),
                        Collectors.counting()));

        var rowsRequired = groups.values().stream().map(x -> Math.ceil((double)x / 9)).mapToInt(Double::intValue).sum();

        return rowsRequired <= inventory.getRowAmount();
    }

    @Override
    public void fill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        var groups = itemStacks.stream()
                .collect(Collectors.groupingBy(x -> x.getType().getKey().toString() + x.getItemMeta().getAsString()));
        var orderedGroups = groups.values().stream()
                .sorted(Comparator.comparingInt(List<ItemStack>::size).reversed())
                .toList();
        var rowIndex = 0;
        for (List<ItemStack> stacks : orderedGroups) {
            var stackRowIndex = 0;
            for (var i = 0; i < stacks.size(); i++) {
                if (i != 0 && i % 9 == 0) {
                    stackRowIndex++;
                }
                var slotIndex = i + 9 * rowIndex;
                inventory.putInSlot(slotIndex, stacks.get(i));
            }
            rowIndex = rowIndex + stackRowIndex + 1;
        }
    }

}
