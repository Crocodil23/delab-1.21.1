package net.crocodil.delab.client.model;

import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.Mudaur;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class MudaurModel <T extends Mudaur> extends ZombieModel<T> {
    public static ModelLayerLocation MUDAUR_OUTER_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Delab.MODID, "mudaur_outer_layer"), "outer");
    public MudaurModel(ModelPart root) {
        super(root);
    }
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {

        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (entity.isThrowing()) {
            if (entity.isLeftHanded()) {
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
            } else
            {
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float) Math.PI;
            }
        }

    }

    public static LayerDefinition createOuterLayer() {
        CubeDeformation cubeDeformation =new CubeDeformation(0.25F);
        MeshDefinition meshdefinition = HumanoidModel.createMesh(cubeDeformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, cubeDeformation), PartPose.offset(1.9F, 12.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
