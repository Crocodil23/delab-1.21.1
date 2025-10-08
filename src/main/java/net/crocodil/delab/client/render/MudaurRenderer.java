package net.crocodil.delab.client.render;

import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.MudaurModel;
import net.crocodil.delab.client.render.layers.MudaurOuterLayer;
import net.crocodil.delab.entity.Mudaur;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MudaurRenderer extends AbstractZombieRenderer<Mudaur, MudaurModel<Mudaur>> {
    ResourceLocation MUDAUR_TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/mudaur/mudaur.png");
    public MudaurRenderer(EntityRendererProvider.Context p_174456_) {
        this(p_174456_, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public MudaurRenderer(EntityRendererProvider.Context context, ModelLayerLocation zombieLayer, ModelLayerLocation innerArmor, ModelLayerLocation outerArmor) {
        super(context, new MudaurModel<>(context.bakeLayer(zombieLayer)), new MudaurModel<>(context.bakeLayer(innerArmor)), new MudaurModel<>(context.bakeLayer(outerArmor)));
        MudaurModel<Mudaur> outerModel = new MudaurModel<>(context.bakeLayer(MudaurModel.MUDAUR_OUTER_LAYER));
        this.addLayer(new MudaurOuterLayer<>(this, outerModel));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Mudaur mudaur) {
        return MUDAUR_TEXTURE;
    }
}

