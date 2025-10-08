package net.crocodil.delab.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public class AngryMobEffect extends MobEffect {
    protected AngryMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        boolean flag = true;
        if(livingEntity instanceof Player player)
            if(player.isCreative())
                flag = false;

        if(flag)
        {
            double range = 40 + 10 * amplifier;
            for (Entity e : livingEntity.level().getEntitiesOfClass(Mob.class, livingEntity.getBoundingBox().inflate(range))) {
                if (e instanceof Mob mob) {
                    mob.setTarget(livingEntity);
                }
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}