package one.spectra.better_chests;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@ExcludeFromGeneratedCoverageReport
public class ScreenEvents {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void mainScreenEvent(ScreenEvent.Opening event) {
        var screen = event.getNewScreen();
        if (screen instanceof ContainerScreen) {
            var containerScreen = (ContainerScreen) screen;
            var minecraft = Minecraft.getInstance();
            event.setNewScreen(new BetterContainerScreen(containerScreen.getMenu(), new Inventory(minecraft.player), containerScreen.getTitle()));
        }
    }

}