package net.crocodil.delab.entity.projectails;

import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.entity.DelabEntityTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class EvilSpitterBullet extends ThrowableItemProjectile {

    public EvilSpitterBullet(EntityType<? extends EvilSpitterBullet> type, Level world) {
        super(type, world);
    }

    public EvilSpitterBullet(Level world, LivingEntity thrower) {
        super(DelabEntityTypes.EVIL_SPITTER_BULLET.get(), thrower, world); // модифицируй ModEntities под себя
    }

    public EvilSpitterBullet(Level world, double x, double y, double z) {
        super(DelabEntityTypes.EVIL_SPITTER_BULLET.get(), x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemStack.EMPTY.getItem();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 2.0F);
            if(result.getEntity() instanceof LivingEntity living)
            {
                living.addEffect(new MobEffectInstance(DelabMobEffects.ANGRY, 160));
            }

            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.playSound(SoundEvents.SNOW_BREAK, 1.0F, 1.0F);
            this.discard();
        }
    }
}
