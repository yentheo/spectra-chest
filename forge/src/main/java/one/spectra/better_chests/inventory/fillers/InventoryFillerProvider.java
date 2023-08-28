package one.spectra.better_chests.inventory.fillers;

import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;

public class InventoryFillerProvider {

    private DefaultFiller _defaultFiller;
    private Set<Filler> _fillers;

    @Inject
    public InventoryFillerProvider(Set<Filler> fillers, DefaultFiller defaultFiller) {
        _fillers = fillers;
        _defaultFiller = defaultFiller;
    }

    public Filler getInventoryFiller(Inventory inventory, List<List<ItemStack>> stacks) {
        return _fillers.stream().filter(x -> x.canFill(inventory, stacks)).findFirst().orElse(_defaultFiller);
    }
}
