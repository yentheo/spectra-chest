package one.spectra.better_chests;

import org.bukkit.plugin.java.JavaPlugin;
import com.google.inject.Guice;

import one.spectra.better_chests.abstractions.event.EventHub;
import one.spectra.better_chests.message_handlers.MessageHandlerHub;

@ExcludeFromGeneratedCoverageReport
public class BetterChestsPlugin extends JavaPlugin {
    private MessageHandlerHub _messageHandlerHub;
    private EventHub _eventHub;

    @Override
    public void onEnable() {
        var injector = Guice.createInjector(new BetterChestsModule(this, getServer()));
        _messageHandlerHub = injector.getInstance(MessageHandlerHub.class);
        _messageHandlerHub.register();
        _eventHub = injector.getInstance(EventHub.class);
        _eventHub.register();
        getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        _messageHandlerHub.unregister();
        _eventHub.unregister();
    }
}
