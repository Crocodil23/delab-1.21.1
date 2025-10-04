package net.crocodil.delab.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.crocodil.delab.Delab;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class AbominationArmorModel<T extends LivingEntity> extends HumanoidModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION_INNER = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(Delab.MODID, "abomination_armor_layer_2"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_OUTER = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(Delab.MODID, "abomination_armor_layer_1"), "main");
	public final ModelPart leftArm;
	public final ModelPart body;
	public final ModelPart rightArm;
	public final ModelPart head;
	public final ModelPart leftLeg;
	public final ModelPart rightLeg;
	private static final float deform_outer = 0.0F;

	public AbominationArmorModel(ModelPart root) {
		super(root);
		this.leftArm = root.getChild("left_arm");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.head = root.getChild("head");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
	}

	public static LayerDefinition createInnerLayer() {
		return createBodyLayer(false,  64, 64);
	}
	public static LayerDefinition createOuterLayer() {
		return createBodyLayer(true, 128, 128);
	}
	public static LayerDefinition createBodyLayer(boolean isOuter, int tex_x, int tex_y) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		if(isOuter) {
			PartDefinition headPart = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 24).addBox(-4.5F, -2.0F, -5.0F, 9.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(40, 20).addBox(-4.5F, -7.0F, -5.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(18, 42).addBox(2.5F, -5.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(52, 49).addBox(0.5F, -5.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(54, 49).addBox(-1.5F, -5.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(58, 5).addBox(-3.5F, -5.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(44, 35).addBox(-3.5F, -1.0F, -5.0F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(50, 16).addBox(-3.5F, -2.0F, 5.0F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(20, 36).addBox(-4.5F, -9.0F, 5.0F, 9.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(40, 22).addBox(-4.5F, -9.0F, -5.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(0, 10).addBox(-4.5F, -9.0F, -5.0F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
					.texOffs(20, 10).addBox(4.5F, -9.0F, -5.0F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
					.texOffs(0, 0).addBox(-4.5F, -9.0F, -5.0F, 9.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
					.texOffs(38, 0).addBox(-5.5F, -7.0F, -6.0F, 11.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(24, 27).addBox(-5.5F, -6.0F, -3.3F, 1.0F, 0.0F, 9.0F, new CubeDeformation(0.0F))
					.texOffs(0, 33).addBox(4.5F, -6.0F, -3.3F, 1.0F, 0.0F, 9.0F, new CubeDeformation(0.0F))
					.texOffs(40, 19).addBox(-4.5F, -6.0F, 5.0F, 9.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(36, 47).addBox(-4.5F, -5.0F, -5.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(26, 59).addBox(3.5F, -5.0F, -5.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(58, 18).addBox(2.5F, -3.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(58, 24).addBox(0.5F, -3.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(60, 5).addBox(-1.5F, -3.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(60, 17).addBox(-3.5F, -3.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(18, 60).addBox(1.5F, -4.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(60, 18).addBox(-0.5F, -4.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(60, 19).addBox(-2.5F, -4.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition cube_r1 = headPart.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 20).addBox(-0.5F, -3.45F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
					.texOffs(18, 58).addBox(-10.5F, -3.45F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -3.0F, -5.0F, -0.5236F, 0.0F, 0.0F));

			PartDefinition RightArmPart = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(38, 1).addBox(-4.4F, -3.5F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(36, 57).addBox(-4.4F, -3.5F, 2.5F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(46, 57).addBox(-4.4F, -3.5F, -2.5F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(26, 43).addBox(-4.4F, -3.5F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(44, 34).addBox(-3.4F, 0.5F, 1.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(50, 17).addBox(-3.4F, 0.5F, -2.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(50, 18).addBox(-2.9F, 8.5F, -2.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(18, 57).addBox(-2.9F, 8.5F, 1.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(50, 6).addBox(-4.4F, 0.5F, -2.5F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(50, 11).addBox(-3.9F, 8.5F, -2.5F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(40, 6).addBox(-3.9F, 0.5F, -2.5F, 0.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(52, 41).addBox(-3.9F, 0.5F, -2.5F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(10, 53).addBox(-3.9F, 0.5F, 2.5F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(20, 33).addBox(-4.7F, 2.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(40, 25).addBox(-4.7F, 7.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(10, 51).addBox(-4.7F, 4.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(20, 35).addBox(0.1F, 5.0F, 2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(22, 35).addBox(0.1F, 5.0F, -2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(14, 42).addBox(0.1F, 2.0F, -2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(16, 42).addBox(0.1F, 2.0F, 2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(54, 25).addBox(1.1F, 2.0F, -2.5F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(56, 49).addBox(1.1F, 5.0F, -2.5F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

			PartDefinition BodyPart = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(38, 41).addBox(-1.0F, 1.0F, -3.4F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
					.texOffs(0, 42).addBox(-1.0F, 1.0F, 1.4F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
					.texOffs(56, 59).addBox(-2.0F, 7.0F, -2.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(58, 22).addBox(-4.4F, 9.0F, 2.5F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(58, 59).addBox(-2.0F, 7.0F, 2.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(58, 35).addBox(-4.4F, 9.0F, -2.5F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(18, 53).addBox(-4.4F, 1.5F, -2.5F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(28, 59).addBox(3.4F, 1.5F, -2.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 59).addBox(3.4F, 5.5F, 2.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(36, 50).addBox(4.4F, 5.5F, -2.5F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(32, 59).addBox(3.4F, 5.5F, -2.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(34, 59).addBox(3.4F, 1.5F, 2.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(18, 55).addBox(-4.4F, 1.5F, 2.5F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(46, 50).addBox(-4.4F, 9.0F, -2.5F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(0, 51).addBox(-4.4F, 1.5F, -2.5F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(26, 52).addBox(4.4F, 1.5F, -2.5F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(38, 6).addBox(4.4F, -1.5F, -2.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(36, 43).addBox(-4.4F, -1.5F, 1.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(24, 58).addBox(4.4F, -1.5F, 1.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(58, 37).addBox(-4.4F, -1.5F, -2.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition LefrArmPart = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(38, 36).addBox(-0.6F, -3.5F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(0, 58).addBox(-0.6F, -3.5F, 2.5F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(58, 1).addBox(-0.6F, -3.5F, -2.5F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(44, 25).addBox(4.4F, -3.5F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

			PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(54, 31).addBox(-3.0F, 8.3F, -3.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(56, 55).addBox(-3.0F, 8.3F, 3.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(14, 43).addBox(3.0F, 8.3F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
					.texOffs(14, 43).addBox(-3.0F, 8.3F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
					.texOffs(0, 27).addBox(-3.0F, 12.3F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

			PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(54, 31).addBox(-3.2F, 8.3F, -3.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(56, 55).addBox(-3.2F, 8.3F, 3.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(14, 43).addBox(2.8F, 8.3F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
					.texOffs(14, 43).addBox(-3.2F, 8.3F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
					.texOffs(0, 27).addBox(-3.2F, 12.3F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		}
		else
		{

			partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
			partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
			partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
			PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-4.5F, 11.0F, -2.5F, 9.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(20, 0).addBox(-4.5F, 11.0F, 2.5F, 9.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(20, 15).addBox(4.5F, 11.0F, -2.5F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(0, 21).addBox(-4.5F, 11.0F, -2.5F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(20, 27).addBox(-2.4F, 0.0F, -2.5F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(18, 20).addBox(1.6F, 4.0F, 2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 17).addBox(0.6F, 3.0F, 2.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 13).addBox(-0.4F, 2.0F, 2.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(20, 29).addBox(-2.4F, 0.0F, 2.5F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 14).addBox(-0.4F, 2.0F, -2.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 18).addBox(0.6F, 3.0F, -2.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 23).addBox(1.6F, 4.0F, -2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(0, 10).addBox(2.6F, 0.0F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(30, 5).addBox(-2.7F, 6.0F, 2.5F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 19).addBox(-2.7F, 6.0F, -2.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 6).addBox(-1.5F, 5.5F, -3.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(10, 21).addBox(2.3F, 6.0F, -2.5F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(30, 24).addBox(1.3F, 6.0F, -2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(20, 21).addBox(-2.7F, 6.0F, -2.5F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(0, 0).addBox(-2.4F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(20, 1).addBox(-2.3F, 0.0F, -2.5F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

			PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(10, 10).addBox(-2.6F, 0.0F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(0, 27).addBox(-2.3F, 6.0F, -2.5F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(10, 27).addBox(2.7F, 6.0F, -2.5F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(30, 1).addBox(-2.6F, 0.0F, -2.5F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 20).addBox(-2.6F, 3.0F, 2.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 25).addBox(-2.6F, 4.0F, 2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 9).addBox(-2.3F, 6.0F, 2.5F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 15).addBox(-2.6F, 2.0F, 2.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 3).addBox(-2.6F, 0.0F, 2.5F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 16).addBox(-2.6F, 2.0F, -2.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 21).addBox(-2.6F, 3.0F, -2.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 26).addBox(-2.6F, 4.0F, -2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 10).addBox(-1.7F, 5.5F, -3.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
					.texOffs(30, 27).addBox(-2.3F, 6.0F, -2.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(30, 22).addBox(0.7F, 6.0F, -2.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
					.texOffs(0, 5).addBox(-2.6F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
					.texOffs(20, 8).addBox(2.5F, 0.0F, -2.5F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		}
		return LayerDefinition.create(meshdefinition, tex_x, tex_y);

	}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,int color)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

}