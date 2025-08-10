package net.crocodil.delab.effects;

import net.crocodil.delab.Delab;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DelabMobEffects
{
    public static final DeferredRegister<MobEffect> DELAB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Delab.MODID);

    public static final Holder<MobEffect> SHADOW_STRIKE = DELAB_EFFECTS.register("shadow_strike",
            () -> new ShadowStrikeEffect(MobEffectCategory.HARMFUL, 0));

    public static void register(IEventBus bus)
    {
        DELAB_EFFECTS.register(bus);
    }
}
