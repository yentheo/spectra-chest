package one.spectra.better_chests.abstractions.event;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChannelEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.google.inject.Inject;

public class EventHub implements Listener {

    private Server _server;
    private Plugin _plugin;
    private Set<EventHandler<PlayerJoinEvent>> _playerJoinEventHandlers;
    private Set<EventHandler<PlayerChannelEvent>> _playerChannelEventsHandlers;

    @Inject
    public EventHub(Plugin plugin, Server server, Set<EventHandler<PlayerJoinEvent>> playerJoinEventHandlers, Set<EventHandler<PlayerChannelEvent>> playerChannelEventHandlers) {
        _server = server;
        _plugin = plugin;
        _playerJoinEventHandlers = playerJoinEventHandlers;
        _playerChannelEventsHandlers = playerChannelEventHandlers;
        _server.getMessenger().registerOutgoingPluginChannel(plugin, "spectra-chest_client:features");
    }

    public void register() {
        _server.getPluginManager().registerEvents(this, _plugin);
    }

    public void unregister() {
    }

    @org.bukkit.event.EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        _playerJoinEventHandlers.forEach(x -> x.handle(event));
    }

    @org.bukkit.event.EventHandler
    private void onPlayerChannel(PlayerChannelEvent event) {
        _playerChannelEventsHandlers.forEach(x -> x.handle(event));
    }
}
