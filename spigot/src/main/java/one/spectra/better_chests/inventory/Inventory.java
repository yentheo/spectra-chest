package one.spectra.better_chests.inventory;

import java.util.List;

import one.spectra.better_chests.abstractions.ItemStack;

public interface Inventory {
    void clear();
    List<ItemStack> getItemStacks();
    void putInSlot(int slot, ItemStack stack);
    void add(ItemStack stack);
    int getSize();
    int getRows();
    int getColumns();
}
