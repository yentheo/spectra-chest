package one.spectra.better_chests.message_handlers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.network.NetworkDirection;
import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.message_handlers.messages.Configuration;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationRequest;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationResponse;

public class GetConfingurationRequestHandler implements MessageHandler<GetConfigurationRequest> {

    @Override
    public void handle(Player player, GetConfigurationRequest message) {
        var chestBlock = getChestBlock(player);
        if (chestBlock != null) {
            var data = chestBlock.getPersistentData();
            var config = new Configuration();
            config.spread = data.getBoolean("better_chests:spread");
            ServerPlayer p = (ServerPlayer) player;
            BetterChestsPacketHandler.INSTANCE.sendTo(new GetConfigurationResponse(config), p.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    private ChestBlockEntity getChestBlock(Player player) {
        if (player.hasContainerOpen()) {
            if (player.containerMenu instanceof ChestMenu) {
                var chestMenu = (ChestMenu) player.containerMenu;
                var container = chestMenu.getContainer();
                if (container instanceof ChestBlockEntity) {
                    var chestBlock = (ChestBlockEntity) container;
                    return chestBlock;
                }
            }
        }
        return null;
    }
}
