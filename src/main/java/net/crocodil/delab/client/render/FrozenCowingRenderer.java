package net.crocodil.delab.client.render;

import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.FrozenCowingModel;
import net.crocodil.delab.client.render.layers.FrozenCowingOuterLayer;
import net.crocodil.delab.entity.FrozenCowing;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class FrozenCowingRenderer extends HumanoidMobRenderer<FrozenCowing, FrozenCowingModel<FrozenCowing>> {

    ResourceLocation COWING_TEXTUE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/cowing/frozen_cowing.png");
    public FrozenCowingRenderer(EntityRendererProvider.Context context) {
        super(context, new FrozenCowingModel<>(context.bakeLayer(FrozenCowingModel.LAYER_LOCATION)), 0.5F);

        addLayer(new HumanoidArmorLayer<>(this,
                new FrozenCowingModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new FrozenCowingModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)),
                context.getModelManager()));
        addLayer(new FrozenCowingOuterLayer<>(this, new FrozenCowingModel<>(context.bakeLayer(FrozenCowingModel.LAYER_LOCATION_OUTER))));

    }

    @Override
    public ResourceLocation getTextureLocation(FrozenCowing cowing) {
        return COWING_TEXTUE;
    }
}
