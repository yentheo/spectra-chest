package one.spectra.better_chests.inventory;

import java.util.List;
import java.util.Optional;
import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.message_handlers.messages.Configuration;

public interface Inventory {
    void clear();
    List<ItemStack> getItemStacks();
    void putInSlot(int slot, ItemStack stack);
    void add(ItemStack stack);
    List<ItemStack> add(List<ItemStack> stacks);
    Configuration geConfiguration();
    void setSpread(boolean value);
    Boolean getSpread();
    void setAlphabeticalSort(boolean value);
    Boolean getAlphabeticalSort();
    int getSize();
    int getRows();
    int getColumns();
}
