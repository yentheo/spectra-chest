package one.spectra.better_chests;
import net.minecraft.world.item.Items;
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
    public net.minecraft.world.item.ItemStack getItemStack() {
        return new net.minecraft.world.item.ItemStack(Items.AIR, _amount);
    }

    @Override
    public String toString() {
        return this.getMaterialKey() + ": " + this.getAmount();
    }
}
