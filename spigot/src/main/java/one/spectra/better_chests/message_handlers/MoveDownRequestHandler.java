package one.spectra.better_chests.message_handlers;

import org.bukkit.entity.Player;

import com.google.inject.Inject;

import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;
import one.spectra.better_chests.Mover;
import one.spectra.better_chests.inventory.SpectraInventory;

@ExcludeFromGeneratedCoverageReport
public class MoveDownRequestHandler implements MessageHandler {
    @Override
    public String getChannel() {
        return "spectra-chest:move-down";
    }

    private Mover _mover;
    
    @Inject
    public MoveDownRequestHandler(Mover mover) {
        _mover = mover;
    }

    @Override
    public void handle(Player player, byte[] message) {
        var sourceInventory = new SpectraInventory(player.getOpenInventory().getTopInventory());
        var destinationInventory = new SpectraInventory(player.getInventory());
        _mover.move(sourceInventory, destinationInventory);
    }

}
