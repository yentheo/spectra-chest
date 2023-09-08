package one.spectra.better_chests.message_handlers;

import one.spectra.better_chests.abstractions.Player;
import one.spectra.better_chests.message_handlers.messages.ConfigureCurrentChestRequest;

public class ConfigureCurrentChestRequestHandler implements MessageHandler<ConfigureCurrentChestRequest> {

    @Override
    public void handle(Player player, ConfigureCurrentChestRequest message) {
        var openInventory = player.getOpenContainer();
        if (openInventory != null) {
            openInventory.setSpread(message.configuration.spread);
        }
    }
}
