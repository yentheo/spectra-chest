package one.spectra.abstractions;

public class Inventory {
    private org.bukkit.inventory.Inventory inventory;
    public int getSize() {
        return this.inventory.getSize();
    }
}
