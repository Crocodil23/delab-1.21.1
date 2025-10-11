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

public class WildArmorModel <T extends LivingEntity> extends HumanoidModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION_INNER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "wild_armor_layer_2"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_OUTER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "wild_armor_layer_1"), "main");
    public final ModelPart leftArm;
    public final ModelPart body;
    public final ModelPart rightArm;
    public final ModelPart head;
    public final ModelPart leftLeg;
    public final ModelPart rightLeg;
    private static final float deform_outer = 0.0F;

    public WildArmorModel(ModelPart root) {
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
            PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -5.0F, 8.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
                    .texOffs(24, 53).addBox(-4.0F, -9.0F, 4.99F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 0).addBox(-4.0F, -9.0F, -4.99F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(24, 39).addBox(-5.0F, -5.0F, 4.99F, 10.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(60, 55).addBox(-0.49F, -6.1F, -1.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(42, 19).addBox(-0.49F, -6.1F, -11.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -3.0F, 6.0F, 0.0F, 0.0F, -0.2618F));

            PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 61).addBox(0.5F, -6.25F, -1.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 19).addBox(0.5F, -6.25F, 9.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -3.0F, -4.0F, 0.0F, 0.0F, 0.2618F));

            PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(20, 10).addBox(1.5F, -6.5F, -1.0F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -3.0F, -4.0F, 0.0F, 0.0F, 0.2618F));

            PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 10).addBox(-0.2F, -7.3F, -1.0F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -2.0F, -4.0F, 0.0F, 0.0F, -0.2618F));

            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(36, 0).addBox(-5.0F, 2.0F, -3.0F, 10.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 27).addBox(-5.0F, 0.0F, 3.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 37).addBox(5.0F, 0.0F, -3.0F, 0.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 39).addBox(-5.0F, 0.0F, -3.0F, 0.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 53).addBox(5.0F, 10.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(24, 57).addBox(-5.0F, 10.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 3).addBox(-3.5F, 11.0F, -3.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 18).addBox(-3.5F, 11.0F, 3.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 6).addBox(-4.0F, 10.0F, -3.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 7).addBox(-4.0F, 10.0F, 3.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(46, 62).addBox(2.0F, 0.0F, -3.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 37).addBox(1.0F, 1.0F, -3.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 62).addBox(-5.0F, 0.0F, -3.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 38).addBox(-5.0F, 1.0F, -3.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

            PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(52, 8).addBox(4.0F, -2.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(32, 57).addBox(0.0F, -1.0F, -3.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(32, 60).addBox(0.0F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 43).addBox(-1.0F, -2.0F, -2.99F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 44).addBox(-1.0F, -2.0F, 2.99F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(2, 61).addBox(-0.9F, -3.0F, -2.99F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 61).addBox(-0.9F, -3.0F, 2.99F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(36, 8).addBox(-0.5F, -4.0F, -2.99F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(36, 9).addBox(-0.5F, -4.0F, 2.99F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 53).addBox(0.0F, -4.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

            PartDefinition cube_r5 = left_arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(60, 42).addBox(-4.1F, -0.6F, -1.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 41).addBox(-4.1F, -0.6F, -7.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, 4.0F, 0.0F, 0.0F, 0.5236F));

            PartDefinition cube_r6 = left_arm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 8).addBox(0.9F, -5.48F, -1.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, -2.0F, 0.0F, 0.0F, -1.0472F));

            PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 52).addBox(-4.0F, -2.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 45).addBox(-4.0F, -1.0F, -3.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 48).addBox(-4.0F, -1.0F, 3.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 51).addBox(-4.0F, -2.0F, -2.99F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 52).addBox(-4.0F, -2.0F, 2.99F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(2, 62).addBox(-2.1F, -3.0F, -2.99F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 62).addBox(-2.1F, -3.0F, 2.99F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(38, 8).addBox(-0.5F, -4.0F, -2.99F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(38, 9).addBox(-0.5F, -4.0F, 2.99F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(12, 55).addBox(0.0F, -4.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

            PartDefinition cube_r7 = right_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(60, 54).addBox(-3.65F, 0.22F, -1.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 53).addBox(-3.65F, 0.22F, -7.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, 4.0F, 0.0F, 0.0F, -0.5236F));

            PartDefinition cube_r8 = right_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 42).addBox(0.5F, -5.8F, -1.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -2.0F, -2.0F, 0.0F, 0.0F, 1.0472F));

            PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(20, 27).addBox(-3.0F, 12.3F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 21).addBox(-3.0F, 7.3F, -3.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 26).addBox(-3.0F, 7.3F, 3.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(36, 42).addBox(3.0F, 7.3F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(44, 19).addBox(-3.0F, 7.3F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

            PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(44, 30).addBox(-3.2F, 7.3F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(48, 41).addBox(2.8F, 7.3F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 31).addBox(-3.2F, 7.3F, 3.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 36).addBox(-3.2F, 7.3F, -3.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(20, 33).addBox(-3.2F, 12.3F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        }
        else
        {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);

            PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 28).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(28, 28).addBox(-2.5F, -0.5F, 2.5F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 10).addBox(-2.5F, -0.5F, -2.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(10, 10).addBox(2.5F, -0.5F, -2.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 0).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

            PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(30, 0).addBox(-2.7F, -0.5F, -2.5F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(30, 9).addBox(-2.7F, -0.5F, 2.5F, 5.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(20, 0).addBox(-2.7F, -0.5F, -2.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(20, 14).addBox(2.3F, -0.5F, -2.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 5).addBox(-2.7F, -0.5F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(30, 18).addBox(-4.6F, 7.5F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 32).addBox(4.4F, 7.5F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 24).addBox(-4.6F, 7.5F, 2.5F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 28).addBox(-4.6F, 7.5F, -2.5F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

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