package one.spectra.better_chests.inventory;

import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;

@ExcludeFromGeneratedCoverageReport
public class SpectraInventoryFactory implements InventoryFactory {
    @Override
    public Inventory create(int size) {
        return new SpectraInventory(size);
    }
}
