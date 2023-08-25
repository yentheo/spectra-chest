package one.spectra.better_chests;

import com.mojang.logging.LogUtils;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class BetterInventoryScreen extends InventoryScreen {

    public BetterInventoryScreen(Player player) {
        super(player);
        LogUtils.getLogger().info("Better container screen");
    }
    @Override
    public void init() {
        super.init();
        ImageButton sortButton = new ImageButton(this.leftPos + this.imageWidth - 14 - 6, this.topPos + 18 * 4, 13, 9, 0, 0, 0,
                new ResourceLocation("better_chests:sort-button.png"), 13, 18, e -> {
                    LogUtils.getLogger().info("YAY");
                    
                });
        this.addRenderableWidget(sortButton);
        LogUtils.getLogger().info("Init");
    }

    @Override
    public void render(GuiGraphics p_282060_, int p_282533_, int p_281661_, float p_281873_) {
        super.render(p_282060_, p_282533_, p_281661_, p_281873_);
    }
}
