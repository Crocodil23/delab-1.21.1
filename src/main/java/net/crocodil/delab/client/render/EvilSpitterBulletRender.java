package net.crocodil.delab.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.EvilSpitterBulletModel;
import net.crocodil.delab.entity.projectails.EvilSpitterBullet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ShulkerBulletRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class EvilSpitterBulletRender extends EntityRenderer<EvilSpitterBullet> {
    private EvilSpitterBulletModel model;
    private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/evil_spitter_bullet.png");

    public EvilSpitterBulletRender(EntityRendererProvider.Context context) {
        super(context);
        this.model = new EvilSpitterBulletModel<>(context.bakeLayer(EvilSpitterBulletModel.LAYER_LOCATION));
    }
    public void render(EvilSpitterBullet entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        float yaw = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
        float pitch = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());

        poseStack.mulPose(Axis.YP.rotationDegrees(yaw - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(pitch));

        VertexConsumer vb = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.model.renderToBuffer(poseStack, vb, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EvilSpitterBullet evilSpitterBullet) {
        return TEXTURE;
    }
}
