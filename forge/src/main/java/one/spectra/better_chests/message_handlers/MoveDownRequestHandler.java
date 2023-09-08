package one.spectra.better_chests.message_handlers;

import com.google.inject.Inject;

import one.spectra.better_chests.Mover;
import one.spectra.better_chests.abstractions.Player;
import one.spectra.better_chests.message_handlers.messages.MoveDownRequest;

public class MoveDownRequestHandler implements MessageHandler<MoveDownRequest> {
    private Mover _mover;

    @Inject
    public MoveDownRequestHandler(Mover mover) {
        _mover = mover;
    }

    @Override
    public void handle(Player player, MoveDownRequest message) {
        var destinationInventory = player.getInventory();
        var sourceInventory = player.getOpenContainer();
        if (destinationInventory != null) {
            _mover.move(sourceInventory, destinationInventory);
        }
    }

}
