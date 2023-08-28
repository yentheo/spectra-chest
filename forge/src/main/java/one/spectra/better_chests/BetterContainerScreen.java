package one.spectra.better_chests;

import com.mojang.logging.LogUtils;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.message_handlers.messages.SortRequest;

public class BetterContainerScreen extends ContainerScreen {

    private int _rowCount = 0;

    public BetterContainerScreen(ChestMenu chestMenu, Inventory playerInventory, Component chestTitle) {
        super(chestMenu, playerInventory, chestTitle);
        this._rowCount = chestMenu.getRowCount();
        LogUtils.getLogger().info("Better container screen");
    }

    @Override
    public void init() {
        super.init();
        var sortButtonImage = new ResourceLocation("better_chests:sort-button.png");
        var sortButtonContainer = new ImageButton(this.leftPos + this.imageWidth - 20, this.topPos + 5, 13, 9, 0, 0, 9,
                sortButtonImage, 13, 18, e -> {
                    BetterChestsPacketHandler.INSTANCE.sendToServer(new SortRequest(false));
                    e.setFocused(false);
                });
        this.addRenderableWidget(sortButtonContainer);

        var containerHeight = _rowCount * 18;

        var sortButtonInventory = new ImageButton(this.leftPos + this.imageWidth - 20, this.topPos + 5 + containerHeight + 14, 13, 9, 0, 0, 9,
                sortButtonImage, 13, 18, e -> {
                    BetterChestsPacketHandler.INSTANCE.sendToServer(new SortRequest(true));
                    e.setFocused(false);
                });
        this.addRenderableWidget(sortButtonInventory);

    }

    @Override
    public void render(GuiGraphics p_282060_, int p_282533_, int p_281661_, float p_281873_) {
        super.render(p_282060_, p_282533_, p_281661_, p_281873_);
    }
}
