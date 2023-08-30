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
    public boolean canFill(Inventory inventory, List<List<ItemStack>> groups) {
        double columnCountAsDouble = inventory.getColumns();
        var rowsPerItem = groups.stream().map(x -> Math.ceil(x.size() / columnCountAsDouble));
        var totalRowsNeeded = rowsPerItem.mapToInt(Double::intValue).sum();

        return totalRowsNeeded <= inventory.getRows();
    }

    @Override
    public void fill(Inventory inventory, List<List<ItemStack>> groups) {
        _logger.info("Filling with row filler");
        var rowIndex = 0;
        for (var groupIndex = 0; groupIndex < groups.size(); groupIndex++) {
            var stacksInGroup = groups.get(groupIndex);
            var columnIndex = 0;
            for (var stackIndex = 0; stackIndex < stacksInGroup.size(); stackIndex++) {
                if (columnIndex == inventory.getColumns()) {
                    columnIndex = 0;
                    rowIndex++;
                }
                inventory.putInSlot(columnIndex + rowIndex * inventory.getColumns(), stacksInGroup.get(stackIndex));
                columnIndex++;
            }
            rowIndex++;
        }
    }

}
