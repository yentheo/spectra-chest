package one.spectra.better_chests.inventory.fillers;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;

public class RowFiller implements Filler {

    @Override
    public boolean canFill(Inventory inventory, List<ItemStack> stacks) {
        var groups = stacks.stream().collect(Collectors.groupingBy(ItemStack::getMaterialKey)).entrySet();

        var rowsEnough = inventory.getRows() >= groups.size();
        var columnsEnough = inventory.getColumns() >= groups.stream().map(entry -> entry.getValue().size())
                .max(Integer::compare).orElse(0);

        return columnsEnough && rowsEnough;
    }

    @Override
    public void fill(Inventory inventory, List<ItemStack> stacks) {
        Comparator<Entry<String, List<ItemStack>>> comparer = Comparator.comparing(entry -> entry.getValue().size(),
                Comparator.reverseOrder());
        var groups = stacks.stream()
                .collect(Collectors.groupingBy(ItemStack::getMaterialKey))
                .entrySet().stream()
                .sorted(comparer.thenComparing(x -> x.getValue().stream().mapToInt(y -> y.getAmount()).sum(),
                        Comparator.reverseOrder()))
                .map(x -> x.getValue().stream()
                        .sorted(Comparator.comparing(stack -> stack.getAmount(), Comparator.reverseOrder())).toList())
                .toList();

        for (var rowIndex = 0; rowIndex < groups.size(); rowIndex++) {
            var stacksInGroup = groups.get(rowIndex);
            for (var columnIndex = 0; columnIndex < stacksInGroup.size(); columnIndex++) {
                inventory.putInSlot(columnIndex + rowIndex * inventory.getColumns(), stacksInGroup.get(columnIndex));
            }
        }
    }

}
