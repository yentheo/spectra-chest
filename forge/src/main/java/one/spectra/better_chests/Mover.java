package one.spectra.better_chests;

import com.google.inject.Inject;

import one.spectra.better_chests.inventory.Inventory;
import one.spectra.better_chests.inventory.InventoryFactory;

public class Mover {

    private InventoryFactory _inventoryFactory;

    @Inject
    public Mover(InventoryFactory inventoryFactory) {
        _inventoryFactory = inventoryFactory;
    }

    public void move(Inventory source, Inventory destination) {
        var memorySource = _inventoryFactory.create(source);
        var memoryDestination = _inventoryFactory.create(destination);

        var stacksToAdd = memorySource.getItemStacks();
        var stacksThatWereNotAdded = memoryDestination.add(stacksToAdd);
        memorySource.clear();
        memorySource.add(stacksThatWereNotAdded);

        source.clear();
        destination.clear();

        var sourceStacks = memorySource.getItemStacks();
        for (var i = 0; i < sourceStacks.size(); i++) {
            source.putInSlot(i, sourceStacks.get(i));
        }
        var destinationStacks = memoryDestination.getItemStacks();
        for (var i = 0; i < destinationStacks.size(); i++) {
            destination.putInSlot(i, destinationStacks.get(i));
        }
    }
}
