package one.spectra.inventory.fillers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import one.spectra.inventory.SpectraInventory;

public class VerticalFiller implements InventoryFiller {

    @Override
    public boolean canFill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        var groups = itemStacks.stream()
                .collect(Collectors.groupingBy(x -> x.getType().getKey().toString() + x.getItemMeta().getAsString(),
                        Collectors.counting()));

        var columnsRequired = groups.values().stream().map(x -> Math.ceil((double)x / inventory.getRowAmount())).mapToInt(Double::intValue).sum();

        return columnsRequired <= 9;
    }

    @Override
    public void fill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        var groups = itemStacks.stream()
                .collect(Collectors.groupingBy(x -> x.getType().getKey().toString() + x.getItemMeta().getAsString()));
        var orderedGroups = groups.values().stream()
                .sorted(Comparator.comparingInt(List<ItemStack>::size).reversed())
                .toList();
        var columnIndex = 0;
        for (List<ItemStack> stacks : orderedGroups) {
            var stackColumnIndex = 0;
            for (var i = 0; i < stacks.size(); i++) {
                if (i != 0 && i % inventory.getRowAmount() == 0) {
                    stackColumnIndex++;
                }
                var slotIndex = i - stackColumnIndex * inventory.getRowAmount();
                inventory.putInSlot(columnIndex + stackColumnIndex + (9 * slotIndex), stacks.get(i));
            }
            columnIndex = columnIndex + stackColumnIndex + 1;
        }
    }

}
