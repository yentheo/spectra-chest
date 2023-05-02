package one.spectra.better_chests.abstractions.event;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.event.player.PlayerChannelEvent;
import org.bukkit.plugin.Plugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

public class PlayerChannelEventHandler implements EventHandler<PlayerChannelEvent> {

    private Plugin _plugin;
    private Logger _logger;

    @Inject
    public PlayerChannelEventHandler(Plugin plugin, Logger logger) {
        _plugin = plugin;
        _logger = logger;
    }

    @Override
    public void handle(PlayerChannelEvent event) {
        var features = new String[] { "sorting", "move-up", "move-down" };
        var mapper = new ObjectMapper();
        try {
            _logger.info("player joined channel: " + event.getChannel());
            if ("spectra-chest_client:features".equals(event.getChannel())) {
                var  buffer = mapper.writeValueAsBytes(features);
                event.getPlayer().sendPluginMessage(_plugin, "spectra-chest_client:features", buffer);
            }
        } catch (IOException e) {
            // gulp
        }
    }

}
