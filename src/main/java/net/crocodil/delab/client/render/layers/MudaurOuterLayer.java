package net.crocodil.delab.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.MudaurModel;
import net.crocodil.delab.entity.Mudaur;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class MudaurOuterLayer<T extends Mudaur, M extends MudaurModel<T>> extends RenderLayer<T, M> {
    private final M outerModel;
    private static final ResourceLocation MUDAUR_OUTER = ResourceLocation.fromNamespaceAndPath(Delab.MODID,
            "textures/entity/mudaur/mudaur_outer_layer.png");

    public MudaurOuterLayer(RenderLayerParent<T, M> parent, M outerModel) {
        super(parent);
        this.outerModel = outerModel;
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.outerModel, MUDAUR_OUTER, poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, -1);
    }
}