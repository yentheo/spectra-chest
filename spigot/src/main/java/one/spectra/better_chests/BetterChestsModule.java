package one.spectra.better_chests;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import one.spectra.better_chests.inventory.InventoryFactory;
import one.spectra.better_chests.inventory.SpectraInventoryFactory;
import one.spectra.better_chests.inventory.fillers.ColumnFiller;
import one.spectra.better_chests.inventory.fillers.DefaultFiller;
import one.spectra.better_chests.inventory.fillers.Filler;
import one.spectra.better_chests.inventory.fillers.InventoryFillerProvider;
import one.spectra.better_chests.inventory.fillers.RowFiller;
import one.spectra.better_chests.message_handlers.MessageHandler;
import one.spectra.better_chests.message_handlers.MessageHandlerHub;
import one.spectra.better_chests.message_handlers.MoveDownRequestHandler;
import one.spectra.better_chests.message_handlers.MoveUpRequestHandler;
import one.spectra.better_chests.message_handlers.SortRequestHandler;

@ExcludeFromGeneratedCoverageReport
public class BetterChestsModule extends AbstractModule {

    private Server _server;
    private Plugin _plugin;

    public BetterChestsModule(Plugin plugin, Server server) {
        _server = server;
        _plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(InventoryFactory.class).to(SpectraInventoryFactory.class);
        bind(Server.class).toInstance(_server);
        bind(Plugin.class).toInstance(_plugin);
        bind(MessageHandlerHub.class).asEagerSingleton();
        bind(Sorter.class);
        bind(Mover.class);
        bind(InventoryFillerProvider.class);
        bind(DefaultFiller.class);

        Multibinder<Filler> fillerBinder = Multibinder.newSetBinder(binder(), Filler.class);
        fillerBinder.addBinding().to(RowFiller.class);
        fillerBinder.addBinding().to(ColumnFiller.class);

        Multibinder<MessageHandler> messageHandlerBinder = Multibinder.newSetBinder(binder(), MessageHandler.class);
        messageHandlerBinder.addBinding().to(SortRequestHandler.class);
        messageHandlerBinder.addBinding().to(MoveUpRequestHandler.class);
        messageHandlerBinder.addBinding().to(MoveDownRequestHandler.class);
    }
}
