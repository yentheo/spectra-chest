package one.spectra.better_chests.abstractions;

public interface ItemStack {
    int getAmount();
    String getMaterialKey();
    org.bukkit.inventory.ItemStack getItemStack();
}
