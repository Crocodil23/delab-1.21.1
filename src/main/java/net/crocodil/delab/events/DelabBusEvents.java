package net.crocodil.delab.events;

import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.ThrowingSpearModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DelabBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ThrowingSpearModel.LAYER_LOCATION, ThrowingSpearModel::createBodyLayer);
    }
}