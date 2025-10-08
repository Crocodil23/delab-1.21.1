package net.crocodil.delab.entity.Goals;

import net.crocodil.delab.entity.FrozenCowing;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class CowingJumpAttackGoal extends Goal {
    private final FrozenCowing cowing;
    private final double leapSpeed;      // горизонтальная множитель скорости прыжка
    private final double leapY;          // вертикальная скорость прыжка// откат в тиках (20 тик = 1 сек)
    private final double maxRangeSqr = 15D * 15D;
    private final double minRangeSqr = 3D * 3D;// макс. дистанция для старта (квадрат)



    public CowingJumpAttackGoal(FrozenCowing cowing, double leapSpeed, double leapY) {
        this.cowing = cowing;
        this.leapSpeed = leapSpeed;
        this.leapY = leapY;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (cowing.level().isClientSide) return false;
        LivingEntity target = cowing.getTarget();
        if (target == null || !target.isAlive()) return false;
        if (cowing.getJumpAttackCD() > 0) return false;

        double distSqr = cowing.distanceToSqr(target);
        return distSqr <= maxRangeSqr && distSqr >= minRangeSqr;
    }

    @Override
    public void start() {
        LivingEntity target = cowing.getTarget();
        if (target == null) return;
        cowing.setLeap(true);
        // Останавливаем навигацию и даём движение к цели - "прыжок"
        cowing.getNavigation().stop();

        // Вектор к цели
        double dx = target.getX() - cowing.getX();
        double dz = target.getZ() - cowing.getZ();
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        if (horizontal == 0) horizontal = 0.001;

        double vx = (dx / horizontal) * leapSpeed;
        double vz = (dz / horizontal) * leapSpeed;

        // Устанавливаем движение — сделаем "рывок" к цели
        cowing.setDeltaMovement(vx, leapY, vz);
        cowing.hasImpulse = true; // чтобы сервер применил скорость

    }

    @Override
    public void tick() {
        if (this.cowing.getJumpAttackCD() > 0) {;
            return;
        }


        LivingEntity target = cowing.getTarget();
        if (target == null || !target.isAlive()) {
            cowing.setLeap(false);
            stop();
            return;
        }
        if (cowing.getLeapTick() > 40 || (cowing.getLeapTick() > 20 && cowing.onGround())) {
            cowing.setLeap(false);
            cowing.setJumpAttackBonusTick(30);
            stop();
        }
    }

    @Override
    public void stop() {
        cowing.setLeap(false);
        cowing.setJumpAttackCD(240);
    }
}