package one.spectra.better_chests.inventory.fillers;

import java.util.List;
import org.slf4j.Logger;

import com.google.inject.Inject;

import one.spectra.better_chests.abstractions.ItemStack;
import one.spectra.better_chests.inventory.Inventory;

public class ColumnFiller implements Filler {

    private Logger _logger;
    @Inject
    public ColumnFiller(Logger logger) {
        _logger = logger;
    }
    @Override
    public boolean canFill(Inventory inventory, List<List<ItemStack>> groups) {
        var maxGroupSize = groups.stream().map(x -> x.size()).max(Integer::compare).orElse(0);
        var columnsEnough = inventory.getColumns() >= groups.size();
        var rowsEnough = inventory.getRows() >= maxGroupSize;

        return columnsEnough && rowsEnough;
    }

    @Override
    public void fill(Inventory inventory, List<List<ItemStack>> groups) {
        for (var columnIndex = 0; columnIndex < groups.size(); columnIndex++) {
            var stacksInGroup = groups.get(columnIndex);
            for (var rowIndex = 0; rowIndex < stacksInGroup.size(); rowIndex++) {
                inventory.putInSlot(columnIndex + rowIndex * inventory.getColumns(), stacksInGroup.get(rowIndex));
            }
        }
    }

}
