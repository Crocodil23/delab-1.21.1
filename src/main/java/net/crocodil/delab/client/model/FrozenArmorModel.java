package net.crocodil.delab.client.model;

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

public class FrozenArmorModel <T extends LivingEntity> extends HumanoidModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION_INNER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "frozen_armor_layer_2"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_OUTER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "frozen_armor_layer_1"), "main");
    public final ModelPart leftArm;
    public final ModelPart body;
    public final ModelPart rightArm;
    public final ModelPart head;
    public final ModelPart leftLeg;
    public final ModelPart rightLeg;
    private static final float deform_outer = 0.0F;

    public FrozenArmorModel(ModelPart root) {
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

            PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(52, 19).addBox(-4.5F, -7.0F, -5.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 42).addBox(-3.5F, -1.0F, 5.0F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(38, 0).addBox(-4.5F, -9.0F, 5.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 45).addBox(-4.5F, -9.0F, -5.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 10).addBox(-4.5F, -9.0F, -5.0F, 0.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                    .texOffs(20, 10).addBox(4.5F, -9.0F, -5.0F, 0.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 0).addBox(-4.5F, -9.0F, -5.0F, 9.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
                    .texOffs(66, 59).addBox(-4.5F, -5.0F, -5.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 67).addBox(3.5F, -5.0F, -5.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 26).addBox(-1.0F, -5.0F, -5.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 43).addBox(-3.5F, -3.0F, -5.0F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(44, 26).addBox(-0.5F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 34).addBox(-5.0F, 4.0F, -3.0F, 10.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(20, 34).addBox(-5.0F, 4.0F, 3.0F, 10.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 8).addBox(-5.0F, 4.0F, -3.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 34).addBox(5.0F, 4.0F, -3.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(32, 64).addBox(2.0F, 0.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 65).addBox(-5.0F, 0.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(22, 65).addBox(2.0F, 0.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(66, 2).addBox(-2.0F, 1.0F, 3.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(42, 65).addBox(-5.0F, 0.0F, 2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                    .texOffs(66, 5).addBox(-2.0F, 1.0F, -3.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 0).addBox(-5.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(32, 56).addBox(4.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 22).addBox(-5.5F, 12.0F, -3.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 24).addBox(-5.5F, 12.0F, 2.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(42, 59).addBox(-5.5F, 12.0F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(54, 59).addBox(4.5F, 12.0F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 42).addBox(-0.6F, -3.5F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 47).addBox(-0.6F, -3.5F, 2.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 53).addBox(-0.6F, -3.5F, -3.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 55).addBox(4.4F, -3.5F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

            PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(20, 42).addBox(-4.4F, -3.5F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 8).addBox(-4.4F, -3.5F, 2.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 21).addBox(-4.4F, -3.5F, -3.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(22, 55).addBox(-4.4F, -3.5F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

            PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(52, 65).addBox(-2.7F, 6.3F, 2.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 65).addBox(-2.7F, 6.3F, -3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 47).addBox(2.3F, 6.3F, -3.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                    .texOffs(16, 47).addBox(-3.7F, 6.3F, -3.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                    .texOffs(48, 48).addBox(-3.2F, 7.3F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 8).addBox(2.8F, 7.3F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 14).addBox(-3.2F, 7.3F, 3.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 27).addBox(-3.2F, 7.3F, -3.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 28).addBox(-3.1F, 12.3F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

            PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(24, 28).addBox(-3.0F, 12.3F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 32).addBox(-3.0F, 7.3F, -3.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(64, 37).addBox(-3.0F, 7.3F, 3.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 34).addBox(3.0F, 7.3F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 55).addBox(-3.0F, 7.3F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(48, 26).addBox(-3.5F, 6.3F, -3.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                    .texOffs(32, 48).addBox(2.5F, 6.3F, -3.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 66).addBox(-2.5F, 6.3F, -3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                    .texOffs(66, 0).addBox(-2.5F, 6.3F, 2.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));


        }
        else
        {
            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 24).addBox(-4.6F, 7.5F, -2.5F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 28).addBox(-4.6F, 7.5F, 2.5F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(18, 28).addBox(-4.6F, 7.5F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(28, 28).addBox(4.4F, 7.5F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(30, 18).addBox(-2.7F, -0.5F, -2.5F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 32).addBox(-2.7F, -0.5F, 2.5F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(20, 0).addBox(-2.7F, -0.5F, -2.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(20, 14).addBox(2.3F, -0.5F, -2.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 5).addBox(-2.7F, -0.5F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

            PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 0).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(30, 9).addBox(-2.5F, -0.5F, 2.5F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 10).addBox(-2.5F, -0.5F, -2.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(10, 10).addBox(2.5F, -0.5F, -2.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 0).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);

        }
        return LayerDefinition.create(meshdefinition, tex_x, tex_y);

    }



    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color)
    {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

}