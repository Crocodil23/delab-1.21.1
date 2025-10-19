package net.crocodil.delab.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.projectails.EvilSpitterBullet;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class EvilSpitterBulletModel<T extends EvilSpitterBullet> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Delab.MODID, "evil_spitter_bullet"), "main");
	private final ModelPart bullet;

	public EvilSpitterBulletModel(ModelPart root) {
		this.bullet = root.getChild("bullet");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bullet = partdefinition.addOrReplaceChild("bullet", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -24.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}
	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		bullet.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}


}