package one.spectra.better_chests.inventory;

public interface InventoryFactory {
    Inventory create(int size);
    Inventory create(Inventory inventory);
}
