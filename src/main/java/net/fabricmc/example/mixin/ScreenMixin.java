package net.fabricmc.example.mixin;

import net.fabricmc.example.Inventory;
import net.fabricmc.example.MoveDownButtonWidget;
import net.fabricmc.example.MoveUpButtonWidget;
import net.fabricmc.example.SortButtonWidget;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.text.Text;

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
		super(title);
	}

	@Inject(method = "init", at = @At("TAIL"))
	private void invsort$init(CallbackInfo callbackinfo) {
		if (client == null || client.player == null)
			return;

		int numSlots = client.player.currentScreenHandler.slots.size();
		if (numSlots >= 45) {
			var x = this.x + this.backgroundWidth - 20;
			var y = this.y + (numSlots > 36 ? (backgroundHeight - 95) : 6);
			var widget = new SortButtonWidget(x, y, Inventory.PLAYER);
			this.addDrawableChild(widget);
		}
		if (numSlots >= 63) {
			var widget2 = new SortButtonWidget(this.x + this.backgroundWidth - 20, this.y + 6, Inventory.CHEST);
			this.addDrawableChild(widget2);
			var x = this.x + this.backgroundWidth - 20;
			var y = this.y + (numSlots > 36 ? (backgroundHeight - 95) : 6);
			this.addDrawable(new MoveUpButtonWidget(x - 11, y));
			this.addDrawable(new MoveDownButtonWidget(x - 22, y));
		}
	}
}
