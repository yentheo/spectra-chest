package one.spectra.better_chests.inventory.fillers;

import java.util.List;
import org.slf4j.Logger;

import com.google.inject.Inject;

import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;

public class RowFiller implements Filler {

    private Logger _logger;

    @Inject
    public RowFiller(Logger logger) {
        _logger = logger;
    }

    @Override
    public boolean canFill(Inventory inventory, List<List<ItemStack>> stacks) {
        var maxGroupSize = stacks.stream().map(x -> x.size()).max(Integer::compare).orElse(0);
        var columnsEnough = inventory.getColumns() >= maxGroupSize;
        var rowsEnough = inventory.getRows() >= stacks.size();

        return columnsEnough && rowsEnough;
    }

    @Override
    public void fill(Inventory inventory, List<List<ItemStack>> groups) {
        for (var rowIndex = 0; rowIndex < groups.size(); rowIndex++) {
            var stacksInGroup = groups.get(rowIndex);
            for (var columnIndex = 0; columnIndex < stacksInGroup.size(); columnIndex++) {
                inventory.putInSlot(columnIndex + rowIndex * inventory.getColumns(), stacksInGroup.get(columnIndex));
            }
        }
    }

}
