package net.crocodil.delab.items;

import net.crocodil.delab.effects.DelabMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

public class DelabFoodProperties {

    public static final FoodProperties FROZEN_FLESH = new FoodProperties.Builder().nutrition(4).saturationModifier(0.1f)
            .effect(() -> new MobEffectInstance(DelabMobEffects.MAGICAL_FROST, 200),0.8F).build();
    public static final FoodProperties FRESH_FLESH = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6f).build();

}
