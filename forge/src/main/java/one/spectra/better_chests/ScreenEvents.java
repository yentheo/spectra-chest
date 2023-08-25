package one.spectra.better_chests;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ScreenEvents {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void mainScreenEvent(ScreenEvent.Opening event) {
        LogUtils.getLogger().info("open");
        var screen = event.getNewScreen();
        if (screen instanceof ContainerScreen) {
            LogUtils.getLogger().info("open");
            var containerScreen = (ContainerScreen) screen;
            event.setNewScreen(new BetterContainerScreen(containerScreen.getMenu(), new Inventory(Minecraft.getInstance().player), containerScreen.getTitle()));
        } else if (screen instanceof InventoryScreen) {
            event.setNewScreen(new BetterInventoryScreen(Minecraft.getInstance().player));
        }
    }

}