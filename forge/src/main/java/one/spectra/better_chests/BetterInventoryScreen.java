package one.spectra.better_chests;

import com.mojang.logging.LogUtils;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;

@ExcludeFromGeneratedCoverageReport
public class BetterInventoryScreen extends InventoryScreen {

    public BetterInventoryScreen(Player player) {
        super(player);
    }
    @Override
    public void init() {
        super.init();

        // var sortButtonImage = new ResourceLocation("better_chests:sort-button.png");        
        // var sortButton = new ImageButton(this.leftPos + this.imageWidth - 20, this.topPos + 5, 13, 9, 0, 0, 9,
        //         sortButtonImage, 13, 18, e -> {
        //             BetterChestsPacketHandler.INSTANCE.sendToServer(new SortRequest(true));
        //             e.setFocused(false);
        //         });
        // this.addRenderableWidget(sortButton);
    }

    @Override
    public void render(GuiGraphics p_282060_, int p_282533_, int p_281661_, float p_281873_) {
        super.render(p_282060_, p_282533_, p_281661_, p_281873_);
    }
}
