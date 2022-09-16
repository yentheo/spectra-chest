package one.spectra.inventory.fillers;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import one.spectra.inventory.SpectraInventory;

public class DefaultFiller implements InventoryFiller {

    @Override
    public boolean canFill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        return itemStacks.size() <= inventory.getSize();
    }

    @Override
    public void fill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        for (var i = 0; i < itemStacks.size(); i++) {
            inventory.putInSlot(i, itemStacks.get(i));
        }
    }

}
