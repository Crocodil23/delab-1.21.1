package net.crocodil.delab.entity;

import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.projectails.Spears.ThrowingSpear;
import net.crocodil.delab.entity.projectails.balls.FrozenBall;
import net.crocodil.delab.entity.projectails.balls.MudBall;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class DelabEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Delab.MODID);

    public static final Supplier<EntityType<ThrowingSpear>> THROWING_SPEAR =
            ENTITY_TYPES.register("throwing_spear", () -> EntityType.Builder.<ThrowingSpear>of(ThrowingSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20).build("throwing_spear"));
    public static final Supplier<EntityType<MudBall>> MUD_BALL =
            ENTITY_TYPES.register("mud_ball", () -> EntityType.Builder.<MudBall>of(MudBall::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20).build("mud_ball"));

    public static final Supplier<EntityType<FrozenBall>> FROZEN_BALL =
            ENTITY_TYPES.register("frozen_ball", () -> EntityType.Builder.<FrozenBall>of(FrozenBall::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20).build("frozen_ball"));

    public static final Supplier<EntityType<Mudaur>> MUDAUR =
            ENTITY_TYPES.register("mudaur", () -> EntityType.Builder.of(Mudaur::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).eyeHeight(1.74F).build("mudaur"));

    public static final Supplier<EntityType<FrozenCowing>> FROZEN_COWING =
            ENTITY_TYPES.register("frozen_cowing", () -> EntityType.Builder.of(FrozenCowing::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).eyeHeight(1.74F).build("frozen_cowing"));


    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
