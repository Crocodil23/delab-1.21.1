package net.crocodil.delab.entity.Goals;

import net.crocodil.delab.entity.DelabEntityTypes;
import net.crocodil.delab.entity.EvilSpitter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class EvilSpitterTargetGoal <T extends LivingEntity> extends NearestAttackableTargetGoal<LivingEntity> {
    public EvilSpitterTargetGoal(EvilSpitter evilSpitter) {
        super(evilSpitter, LivingEntity.class,true);
    }

    protected void findTarget() {
        super.findTarget();

        LivingEntity living = this.target;
        if(living != null)
            if(living.getType() == EntityType.CREEPER || living.getType() == DelabEntityTypes.EVIL_SPITTER)
                this.target = null;
    }

        @Override
    public boolean canUse() {
        long dayTime = this.mob.level().getLevelData().getDayTime() % 24000L;
        if (dayTime < 12000L)
            return false;
        return super.canUse();
    }
}
