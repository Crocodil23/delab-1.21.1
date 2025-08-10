package net.crocodil.delab.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.warden.Warden;

public class ShadowStrikeEffect extends MobEffect {
    protected ShadowStrikeEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {

        for (Entity e : livingEntity.level().getEntitiesOfClass(Mob.class, livingEntity.getBoundingBox().inflate(64.0))) {
            if (e instanceof Mob mob) {
                    if(!(mob instanceof Warden)) {
                        if (mob.getTarget() == livingEntity) {
                            mob.setTarget(null);
                            mob.getNavigation().stop();
                        }
                    }
            }
        }
            livingEntity.setInvisible(true);
            return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
