package one.spectra.inventory.fillers;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import one.spectra.inventory.SpectraInventory;

public interface InventoryFiller {
    boolean canFill(List<ItemStack> itemStacks, SpectraInventory inventory);
    void fill(List<ItemStack> itemStacks, SpectraInventory inventory);
}
