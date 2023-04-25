package one.spectra.better_chests.message_handlers;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.inject.Inject;

import one.spectra.better_chests.Sorter;
import one.spectra.better_chests.inventory.SpectraInventory;

public class SortRequestHandler implements MessageHandler {

    private Sorter _sorter;

    @Inject
    public SortRequestHandler(Sorter sorter) {
        _sorter = sorter;
    }

    @Override
    public String getChannel() {
        return "spectra-chest:sort";
    }

    @Override
    public void handle(Player player, byte[] message) {
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        byte flag = in.readByte();
        var inventory = flag == 0
                ? new SpectraInventory(player.getInventory())
                : new SpectraInventory(player.getOpenInventory().getTopInventory());
        _sorter.sort(inventory);
    }

}
