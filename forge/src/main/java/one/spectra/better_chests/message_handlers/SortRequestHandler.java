package one.spectra.better_chests.message_handlers;

import com.google.inject.Inject;
import com.mojang.logging.LogUtils;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import one.spectra.better_chests.Sorter;
import one.spectra.better_chests.inventory.SpectraInventory;
import one.spectra.better_chests.message_handlers.messages.SortRequest;

public class SortRequestHandler implements MessageHandler<SortRequest> {

    private Sorter _sorter;

    @Inject
    public SortRequestHandler(Sorter sorter) {
        _sorter = sorter;
    }

    @Override
    public void handle(Player player, SortRequest message) {
        var inventory = message.sortPlayerInventory
                ? new SpectraInventory(player.getInventory())
                : new SpectraInventory(getOpenContainer(player));
        var spread = message.sortPlayerInventory
                ? false
                : getChestBlock(player).getPersistentData().getBoolean("better_chests:spread");

        LogUtils.getLogger().info("Should spread " + spread);

        _sorter.sort(inventory);
    }

    private Container getOpenContainer(Player player) {
        if (player.hasContainerOpen()) {
            if (player.containerMenu instanceof ChestMenu) {
                var chestMenu = (ChestMenu) player.containerMenu;
                return chestMenu.getContainer();
            }
        }
        return null;
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
