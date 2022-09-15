package one.spectra.chest.abstractions;

public class ItemStack {
    private net.minecraft.item.ItemStack itemStack;

    public ItemStack(net.minecraft.item.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Item getItem() {
        return new Item(this.itemStack.getItem());
    }

    public int getCount() {
        return this.itemStack.getCount();
    }

    public void setCount(int count) {
        this.itemStack.setCount(count);
    }
}
