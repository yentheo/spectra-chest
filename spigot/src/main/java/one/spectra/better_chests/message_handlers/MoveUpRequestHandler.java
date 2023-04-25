package one.spectra.better_chests.message_handlers;

import org.bukkit.entity.Player;

import com.google.inject.Inject;

import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;
import one.spectra.better_chests.Mover;
import one.spectra.better_chests.inventory.SpectraInventory;

@ExcludeFromGeneratedCoverageReport
public class MoveUpRequestHandler implements MessageHandler {
    @Override
    public String getChannel() {
        return "spectra-chest:move-up";
    }

    private Mover _mover;
    
    @Inject
    public MoveUpRequestHandler(Mover mover) {
        _mover = mover;
    }

    @Override
    public void handle(Player player, byte[] message) {
        var sourceInventory = new SpectraInventory(player.getInventory());
        var destinationInventory = new SpectraInventory(player.getOpenInventory().getTopInventory());
        _mover.move(sourceInventory, destinationInventory);
    }

}
