package one.spectra.better_chests.inventory.fillers;

import java.util.ArrayList;
import java.util.List;

import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;

public class InventoryFillerProvider {

    private List<Filler> _fillers;

    public InventoryFillerProvider() {
        _fillers = new ArrayList<>();
        _fillers.add(new RowFiller());
        _fillers.add(new ColumnFiller());
    }

    public Filler getInventoryFiller(Inventory inventory, List<ItemStack> stacks) {
        return _fillers.stream().filter(x -> x.canFill(inventory, stacks)).findFirst().orElse(new DefaultFiller());
    }
}
