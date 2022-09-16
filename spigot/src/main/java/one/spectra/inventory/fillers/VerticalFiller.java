package one.spectra.inventory.fillers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

import one.spectra.inventory.SpectraInventory;
import org.bukkit.inventory.meta.ItemMeta;

class ItemStackKey {
    Material material;
    ItemMeta itemMeta;

    public ItemStackKey(Material material, ItemMeta itemMeta) {
        this.material = material;
        this.itemMeta = itemMeta;
    }

    @Override
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof ItemStackKey))
            return false;
        var otherKey = (ItemStackKey) arg0;

        return material == otherKey.material && itemMeta == otherKey.itemMeta;
    }

}

class ItemStackGroup {
    ItemStackKey key;
    List<ItemStack> stacks;

    public ItemStackGroup(ItemStackKey key, List<ItemStack> stacks) {
        this.key = key;
        this.stacks = stacks;
    }
}

public class VerticalFiller implements InventoryFiller {

    @Override
    public boolean canFill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        var groups = itemStacks.stream()
                .collect(Collectors.groupingBy(x -> x.getType().getKey().toString() + x.getItemMeta().getAsString(),
                        Collectors.counting()));

        var maxStacksForItem = Collections.max(groups.values());

        return groups.size() <= 9 && maxStacksForItem <= inventory.getRowAmount();
    }

    @Override
    public void fill(List<ItemStack> itemStacks, SpectraInventory inventory) {
        var groups = itemStacks.stream()
                .collect(Collectors.groupingBy(x -> x.getType().getKey().toString() + x.getItemMeta().getAsString()));
        var columnIndex = 0;
        for (String key : groups.keySet()) {
            var stacks = groups.get(key);
            for (var i = 0; i < stacks.size(); i++) {
                inventory.putInSlot(columnIndex + (9 * i), stacks.get(i));
            }
            columnIndex++;
        }
    }

}
