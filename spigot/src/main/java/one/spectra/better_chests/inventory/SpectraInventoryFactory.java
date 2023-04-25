package one.spectra.better_chests.inventory;

import org.bukkit.Server;

import com.google.inject.Inject;

import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;

@ExcludeFromGeneratedCoverageReport
public class SpectraInventoryFactory implements InventoryFactory {

    private Server _server;

    @Inject
    public SpectraInventoryFactory(Server server) {
        _server = server;
    }

    @Override
    public Inventory create(int size) {
        var memoryInventory = _server.createInventory(null, size);
        return new SpectraInventory(memoryInventory);
    }

    public Inventory create(Inventory inventory) {
        var copied = create(inventory.getSize());
        copied.add(inventory.getItemStacks());
        return copied;
    }
}
