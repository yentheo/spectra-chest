package one.spectra.better_chests.message_handlers;

import com.google.inject.Inject;
import one.spectra.better_chests.Sorter;
import one.spectra.better_chests.abstractions.Player;
import one.spectra.better_chests.message_handlers.messages.SortRequest;

public class SortRequestHandler implements MessageHandler<SortRequest> {

    private Sorter _sorter;

    @Inject
    public SortRequestHandler(Sorter sorter) {
        _sorter = sorter;
    }

    @Override
    public void handle(Player player, SortRequest message) {
        var inventory = message.sortPlayerInventory ? player.getInventory() : player.getOpenContainer();
        _sorter.sort(inventory);
    }
}
