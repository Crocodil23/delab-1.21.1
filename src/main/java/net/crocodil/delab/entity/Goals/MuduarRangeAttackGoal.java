package net.crocodil.delab.entity.Goals;

import net.crocodil.delab.entity.projectails.balls.MudBall;
import net.crocodil.delab.entity.Mudaur;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class MuduarRangeAttackGoal  extends Goal {
    private final Mudaur mudaur;
    private LivingEntity target;
    private final double maxDistance = 14.0D; // максимальная дистанция, на которой атакуют снежком// неточность (чем больше — тем сильнее разброс)

    public MuduarRangeAttackGoal(Mudaur mudaur) {
        this.mudaur = mudaur;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.TARGET));
    }

    @Override
    public boolean canUse() {

        if (this.mudaur.getTarget() != null && (this.mudaur.getTarget().isAlive())) {
            return this.mudaur.getRangeAttackCd() >= 100 && this.mudaur.throwTime >= 30 && this.mudaur.isThrowing();
        }
        this.mudaur.setRangeAttackCd(0);
        this.mudaur.throwTime = 0;
        this.mudaur.setThrowing(false);
        this.mudaur.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);

        return false;
    }

    @Override
    public void start() {
        this.target = this.mudaur.getTarget();
    }

    @Override
    public void stop() {
        this.target = null;
    }
    @Override
    public void tick() {

        this.mudaur.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        this.mudaur.playSound(SoundEvents.SNOWBALL_THROW);
        MudBall mudBall = new MudBall(this.mudaur.level(), this.mudaur);
        double d0 = target.getEyeY() - (double)1.1F;
        double d1 = target.getX() - this.mudaur.getX();
        double d2 = d0 - mudBall.getY();
        double d3 = target.getZ() - this.mudaur.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        mudBall.setOwner(this.mudaur);
        mudBall.shoot(d1, d2 + d4, d3, 1.5F, 0.3F);

        this.mudaur.level().addFreshEntity(mudBall);

        this.mudaur.setRangeAttackCd(0);
        this.mudaur.throwTime = 0;
        this.mudaur.setThrowing(false);
        this.mudaur.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);


    }

}