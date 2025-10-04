package net.crocodil.delab.client.render;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.projectails.Spears.SpearMaterial;
import net.crocodil.delab.entity.projectails.Spears.ThrowingSpear;
import net.crocodil.delab.client.model.ThrowingSpearModel;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ThrowingSpearRenderer extends EntityRenderer<ThrowingSpear> {
    private final ThrowingSpearModel model;

    private static final Map<SpearMaterial, ResourceLocation> LOCATION_BY_MATERIAL =
            Util.make(Maps.newEnumMap(SpearMaterial.class), map -> {
                map.put(SpearMaterial.WOODEN,
                        ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/wooden_spear.png"));
                map.put(SpearMaterial.STONE,
                        ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/stone_spear.png"));
                map.put(SpearMaterial.IRON,
                        ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/iron_spear.png"));
                map.put(SpearMaterial.GOLDEN,
                        ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/golden_spear.png"));
                map.put(SpearMaterial.DIAMOND,
                        ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/diamond_spear.png"));
                map.put(SpearMaterial.NETHERITE,
                        ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/netherite_spear.png"));
            });

    public ThrowingSpearRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ThrowingSpearModel(context.bakeLayer(ThrowingSpearModel.LAYER_LOCATION));
    }

    public void render(ThrowingSpear entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(entity)), false, entity.isFoil());
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrowingSpear throwingSpear) {
        return LOCATION_BY_MATERIAL.get(throwingSpear.getMaterial());
    }
}
