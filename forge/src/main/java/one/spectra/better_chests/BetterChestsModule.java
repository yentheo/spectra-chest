package one.spectra.better_chests;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import net.minecraft.server.MinecraftServer;
import one.spectra.better_chests.inventory.InventoryFactory;
import one.spectra.better_chests.inventory.SpectraInventoryFactory;
import one.spectra.better_chests.inventory.fillers.ColumnFiller;
import one.spectra.better_chests.inventory.fillers.DefaultFiller;
import one.spectra.better_chests.inventory.fillers.Filler;
import one.spectra.better_chests.inventory.fillers.InventoryFillerProvider;
import one.spectra.better_chests.inventory.fillers.RowFiller;
import one.spectra.better_chests.message_handlers.MessageHandler;
import one.spectra.better_chests.message_handlers.MoveDownRequestHandler;
import one.spectra.better_chests.message_handlers.MoveUpRequestHandler;
import one.spectra.better_chests.message_handlers.SortRequestHandler;

public class BetterChestsModule extends AbstractModule {

    public BetterChestsModule() {    }

    @Override
    protected void configure() {
        bind(InventoryFactory.class).to(SpectraInventoryFactory.class);

        bind(Sorter.class);
        bind(Mover.class);
        bind(InventoryFillerProvider.class);
        bind(DefaultFiller.class);

        Multibinder<Filler> fillerBinder = Multibinder.newSetBinder(binder(), Filler.class);
        fillerBinder.addBinding().to(RowFiller.class);
        fillerBinder.addBinding().to(ColumnFiller.class);

        var messageHandlerBinder = Multibinder.newSetBinder(binder(), MessageHandler.class);
        messageHandlerBinder.addBinding().to(SortRequestHandler.class);
        messageHandlerBinder.addBinding().to(MoveUpRequestHandler.class);
        messageHandlerBinder.addBinding().to(MoveDownRequestHandler.class);
    }
}
