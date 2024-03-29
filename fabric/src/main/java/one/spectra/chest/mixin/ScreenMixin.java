package one.spectra.chest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import one.spectra.chest.InventoryType;
import one.spectra.chest.MoveDownButtonWidget;
import one.spectra.chest.MoveUpButtonWidget;
import one.spectra.chest.SortButtonWidget;
import one.spectra.chest.SpectraChestMod;
import net.minecraft.client.gui.screen.Screen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin(HandledScreen.class)
public class ScreenMixin extends Screen {
	@Shadow
	protected int x;
	@Shadow
	protected int y;
	@Shadow
	protected int backgroundWidth;
	@Shadow
	protected int backgroundHeight;

	protected ScreenMixin(Text title) {
		super(Text.empty().append("Test"));
	}

	@Inject(method = "init", at = @At("TAIL"))
	private void invsort$init(CallbackInfo callbackinfo) {
		initialize(callbackinfo);
	}

	private void initialize(CallbackInfo callbackinfo) {
		if (client == null || client.player == null)
			return;

		SpectraChestMod.LOGGER.info(client.player.currentScreenHandler.toString());
		var isGenericContainerScreen = client.player.currentScreenHandler instanceof GenericContainerScreenHandler;
		var isPlayerScreen = client.player.currentScreenHandler instanceof PlayerScreenHandler;
		if (!isGenericContainerScreen && !isPlayerScreen) {
			var screenHandler = client.player.currentScreenHandler;
			SpectraChestMod.LOGGER.info(screenHandler.toString());
			return;
		}

		int numSlots = client.player.currentScreenHandler.slots.size();
		var x = this.x + this.backgroundWidth - 20;
		if (numSlots >= 45) {
			var y = this.y + (numSlots > 36 ? (backgroundHeight - 95) : 6);
			var widget = new SortButtonWidget(x, y, InventoryType.PLAYER);
			this.addDrawableChild(widget);
		}
		if (numSlots >= 63) {
			var widget2 = new SortButtonWidget(x, this.y + 6, InventoryType.CHEST);
			this.addDrawableChild(widget2);
			var y = this.y + (numSlots > 36 ? (backgroundHeight - 95) : 6);
			this.addDrawableChild(new MoveUpButtonWidget(x - 11, y));
			this.addDrawableChild(new MoveDownButtonWidget(x - 22, y));
		}
	}
}
