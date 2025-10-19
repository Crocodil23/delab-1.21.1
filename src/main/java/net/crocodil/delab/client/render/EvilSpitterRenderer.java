package net.crocodil.delab.client.render;

import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.EvilSpitterModel;
import net.crocodil.delab.entity.EvilSpitter;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EvilSpitterRenderer extends MobRenderer<EvilSpitter, EvilSpitterModel<EvilSpitter>> {

    ResourceLocation EVIL_SPITTER_TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/evil_spitter.png");

    public EvilSpitterRenderer(EntityRendererProvider.Context context) {
        super(context, new EvilSpitterModel<>(context.bakeLayer(EvilSpitterModel.LAYER_LOCATION)), 0.35f);
    }

    @Override
    public ResourceLocation getTextureLocation(EvilSpitter evilSpitter) {
        return EVIL_SPITTER_TEXTURE;
    }

}