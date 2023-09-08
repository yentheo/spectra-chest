package one.spectra.better_chests.message_handlers;

import com.google.inject.Inject;

import one.spectra.better_chests.Mover;
import one.spectra.better_chests.abstractions.Player;
import one.spectra.better_chests.message_handlers.messages.MoveUpRequest;

public class MoveUpRequestHandler implements MessageHandler<MoveUpRequest> {
    private Mover _mover;

    @Inject
    public MoveUpRequestHandler(Mover mover) {
        _mover = mover;
    }

    @Override
    public void handle(Player player, MoveUpRequest message) {
        var sourceInventory = player.getInventory();
        var destinationInventory = player.getOpenContainer();
        if (destinationInventory != null) {
            _mover.move(sourceInventory, destinationInventory);
        }
    }

}
