package one.spectra.better_chests.message_handlers;

import com.mojang.logging.LogUtils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import one.spectra.better_chests.message_handlers.messages.ConfigureCurrentChestRequest;

public class ConfigureCurrentChestRequestHandler implements MessageHandler<ConfigureCurrentChestRequest> {

    @Override
    public void handle(Player player, ConfigureCurrentChestRequest message) {
        LogUtils.getLogger().info("Setting container");
        var chestBlock = getChestBlock(player);
        if (chestBlock  != null) {
            var data = chestBlock.getPersistentData();
            data.putBoolean("better_chests:spread", message.configuration.spread);
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
