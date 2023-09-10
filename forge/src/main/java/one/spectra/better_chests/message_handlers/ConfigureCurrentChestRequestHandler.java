package one.spectra.better_chests.message_handlers;

import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.google.inject.Inject;

import one.spectra.better_chests.abstractions.Player;
import one.spectra.better_chests.message_handlers.messages.ConfigureCurrentChestRequest;

public class ConfigureCurrentChestRequestHandler implements MessageHandler<ConfigureCurrentChestRequest> {

    private Logger _logger;

    @Inject
    public ConfigureCurrentChestRequestHandler(Logger logger) {
        _logger = logger;
    }

    @Override
    public void handle(Player player, ConfigureCurrentChestRequest message) {
        _logger.info("Setting config");
        var openInventory = player.getOpenContainer();
        if (openInventory != null) {
            openInventory.setSpread(message.configuration.spread);
            Executors.newCachedThreadPool().submit(() -> {
                _logger.info("Set config");
            });
        }
    }
}
