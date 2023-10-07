package one.spectra.better_chests.inventory;

import net.minecraft.world.Container;

public interface InventoryCreator {
    Inventory create(int size);
    Inventory create(Container container);
    Inventory create(Inventory inventory);
}
