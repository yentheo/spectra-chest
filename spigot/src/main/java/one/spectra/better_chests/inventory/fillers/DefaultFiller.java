package one.spectra.better_chests.inventory.fillers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;

public class DefaultFiller implements Filler {

    @Override
    public boolean canFill(Inventory inventory, List<ItemStack> stacks) {
        return inventory.getSize() >= stacks.size();
    }

    @Override
    public void fill(Inventory inventory, List<ItemStack> stacks) {
        var groups = stacks.stream().collect(Collectors.groupingBy(ItemStack::getMaterialKey)).entrySet()
                .stream().sorted(Comparator.comparing(entry -> entry.getValue().size(), Comparator.reverseOrder()))
                .map(x -> x.getValue().stream()
                        .sorted(Comparator.comparing(stack -> stack.getAmount(), Comparator.reverseOrder())).toList())
                .flatMap(x -> x.stream())
                .toList();

        for (var index = 0; index < groups.size(); index++) {
            inventory.putInSlot(index, groups.get(index));
        }
    }

}
