package net.crocodil.delab.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.EvilSpitter;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class EvilSpitterModel<T extends EvilSpitter> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Delab.MODID, "evil_spitter"), "main");
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart gun;
    private final ModelPart body;
    private final ModelPart leaf_front;
    private final ModelPart leaf_back;
    private final ModelPart leaf_right;
    private final ModelPart leaf_left;

    public EvilSpitterModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.gun = root.getChild("gun");
        this.body = root.getChild("body");
        this.leaf_front = root.getChild("leaf_front");
        this.leaf_back = root.getChild("leaf_back");
        this.leaf_right = root.getChild("leaf_right");
        this.leaf_left = root.getChild("leaf_left");
    }

    public ModelPart root() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition gun = partdefinition.addOrReplaceChild("gun", CubeListBuilder.create().texOffs(20, 32).addBox(-1.0F, -2.5F, -8.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 28).addBox(-1.0F, -0.5F, -8.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 36).addBox(1.0F, -2.5F, -8.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 20).addBox(-1.0F, -2.5F, -8.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 36).addBox(-1.5F, -2.5F, -4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 32).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 16).addBox(-0.5F, -12.0F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 38).addBox(-2.5F, -1.0F, -3.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 39).addBox(-2.5F, -1.0F, 2.5F, 5.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 6).addBox(2.5F, -1.0F, -2.5F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 11).addBox(-3.5F, -1.0F, -2.5F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition leaf_front = partdefinition.addOrReplaceChild("leaf_front", CubeListBuilder.create().texOffs(36, 26).addBox(-2.0F, 0.0F, -13.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.5F, 0.0F, -11.0F, 5.0F, 0.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-1.5F, 0.0F, -15.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, -3.5F));

        PartDefinition leaf_back = partdefinition.addOrReplaceChild("leaf_back", CubeListBuilder.create().texOffs(0, 11).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(38, 36).addBox(-2.0F, 0.0F, 11.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 42).addBox(-1.5F, 0.0F, 13.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 3.5F));

        PartDefinition leaf_right = partdefinition.addOrReplaceChild("leaf_right", CubeListBuilder.create().texOffs(0, 27).addBox(-11.0F, 0.0F, -2.5F, 11.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(36, 16).addBox(-13.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(38, 40).addBox(-15.0F, 0.0F, -1.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 23.0F, 0.0F));

        PartDefinition leaf_left = partdefinition.addOrReplaceChild("leaf_left", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, -2.5F, 11.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(32, 32).addBox(11.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 40).addBox(13.0F, 0.0F, -1.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 23.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.body.yRot = 0f;
        this.body.xRot = 0f;

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        this.gun.yRot = this.head.yRot;
        this.gun.xRot = this.head.xRot;

        if (entity.getLeafPose() == EvilSpitter.OPEN) {
            this.gun.visible = true;
            this.leaf_front.xRot = this.leaf_front.xRot * 0.5F + (float) Math.PI/96;
            this.leaf_back.xRot = this.leaf_back.xRot * 0.5F - (float) Math.PI/96;
            this.leaf_left.zRot = this.leaf_left.zRot * 0.5F + (float) Math.PI/96;
            this.leaf_right.zRot = this.leaf_right.zRot * 0.5F - (float) Math.PI/96;
        } else if (entity.getLeafPose() == EvilSpitter.CLOSE) {
            this.gun.visible = false;
            this.leaf_front.xRot = this.leaf_front.xRot * 0.5F - (float) Math.PI/3.84F;
            this.leaf_back.xRot = this.leaf_back.xRot * 0.5F + (float) Math.PI/3.84F;
            this.leaf_left.zRot = this.leaf_left.zRot * 0.5F - (float) Math.PI/3.84F;
            this.leaf_right.zRot = this.leaf_right.zRot * 0.5F + (float) Math.PI/3.84F;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        gun.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leaf_front.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leaf_back.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leaf_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        leaf_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
