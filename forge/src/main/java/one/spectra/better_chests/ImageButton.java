package one.spectra.better_chests;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ImageButton extends Button {
   protected final ResourceLocation resourceLocation;
   protected final int xTexStart;
   protected final int yTexStart;
   protected final int yDiffTex;
   protected final int textureWidth;
   protected final int textureHeight;

   public ImageButton(int p_169011_, int p_169012_, int p_169013_, int p_169014_, int p_169015_, int p_169016_, ResourceLocation p_169017_, Button.OnPress p_169018_) {
      this(p_169011_, p_169012_, p_169013_, p_169014_, p_169015_, p_169016_, p_169014_, p_169017_, 256, 256, p_169018_);
   }

   public ImageButton(int x, int y, int width, int height, int xTextStart, int yTextStart, int yDiffTex, ResourceLocation resourceLocation, Button.OnPress p_94277_) {
      this(x, y, width, height, xTextStart, yTextStart, yDiffTex, resourceLocation, 256, 256, p_94277_);
   }

   public ImageButton(int x, int y, int width, int height, int xTextStart, int yTextStart, int yDiffTex, ResourceLocation resourceLocation, int textureWidth, int textureHeight, Button.OnPress p_94240_) {
      this(x, y, width, height, xTextStart, yTextStart, yDiffTex, resourceLocation, textureWidth, textureHeight, p_94240_, CommonComponents.EMPTY);
   }

   public ImageButton(int x, int y, int width, int height, int xTextStart, int yTextStart, int yDiffTex, ResourceLocation resourceLocation, int textureWidth, int textureHeight, Button.OnPress p_94266_, Component p_94267_) {
      super(x, y, width, height, p_94267_, p_94266_, DEFAULT_NARRATION);
      this.textureWidth = textureWidth;
      this.textureHeight = textureHeight;
      this.xTexStart = xTextStart;
      this.yTexStart = yTextStart;
      this.yDiffTex = yDiffTex;
      this.resourceLocation = resourceLocation;
   }

   public void renderWidget(GuiGraphics p_283502_, int p_281473_, int p_283021_, float p_282518_) {
      this.renderTexture(p_283502_, this.resourceLocation, this.getX(), this.getY(), this.xTexStart, this.yTexStart, this.yDiffTex, this.width, this.height, this.textureWidth, this.textureHeight);
   }
}