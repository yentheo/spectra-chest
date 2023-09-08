package one.spectra.better_chests;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.mojang.logging.LogUtils;

import one.spectra.better_chests.inventory.InventoryFactory;
import one.spectra.better_chests.inventory.SpectraInventoryFactory;
import one.spectra.better_chests.inventory.fillers.ColumnFiller;
import one.spectra.better_chests.inventory.fillers.DefaultFiller;
import one.spectra.better_chests.inventory.fillers.Filler;
import one.spectra.better_chests.inventory.fillers.InventoryFillerProvider;
import one.spectra.better_chests.inventory.fillers.RowFiller;
import one.spectra.better_chests.message_handlers.ConfigureCurrentChestRequestHandler;
import one.spectra.better_chests.message_handlers.MessageHandler;
import one.spectra.better_chests.message_handlers.MessageHandlerHub;
import one.spectra.better_chests.message_handlers.MessageService;
import one.spectra.better_chests.message_handlers.MoveDownRequestHandler;
import one.spectra.better_chests.message_handlers.MoveUpRequestHandler;
import one.spectra.better_chests.message_handlers.SortRequestHandler;

@ExcludeFromGeneratedCoverageReport
public class BetterChestsModule extends AbstractModule {

    public BetterChestsModule() {
    }

    @Override
    protected void configure() {
        bind(Logger.class).toInstance(LogUtils.getLogger());
        bind(InventoryFactory.class).to(SpectraInventoryFactory.class);

        bind(Sorter.class);
        bind(Mover.class);
        bind(InventoryFillerProvider.class);
        bind(Filler.class).annotatedWith(Names.named("defaultFiller")).to(DefaultFiller.class);
        bind(RowFiller.class);
        bind(ColumnFiller.class);
        bind(MessageService.class);
        bind(MessageHandlerHub.class).asEagerSingleton();

        bind(new TypeLiteral<List<Filler>>() {
        }).toProvider(new Provider<List<Filler>>() {

            @Inject
            RowFiller rowFiller;
            @Inject
            ColumnFiller columnFiller;

            @Override
            public List<Filler> get() {
                var fillers = new ArrayList<Filler>();
                fillers.add(rowFiller);
                fillers.add(columnFiller);
                return fillers;
            }
        });

        var messageHandlerBinder = Multibinder.newSetBinder(binder(), MessageHandler.class);
        messageHandlerBinder.addBinding().to(SortRequestHandler.class);
        messageHandlerBinder.addBinding().to(ConfigureCurrentChestRequestHandler.class);
        messageHandlerBinder.addBinding().to(MoveUpRequestHandler.class);
        messageHandlerBinder.addBinding().to(MoveDownRequestHandler.class);
    }
}
