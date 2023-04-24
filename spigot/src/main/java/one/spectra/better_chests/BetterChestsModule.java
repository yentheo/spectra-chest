package one.spectra.better_chests;

import org.bukkit.Server;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import one.spectra.better_chests.inventory.InventoryFactory;
import one.spectra.better_chests.inventory.SpectraInventoryFactory;
import one.spectra.better_chests.inventory.fillers.ColumnFiller;
import one.spectra.better_chests.inventory.fillers.DefaultFiller;
import one.spectra.better_chests.inventory.fillers.Filler;
import one.spectra.better_chests.inventory.fillers.InventoryFillerProvider;
import one.spectra.better_chests.inventory.fillers.RowFiller;

public class BetterChestsModule extends AbstractModule {

    private Server _server;

    public BetterChestsModule(Server server) {
        _server = server;
    }

    @Override
    protected void configure() {
        bind(InventoryFactory.class).to(SpectraInventoryFactory.class);
        bind(Server.class).toInstance(_server);
        bind(Sorter.class);
        bind(InventoryFillerProvider.class);
        bind(DefaultFiller.class);
        
        Multibinder<Filler> multibinder = Multibinder.newSetBinder(binder(), Filler.class);
        multibinder.addBinding().to(RowFiller.class);
        multibinder.addBinding().to(ColumnFiller.class);
    }
}
