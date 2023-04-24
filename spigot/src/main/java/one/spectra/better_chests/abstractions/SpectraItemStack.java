package one.spectra.better_chests.abstractions;

public class SpectraItemStack implements ItemStack {

    private org.bukkit.inventory.ItemStack _itemStack;

    public SpectraItemStack(org.bukkit.inventory.ItemStack itemStack) {
        _itemStack = itemStack;
    }

    @Override
    public int getAmount() {
        return this._itemStack.getAmount();
    }

    @Override
    public String getMaterialKey() {
        return this._itemStack.getType().getKey().getKey();
    }

    @Override
    public org.bukkit.inventory.ItemStack getItemStack() {
        return this._itemStack;
    }
}
