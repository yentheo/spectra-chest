package one.spectra.better_chests.abstractions;

public class SpectraItemStack implements ItemStack {

    private net.minecraft.world.item.ItemStack _itemStack;

    public SpectraItemStack(net.minecraft.world.item.ItemStack itemStack) {
        _itemStack = itemStack;
    }

    @Override
    public int getAmount() {
        return this._itemStack.getCount();
    }

    @Override
    public String getMaterialKey() {
        return this._itemStack.getDescriptionId();
    }

    @Override
    public net.minecraft.world.item.ItemStack getItemStack() {
        return this._itemStack;
    }

    @Override
    public ItemStack takeOne() {
        this._itemStack.shrink(1);
        var newStack = new net.minecraft.world.item.ItemStack(this._itemStack.getItem(), 1, this._itemStack.getTag());
        return new SpectraItemStack(newStack);
    }
}
