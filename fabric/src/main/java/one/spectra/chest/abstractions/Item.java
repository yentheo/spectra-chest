package one.spectra.chest.abstractions;

public class Item {
    private net.minecraft.item.Item item;

    public Item(net.minecraft.item.Item item) {
        this.item = item;
    }

    public int getMaxCount() {
        return this.item.getMaxCount();
    }

    public boolean isItem(net.minecraft.item.Item item) {
        return this.item == item;
    }

    public boolean isItem(Class<?> cls) {
        return cls.isInstance(this.item);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Item)) {
            return false;
        }

        Item c = (Item) o;

        return c.item == this.item;
    }
}
