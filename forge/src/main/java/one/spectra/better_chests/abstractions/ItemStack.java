package one.spectra.better_chests.abstractions;

public interface ItemStack {
    int getAmount();
    String getMaterialKey();    
    net.minecraft.world.item.ItemStack getItemStack();
}
