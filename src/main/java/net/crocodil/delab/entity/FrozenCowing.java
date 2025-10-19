package net.crocodil.delab.entity;

import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.entity.Goals.CowingJumpAttackGoal;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
public class FrozenCowing extends Monster {

    private int jumpAttackCD = 0;
    private int jumpAttackBonusTick = 300;
    private int leapTick = 0;

    private static final EntityDataAccessor<Boolean> LEAPING =
            SynchedEntityData.defineId(FrozenCowing.class, EntityDataSerializers.BOOLEAN);

    protected FrozenCowing(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 22d)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, (double)-2.0F)
                .add(Attributes.FOLLOW_RANGE, 35D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new CowingJumpAttackGoal(this, 1D, 0.75D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, (double)1.0F, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static Item getEquipmentForSlot(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD -> DelabItems.FROZEN_HELMET.get();
            case CHEST -> DelabItems.FROZEN_CHESTPLATE.get();
            case LEGS -> DelabItems.FROZEN_LEGGINGS.get();
            case FEET -> DelabItems.FROZEN_BOOTS.get();
            default -> null;
        };
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        if (random.nextFloat() < 0.15F * difficulty.getSpecialMultiplier()) {
            float f = this.level().getDifficulty() == Difficulty.HARD ? 0.1F : 0.25F;
            boolean flag = true;
            for (EquipmentSlot equipmentslot : EquipmentSlot.values()) {
                ItemStack itemstack = this.getItemBySlot(equipmentslot);
                if (!flag && random.nextFloat() < f) {
                    break;
                }

                flag = false;
                if (itemstack.isEmpty()) {
                    Item item = getEquipmentForSlot(equipmentslot);
                    if (item != null) {
                        this.setItemSlot(equipmentslot, new ItemStack(item));
                    }
                }
            }
        }
        if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F))
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(DelabItems.FROZEN_AXE.get()));
        else
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_AXE));
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        populateDefaultEquipmentSlots(random, difficulty);
        return spawnGroupData;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(source == this.damageSources().freeze())
            return false;
        return super.hurt(source, amount);
    }

    public int getJumpAttackCD()
    {
        return jumpAttackCD;
    }
    public void setJumpAttackCD(int cd)
    {
        jumpAttackCD = cd;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LEAPING, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Leaping", this.isLeap());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(LEAPING, compound.getBoolean("Leaping"));
    }

    public boolean isLeap() {
        return this.entityData.get(LEAPING);
    }

    public void setLeap(boolean leaping) {
        this.entityData.set(LEAPING, leaping);
    }

    public int getJumpAttackBonusTick()
    {
        return jumpAttackBonusTick;
    }

    public void setJumpAttackBonusTick(int jumpAttackBounsTick) {
        this.jumpAttackBonusTick = jumpAttackBounsTick;
    }

    public int getLeapTick() {
        return leapTick;
    }

    public void setLeapTick(int leapTick) {
        this.leapTick = leapTick;
    }

    @Override
    public void tick() {
        super.tick();
        if(isLeap())
            leapTick++;
        else
            leapTick = 0;

        if(jumpAttackCD >= 0)
            jumpAttackCD--;
        if(jumpAttackBonusTick >= 0)
            jumpAttackBonusTick--;
    }

    public boolean doHurtTarget(Entity entity) {
        boolean flag = super.doHurtTarget(entity);
        if (flag
                && (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()
                    || this.getItemBySlot(EquipmentSlot.MAINHAND).is(Items.STONE_AXE))
                && entity instanceof LivingEntity living)
        {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            DelabMobEffects.addMagicalFrostMobeEffect(living, this, 60*(int)f);
        }
        return flag;
    }

    public float getVoicePitch() {
        return 0.6F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COW_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    public static boolean checkCowingSpawnRules(EntityType<? extends Monster> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL &&
                (MobSpawnType.ignoresLightRequirements(spawnType)
                        || isDarkEnoughToSpawn(level, pos, random))
                && checkMobSpawnRules(type, level, spawnType, pos, random)
                && level.canSeeSky(pos);
    }

}
