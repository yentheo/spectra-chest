package one.spectra;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Merger {
    public List<ItemStack> merge(List<ItemStack> original) {
        var merged = new ArrayList<ItemStack>();
        for (ItemStack itemStack : original) {
            if (itemStack.getMaxStackSize() > 1) {
                var possibleFirstFreeStack = merged.stream()
                        .filter(x -> x.getMaxStackSize() > 1 && x.getAmount() < x.getMaxStackSize())
                        .filter(x -> x.getItemMeta().equals(itemStack.getItemMeta())
                                && x.getType().equals(itemStack.getType()))
                        .findFirst();
                if (possibleFirstFreeStack.isPresent()) {
                    var firstFreeStack = possibleFirstFreeStack.get();
                    var amountToAdd = itemStack.getAmount();
                    var newAmount = firstFreeStack.getAmount() + amountToAdd;
                    var overflow = newAmount - itemStack.getMaxStackSize();
                    if (overflow > 0) {
                        firstFreeStack.setAmount(amountToAdd);
                        itemStack.setAmount(overflow);
                        merged.add(itemStack);
                    } else {
                        firstFreeStack.setAmount(newAmount);
                    }
                } else {
                    merged.add(itemStack);
                }
            } else {
                merged.add(itemStack);
            }
        }
        return merged;
    }
}
