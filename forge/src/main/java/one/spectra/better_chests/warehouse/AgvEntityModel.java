// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package one.spectra.better_chests.warehouse;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class AgvEntityModel extends EntityModel<AgvEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "agv"), "main");
	private final ModelPart main;

	public AgvEntityModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -5.0F, -6.7643F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 49).addBox(-6.5858F, -5.0F, -8.1785F, 13.1716F, 16.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(38, 49).addBox(3.0F, -23.0F, 9.2357F, 3.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 49).mirror().addBox(-6.0F, -23.0F, 9.2357F, 3.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, 0.7643F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 49).addBox(-6.0F, -16.0F, -13.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5355F, 11.0F, 5.2565F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r2 = main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 49).addBox(-1.0F, -16.0F, -13.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8995F, 11.0F, 1.721F, 0.0F, -0.7854F, 0.0F));

		PartDefinition forks = main.addOrReplaceChild("forks", CubeListBuilder.create(), PartPose.offset(6.0F, 11.0F, 15.2357F));

		PartDefinition right_fork = forks.addOrReplaceChild("right_fork", CubeListBuilder.create().texOffs(0, 2).addBox(-2.2929F, -1.0F, 9.7071F, 1.5858F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(21, 33).addBox(-3.0F, -1.0F, -5.0F, 3.0F, 1.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition cube_r3 = right_fork.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(5, 1).addBox(-3.0F, -1.0F, 6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0711F, 0.0F, 7.1716F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = right_fork.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 6).addBox(-3.0F, -1.0F, 6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.6569F, 0.0F, 7.1716F, 0.0F, -0.7854F, 0.0F));

		PartDefinition left_fork = forks.addOrReplaceChild("left_fork", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(0.7071F, -1.0F, 9.7071F, 1.5858F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 33).mirror().addBox(0.0F, -1.0F, -5.0F, 3.0F, 1.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-12.0F, -1.0F, 0.0F));

		PartDefinition cube_r5 = left_fork.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 6).mirror().addBox(2.0F, -1.0F, 6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.6569F, 0.0F, 7.1716F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r6 = left_fork.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(5, 1).mirror().addBox(2.0F, -1.0F, 6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0711F, 0.0F, 7.1716F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
	@Override
	public void setupAnim(AgvEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}