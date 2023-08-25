package one.spectra.better_chests.inventory;

import com.google.inject.Inject;

public class SpectraInventoryFactory implements InventoryFactory {

    @Inject
    public SpectraInventoryFactory() {
    }

    @Override
    public Inventory create(int size) {
        var memoryInventory = new net.minecraft.world.SimpleContainer(size);
        return new SpectraInventory(memoryInventory);
    }

    public Inventory create(Inventory inventory) {
        var copied = create(inventory.getSize());
        copied.add(inventory.getItemStacks());
        return copied;
    }
}
