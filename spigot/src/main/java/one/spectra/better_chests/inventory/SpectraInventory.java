package one.spectra.better_chests.inventory;

import java.util.ArrayList;
import org.bukkit.inventory.PlayerInventory;

import one.spectra.better_chests.BetterChestsPlugin;
import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;
import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.abstractions.SpectraItemStack;

@ExcludeFromGeneratedCoverageReport
public class SpectraInventory implements Inventory {
    private org.bukkit.inventory.Inventory _inventory;
    private int _skipSlots;
    private int _size;

    public SpectraInventory(int size) {
        this(BetterChestsPlugin.SERVER.createInventory(null, size));
    }

    public SpectraInventory(PlayerInventory playerInventory) {
        this(playerInventory, 9, 27);
    }

    public SpectraInventory(org.bukkit.inventory.Inventory inventory) {
        this(inventory, 0, inventory.getSize());
    }

    private SpectraInventory(org.bukkit.inventory.Inventory inventory, int skipSlots, int size) {
        _inventory = inventory;
        _skipSlots = skipSlots;
        _size = size;
    }

    public int getSize() {
        return _size;
    }

    public void putInSlot(int slot, ItemStack stack) {
        _inventory.setItem(slot + _skipSlots, stack.getItemStack());
    }

    public void add(ItemStack stack) {
        _inventory.addItem(stack.getItemStack());
    }

    public void clear() {
        for (int i = _skipSlots; i < _size + _skipSlots; i++) {
            _inventory.clear(i);
        }
    }

    public ArrayList<ItemStack> getItemStacks() {
        var stacks = new ArrayList<ItemStack>();
        for (int i = _skipSlots; i < _size + _skipSlots; i++) {
            var stack = _inventory.getItem(i);

            if (stack != null && !stack.getType().isAir()) {
                stacks.add(new SpectraItemStack(stack));
            }
        }
        return stacks;
    }

    @Override
    public int getRows() {
        return _size / getColumns();
    }

    @Override
    public int getColumns() {
        return 9;
    }
}
