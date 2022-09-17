package one.spectra.inventory.fillers;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import one.spectra.inventory.SpectraInventory;

public class DefaultFiller implements InventoryFiller {

    @Override
    public boolean canFill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        return itemStacks.size() <= inventory.getSize();
    }

    @Override
    public void fill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        var groups = itemStacks.stream()
                .collect(Collectors.groupingBy(x -> x.getType().getKey().toString() + x.getItemMeta().getAsString()));
        var stacks = groups.values().stream()
                .sorted(Comparator.comparingInt(List<ItemStack>::size).reversed())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (var i = 0; i < stacks.size(); i++) {
            inventory.putInSlot(i, stacks.get(i));
        }
    }

}
