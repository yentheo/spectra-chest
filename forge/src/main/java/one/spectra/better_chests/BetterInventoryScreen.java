package one.spectra.better_chests;

import com.mojang.logging.LogUtils;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.configuration.BetterChestsClientConfiguration;
import one.spectra.better_chests.message_handlers.messages.SortRequest;

@ExcludeFromGeneratedCoverageReport
public class BetterInventoryScreen extends InventoryScreen {

    private ImageButton _sortButton;

    public BetterInventoryScreen(Player player) {
        super(player);
    }

    @Override
    public void init() {
        super.init();
        var positionX = this.leftPos + this.imageWidth - 20;
        var positionY = this.topPos + 72;

        var sortButtonImage = new ResourceLocation("better_chests:sort-button.png");
        this._sortButton = new ImageButton(positionX, positionY, 13, 9, 0, 0, 9, sortButtonImage, 13, 18, e -> {
            BetterChestsPacketHandler.INSTANCE.sendToServer(new SortRequest(true, BetterChestsClientConfiguration.SPREAD.get(), BetterChestsClientConfiguration.SORT_ALPHABETICALLY.get()));
            e.setFocused(false);
        });
        this.addRenderableWidget(_sortButton);
    }

    @Override
    public void render(GuiGraphics p_282060_, int p_282533_, int p_281661_, float p_281873_) {
        var positionX = this.leftPos + this.imageWidth - 20;
        var positionY = this.topPos + 72;
        if (this.getRecipeBookComponent().isActive()) {
            positionX = this.leftPos + imageWidth - 20;
        }

        this._sortButton.setPosition(positionX, positionY);

        super.render(p_282060_, p_282533_, p_281661_, p_281873_);
    }
}
