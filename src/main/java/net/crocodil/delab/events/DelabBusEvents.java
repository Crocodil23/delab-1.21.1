package net.crocodil.delab.events;

import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.*;
import net.crocodil.delab.entity.FrozenCowing;
import net.crocodil.delab.entity.DelabEntityTypes;
import net.crocodil.delab.entity.Mudaur;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DelabBusEvents {


    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ThrowingSpearModel.LAYER_LOCATION, ThrowingSpearModel::createBodyLayer);
        event.registerLayerDefinition(AbominationArmorModel.LAYER_LOCATION_OUTER, AbominationArmorModel::createOuterLayer);
        event.registerLayerDefinition(AbominationArmorModel.LAYER_LOCATION_INNER, AbominationArmorModel::createInnerLayer);
        event.registerLayerDefinition(FrozenArmorModel.LAYER_LOCATION_OUTER, FrozenArmorModel::createOuterLayer);
        event.registerLayerDefinition(FrozenArmorModel.LAYER_LOCATION_INNER, FrozenArmorModel::createInnerLayer);
        event.registerLayerDefinition(MudaurModel.MUDAUR_OUTER_LAYER, MudaurModel::createOuterLayer);
        event.registerLayerDefinition(FrozenCowingModel.LAYER_LOCATION, FrozenCowingModel::createMainLayer);
        event.registerLayerDefinition(FrozenCowingModel.LAYER_LOCATION_OUTER, FrozenCowingModel::createOuterLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(DelabEntityTypes.MUDAUR.get(), Mudaur.createAttributes().build());
        event.put(DelabEntityTypes.FROZEN_COWING.get(), FrozenCowing.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(DelabEntityTypes.MUDAUR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mudaur::checkMudaurSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(DelabEntityTypes.FROZEN_COWING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FrozenCowing::checkCowingSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }


}