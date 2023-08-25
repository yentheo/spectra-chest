package one.spectra.better_chests;

import com.google.inject.Inject;

import one.spectra.better_chests.inventory.Inventory;
import one.spectra.better_chests.inventory.InventoryFactory;
import one.spectra.better_chests.inventory.fillers.InventoryFillerProvider;

public class Sorter {

    private InventoryFactory _inventoryFactory;
    private InventoryFillerProvider _InventoryFillerProvider;

    @Inject
    public Sorter(InventoryFactory inventoryFactory, InventoryFillerProvider inventoryFillerProvider) {
        _inventoryFactory = inventoryFactory;
        _InventoryFillerProvider = inventoryFillerProvider;
    }

    public void sort(Inventory inventory) {
        var itemStacks = inventory.getItemStacks();

        var tempInventory = this._inventoryFactory.create(inventory.getSize());
        itemStacks.forEach(x -> tempInventory.add(x));
        inventory.clear();

        var sortedStacks = tempInventory.getItemStacks();

        var filler = _InventoryFillerProvider.getInventoryFiller(inventory, sortedStacks);
        filler.fill(inventory, sortedStacks);
    }
}