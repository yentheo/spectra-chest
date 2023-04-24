package one.spectra.better_chests;

import one.spectra.better_chests.abstractions.ItemStack;

public class FakeItemStack implements ItemStack {

    private int _amount;
    private String _materialKey;

    public FakeItemStack(int amount, String materialKey) {
        _amount = amount;
        _materialKey = materialKey;
    }

    @Override
    public int getAmount() {
        return _amount;
    }

    @Override
    public String getMaterialKey() {
        return _materialKey;
    }

    @Override
    public org.bukkit.inventory.ItemStack getItemStack() {
        return new org.bukkit.inventory.ItemStack(null, _amount);
    }

    @Override
    public String toString() {
        return this.getMaterialKey() + ": " + this.getAmount();
    }
}
