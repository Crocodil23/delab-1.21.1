package net.crocodil.delab.entity;

import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class MudBall extends ThrowableItemProjectile {
    public MudBall(EntityType<? extends MudBall> entityType, Level level) {
        super(entityType, level);
    }

    public MudBall(Level level, LivingEntity shooter) {
        super(DelabEntityTypes.MUD_BALL.get(), shooter, level);
    }

    public MudBall(Level level, double x, double y, double z) {
        super(DelabEntityTypes.MUD_BALL.get(), x, y, z, level);
    }

    protected Item getDefaultItem() {
        return DelabItems.MUD_BALL.get();
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItem();
        return  new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleoptions,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        (this.random.nextDouble() - 0.5) * 0.08,
                        (this.random.nextDouble() - 0.5) * 0.08,
                        (this.random.nextDouble() - 0.5) * 0.08);
            }
        }

    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if(entity instanceof LivingEntity living)
        {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 1);
            DelabMobEffects.addMudInMudMobeEffect(living, (LivingEntity) this.getOwner(), 160);
        }


    }

    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }
}