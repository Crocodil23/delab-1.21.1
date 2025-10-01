package net.crocodil.delab.effects;

import net.crocodil.delab.Delab;
import net.crocodil.delab.items.DelabArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DelabMobEffects
{
    public static final DeferredRegister<MobEffect> DELAB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Delab.MODID);

    public static final Holder<MobEffect> SHADOW_STRIKE = DELAB_EFFECTS.register("shadow_strike",
            () -> new ShadowStrikeEffect(MobEffectCategory.BENEFICIAL, 0));
    public static final Holder<MobEffect> IN_MUD = DELAB_EFFECTS.register("in_mud",
            () -> new WithoutScaleMobEffect(MobEffectCategory.HARMFUL, 0x734222)
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE,
                            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "in_mud_damage"),
                            -1,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "in_mud_speed"),
                            -0.3F,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(
                            Attributes.MINING_EFFICIENCY,
                            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "in_mud_mining"),
                            -0.3F,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> MAGICAL_FROST = DELAB_EFFECTS.register("magical_frost",
            () -> new MagicalFrostEffect(MobEffectCategory.HARMFUL, 0x649bc6));


    public static void register(IEventBus bus)
    {
        DELAB_EFFECTS.register(bus);
    }
    public static void addMudInMudMobeEffect(LivingEntity living, LivingEntity source_living, int duration)
    {
        if (DelabArmorMaterials.isFullSetOff(DelabArmorMaterials.ABOMINATION, source_living))
            duration *= 1.5;
        living.addEffect(new MobEffectInstance(DelabMobEffects.IN_MUD, duration));
    }
}
