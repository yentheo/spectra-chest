package one.spectra.chest.abstractions;

public class Inventory {
    private net.minecraft.inventory.Inventory inventory;

    public Inventory(net.minecraft.inventory.Inventory inventory) {
        this.inventory = inventory;
    }

    public ItemStack getStack(int slot) {
        return new ItemStack(this.inventory.getStack(slot));
    }
}
