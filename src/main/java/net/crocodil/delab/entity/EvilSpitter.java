package net.crocodil.delab.entity;

import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.Goals.EvilSpitterTargetGoal;
import net.crocodil.delab.entity.projectails.EvilSpitterBullet;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class EvilSpitter extends Monster implements RangedAttackMob {
    private static final EntityDataAccessor<Integer> LEAF_POSE =
            SynchedEntityData.defineId(EvilSpitter.class, EntityDataSerializers.INT);

    public static final int OPEN = 0;
    public static final int CLOSE = 1;

    private boolean rotationSaved = false;

    private float savedYRot, savedXRot, savedYHeadRot, savedYBodyRot;

    private static final ResourceLocation CLOSE_ARMOR_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "close");
    private static final AttributeModifier CLOSE_ARMOR_MODIFIER =
            new AttributeModifier(CLOSE_ARMOR_MODIFIER_ID, (double)15.0F, AttributeModifier.Operation.ADD_VALUE);


    protected EvilSpitter(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 2, 120, 20));
        this.targetSelector.addGoal(2, new EvilSpitterTargetGoal<>(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 18d)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, (double)0.0F)
                .add(Attributes.FOLLOW_RANGE, 20D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }
    @Override
    public void push(double x, double y, double z) {

    }
    public int getLeafPose()
    {
        return this.entityData.get(LEAF_POSE);
    }
    public void setLeafPose(int pose)
    {
        this.entityData.set(LEAF_POSE, pose);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LEAF_POSE, OPEN);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("LEAF_POSE", this.getLeafPose());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(LEAF_POSE, compound.getInt("LEAF_POSE"));
    }
    @Override
    public void tick() {
        super.tick();

        if (!rotationSaved) {
            rotationSaved = true;
            savedYRot = this.getYRot();
            savedXRot = this.getXRot();
            savedYHeadRot = this.yHeadRot;
            savedYBodyRot = this.yBodyRot;
        }
        long dayTime = this.level().getLevelData().getDayTime() % 24000L;

        boolean isDay   = (dayTime >= 0L && dayTime < 12000L);
        boolean isNight = (dayTime >= 13000L && dayTime < 23000L);
        if (isDay && this.getLeafPose() != CLOSE)
        {
            this.getAttribute(Attributes.ARMOR).addPermanentModifier(CLOSE_ARMOR_MODIFIER);
            this.setLeafPose(CLOSE);
        }

        else if(isNight && this.getLeafPose() != OPEN)
        {
            this.getAttribute(Attributes.ARMOR).removeModifier(CLOSE_ARMOR_MODIFIER);
            this.setLeafPose(OPEN);
        }

        if(this.getLeafPose() == CLOSE)
        {

            this.setYRot(savedYRot);
            this.setXRot(savedXRot);
            this.yBodyRot = savedYBodyRot;
            this.yHeadRot = savedYHeadRot;
            this.yRotO = savedYRot;
            this.xRotO = savedXRot;

        }

    }
    @Override
    public void performRangedAttack(LivingEntity target, float v) {
        EvilSpitterBullet bullet = new EvilSpitterBullet(this.level(), this);

        double d0 = target.getX() - this.getX();
        double d1 = target.getY(0.3333333333333333) - bullet.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);

        bullet.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(bullet);
    }

    static class EvilSpitterBodyRotationControl extends BodyRotationControl {
        public EvilSpitterBodyRotationControl(Mob mob) {
            super(mob);
        }

        @Override
        public void clientTick() {
        }
    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new EvilSpitterBodyRotationControl(this);
    }
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.GRASS_BREAK;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GRASS_BREAK;
    }

    public static boolean canSeeSkyIfIgnoreLeavesAndLogs(ServerLevelAccessor level, BlockPos pos) {
        int x = pos.getX();
        int z = pos.getZ();
        int yStart = pos.getY() + 1;
        int maxY = level.getMaxBuildHeight();

        for (int y = yStart; y < maxY; y++) {
            BlockPos here = new BlockPos(x, y, z);
            BlockState state = level.getBlockState(here);

            if (state.isAir()) {
                continue;
            }

            if (state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS)) {
                continue;
            }
            return false;
        }
        return true;
    }
    public static boolean checkEvilSpitterSpawnRules(EntityType<? extends EvilSpitter> evilSpitter, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkAnyLightMonsterSpawnRules(evilSpitter, level, spawnType,pos, random) && canSeeSkyIfIgnoreLeavesAndLogs(level, pos);

    }
}
