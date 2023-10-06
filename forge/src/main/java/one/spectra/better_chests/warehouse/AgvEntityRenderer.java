package one.spectra.better_chests.warehouse;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AgvEntityRenderer extends EntityRenderer<AgvEntity> {

    private AgvEntityModel _model;
    

    public AgvEntityRenderer(Context p_174300_) {
        super(p_174300_);
        this._model = new AgvEntityModel(p_174300_.bakeLayer(AgvEntityModel.LAYER_LOCATION));
    }
    

   public void render(AgvEntity p_115418_, float p_115419_, float p_115420_, PoseStack p_115421_, MultiBufferSource p_115422_, int p_115423_) {
      super.render(p_115418_, p_115419_, p_115420_, p_115421_, p_115422_, p_115423_);
      VertexConsumer vertexconsumer = p_115422_.getBuffer(this._model.renderType(this.getTextureLocation(p_115418_)));
      this._model.renderToBuffer(p_115421_, vertexconsumer, p_115423_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    //   p_115421_.popPose();
   }

    @Override
    public ResourceLocation getTextureLocation(AgvEntity p_114482_) {
        return new ResourceLocation("better_chests", "agv.png");
    }
    
}
