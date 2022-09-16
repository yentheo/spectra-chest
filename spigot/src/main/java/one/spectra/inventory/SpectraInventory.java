package one.spectra.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SpectraInventory {
    private Inventory inventory;
    private int skipSlots;
    private int size;

    public SpectraInventory(PlayerInventory playerInventory) {
        this(playerInventory, 9, 27);
    }

    public SpectraInventory(Inventory inventory) {
        this(inventory, 0, inventory.getSize());
    }

    private SpectraInventory(Inventory inventory, int skipSlots, int size) {
        this.inventory = inventory;
        this.skipSlots = skipSlots;
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void putInSlot(int slot, ItemStack stack) {
        inventory.setItem(slot + skipSlots, stack);
    }

    public int getRowAmount() {
        return size / 9;
    }
}
