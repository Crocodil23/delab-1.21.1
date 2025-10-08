package net.crocodil.delab.items;

import net.crocodil.delab.effects.DelabMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class DelabFoodProperties {

    public static final FoodProperties FROZEN_FLESH = new FoodProperties.Builder().nutrition(4).saturationModifier(0.1f)
            .effect(() -> new MobEffectInstance(DelabMobEffects.MAGICAL_FROST, 200),0.8F).build();
    public static final FoodProperties FRESH_FLESH = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6f).build();

    public static final FoodProperties ANCIENT_TOFU = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).build();
    public static final FoodProperties DEFENDER_OFFERING = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.3f)
            .usingConvertsTo(Items.BOWL)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(DelabMobEffects.ANGRY, 600),1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800,1),1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800),1.0F)
            .build();

}