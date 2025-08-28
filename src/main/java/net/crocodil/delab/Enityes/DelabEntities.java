package net.crocodil.delab.Enityes;

import net.crocodil.delab.Delab;
import net.crocodil.delab.Enityes.Spears.ThrowingSpear;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class DelabEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Delab.MODID);

    public static final Supplier<EntityType<ThrowingSpear>> THROWING_SPEAR =
            ENTITIES.register("throwing_spear", () -> EntityType.Builder.<ThrowingSpear>of(ThrowingSpear::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20).build("throwing_spear"));


    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
