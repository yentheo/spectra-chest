package one.spectra.better_chests.abstractions.event;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.google.inject.Inject;

public class PlayerJoinEventHandler implements EventHandler<PlayerJoinEvent> {

    private Plugin _plugin;
    private Logger _logger;

    @Inject
    public PlayerJoinEventHandler(Plugin plugin, Logger logger) {
        _plugin = plugin;
        _logger = logger;
    }

    @Override
    public void handle(PlayerJoinEvent event) {
    }

}
