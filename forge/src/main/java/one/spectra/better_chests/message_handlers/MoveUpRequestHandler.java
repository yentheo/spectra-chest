package one.spectra.better_chests.message_handlers;

import com.google.inject.Inject;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import one.spectra.better_chests.Mover;
import one.spectra.better_chests.inventory.SpectraInventory;
import one.spectra.better_chests.message_handlers.messages.MoveUpRequest;

public class MoveUpRequestHandler implements MessageHandler<MoveUpRequest> {
    private Mover _mover;
    
    @Inject
    public MoveUpRequestHandler(Mover mover) {
        _mover = mover;
    }

    @Override
    public void handle(Player player, MoveUpRequest message) {
        if (player.hasContainerOpen()) {
            if (player.containerMenu instanceof ChestMenu) {
                var chestMenu = (ChestMenu)player.containerMenu;
                var sourceInventory = new SpectraInventory(player.getInventory());
                var destinationInventory = new SpectraInventory(chestMenu.getContainer());
                _mover.move(sourceInventory, destinationInventory);
            }
        }
    }

}
