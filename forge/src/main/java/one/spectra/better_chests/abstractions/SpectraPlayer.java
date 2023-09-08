package one.spectra.better_chests.abstractions;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraftforge.network.NetworkDirection;
import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;
import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.inventory.SpectraInventory;

@ExcludeFromGeneratedCoverageReport
public class SpectraPlayer implements Player {
    private net.minecraft.world.entity.player.Player _player;

    public SpectraPlayer(net.minecraft.world.entity.player.Player player) {
        _player = player;
    }

    public SpectraInventory getOpenContainer() {
        if (_player.hasContainerOpen()) {
            if (_player.containerMenu instanceof ChestMenu) {
                var chestMenu = (ChestMenu) _player.containerMenu;
                return new SpectraInventory(chestMenu.getContainer());
            }
        }
        return null;
    }

    public SpectraInventory getInventory() {
        return new SpectraInventory(_player.getInventory());
    }

    @Override
    public <TMessage> void sendTo(TMessage message) {
        if (_player instanceof ServerPlayer) {
            var p = (ServerPlayer)_player;
            BetterChestsPacketHandler.INSTANCE.sendTo(message, p.connection.connection,
                    NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}
