package one.spectra.better_chests.message_handlers;

import com.google.inject.Inject;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import one.spectra.better_chests.Mover;
import one.spectra.better_chests.inventory.SpectraInventory;
import one.spectra.better_chests.message_handlers.messages.MoveDownRequest;

public class MoveDownRequestHandler implements MessageHandler<MoveDownRequest> {
    private Mover _mover;

    @Inject
    public MoveDownRequestHandler(Mover mover) {
        _mover = mover;
    }

    @Override
    public void handle(Player player, MoveDownRequest message) {
        if (player.hasContainerOpen()) {
            if (player.containerMenu instanceof ChestMenu) {
                var chestMenu = (ChestMenu)player.containerMenu;
                var sourceInventory = new SpectraInventory(chestMenu.getContainer());
                var destinationInventory = new SpectraInventory(player.getInventory());
                _mover.move(sourceInventory, destinationInventory);
            }
        }
    }

}
