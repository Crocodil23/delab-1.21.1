package net.crocodil.delab.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class WithoutScaleMobEffect extends MobEffect {
    protected WithoutScaleMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(attributeMap, 0);

    }
}