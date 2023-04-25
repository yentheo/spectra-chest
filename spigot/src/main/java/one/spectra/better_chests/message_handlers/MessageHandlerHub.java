package one.spectra.better_chests.message_handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.inject.Inject;

public class MessageHandlerHub {

    private Server _server;
    private Plugin _plugin;
    private Set<MessageHandler> _messageHandlers;

    private List<Runnable> _registrations = new ArrayList<Runnable>();

    @Inject
    public MessageHandlerHub(Server server, Plugin plugin, Set<MessageHandler> messageHandlers) {
        _server = server;
        _plugin = plugin;
        _messageHandlers = messageHandlers;
    }

    public void register() {
        _messageHandlers.forEach(h -> {
            var listener = new PluginMessageListener() {
                public void onPluginMessageReceived(String channel, Player player, byte[] message) {
                    h.handle(player, message);
                }
            };
            _registrations.add(new Runnable() {
                public void run() {
                    _server.getMessenger().unregisterIncomingPluginChannel(_plugin, h.getChannel(), listener);
                }
            });
            _server.getMessenger().registerIncomingPluginChannel(_plugin, h.getChannel(), listener);
        });
    }

    public void unregister() {
        _registrations.forEach(x -> x.run());
    }
}
