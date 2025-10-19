package net.crocodil.delab.effects;

import net.crocodil.delab.entity.EvilSpitter;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
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
        else if (livingEntity.getType() == EntityType.CREEPER)
                flag = false;

        if(flag)
        {
            double range = 40 + 10 * amplifier;
            for (Entity e : livingEntity.level().getEntitiesOfClass(Mob.class, livingEntity.getBoundingBox().inflate(range))) {
                if (e instanceof Mob mob) {
                    if(e instanceof EvilSpitter evilSpitter)
                        if(evilSpitter.getLeafPose() == EvilSpitter.CLOSE)
                            continue;
                    if(e instanceof Creeper)
                        continue;;

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