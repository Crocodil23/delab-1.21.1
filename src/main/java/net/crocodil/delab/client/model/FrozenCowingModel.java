package net.crocodil.delab.client.model;

import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.FrozenCowing;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class FrozenCowingModel<T extends FrozenCowing> extends HumanoidModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Delab.MODID, "frozen_cowing"), "main");
    public static final ModelLayerLocation LAYER_LOCATION_OUTER =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Delab.MODID, "frozen_cowing"), "outer");
    public FrozenCowingModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createOuterLayer()
    {
        return createBodyLayer(0.25F);
    }

    public static LayerDefinition createMainLayer()
    {
        return createBodyLayer(0.0F);
    }

    public static LayerDefinition createBodyLayer(float def) {

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(def)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(def))
                .texOffs(32, 32).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(def))
                .texOffs(40, 16).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(def))
                .texOffs(40, 20).addBox(-6.0F, -6.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(def))
                .texOffs(40, 22).addBox(4.0F, -6.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(def)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 35).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(def))
                .texOffs(40, 24).addBox(-10.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(def))
                .texOffs(36, 35).addBox(-11.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(def))
                .texOffs(40, 18).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(def)), PartPose.offsetAndRotation(5.0F, -5.0F, -1.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(def)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(def)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(def)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(def)), PartPose.offset(1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if(entity.isAggressive())
        {
            if(!entity.isLeap())
            {
                if (entity.isLeftHanded())
                    this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float) Math.PI / 2;
                else
                    this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI / 2;
            }
        }
        if(entity.isLeap())
        {
            if (entity.isLeftHanded())
            {
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float) Math.PI;
                this.rightArm.xRot = this.rightArm.xRot * 0.5F + (float) Math.PI / 4;
            }

            else
            {
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
                this.leftArm.xRot = this.leftArm.xRot * 0.5F + (float) Math.PI / 4;
            }

        }

    }
}
