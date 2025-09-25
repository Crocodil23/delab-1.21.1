package net.crocodil.delab.events;

import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.AbominationArmorModel;
import net.crocodil.delab.client.model.ThrowingSpearModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DelabBusEvents {


    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ThrowingSpearModel.LAYER_LOCATION, ThrowingSpearModel::createBodyLayer);
        event.registerLayerDefinition(AbominationArmorModel.LAYER_LOCATION_OUTER, AbominationArmorModel::createOuterLayer);
        event.registerLayerDefinition(AbominationArmorModel.LAYER_LOCATION_INNER, AbominationArmorModel::createInnerLayer);
    }
    
}