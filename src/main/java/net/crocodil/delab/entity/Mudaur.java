package net.crocodil.delab.entity;

import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.entity.Goals.MuduarRangeAttackGoal;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Mudaur extends Zombie {
    private static final EntityDataAccessor<Boolean> THROWING =
            SynchedEntityData.defineId(Mudaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> RANGE_ATTACK_CD =
            SynchedEntityData.defineId(Mudaur.class, EntityDataSerializers.INT);
    public int throwTime = 0;

    public Mudaur(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 22d)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, (double)3.0F)
                .add(Attributes.ARMOR, (double)2.0F)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
                .add(Attributes.FOLLOW_RANGE, 35D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MuduarRangeAttackGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(RANGE_ATTACK_CD, 0);
        builder.define(THROWING, false);
    }


    public void setRangeAttackCd(int cd) {

        this.entityData.set(RANGE_ATTACK_CD,cd  & 255);
    }

    public int getRangeAttackCd() {
        return this.entityData.get(RANGE_ATTACK_CD);
    }
    public boolean isThrowing() {
        return this.entityData.get(THROWING);
    }
    public void setThrowing(boolean throwing)
    {
        this.entityData.set(THROWING, throwing);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("RangaAttackCD", this.getRangeAttackCd());
        compound.putBoolean("THROWING", this.isThrowing());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(RANGE_ATTACK_CD, compound.getInt("RangaAttackCD"));
        this.entityData.set(THROWING, compound.getBoolean("THROWING"));
    }
    public void tick()
    {

        if(this.getRangeAttackCd() < 100)
            this.setRangeAttackCd(this.getRangeAttackCd()+1);
        else
            this.entityData.set(THROWING, true);

        if (this.isThrowing())
        {
            this.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(DelabItems.MUD_BALL.asItem()));
            if(throwTime < 30)
                throwTime++;
        }
        else
        {
            this.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
        }

        super.tick();
    }
    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        if (random.nextFloat() < 0.15F * difficulty.getSpecialMultiplier()) {
            float f = this.level().getDifficulty() == Difficulty.HARD ? 0.1F : 0.25F;
            boolean flag = true;
            for(EquipmentSlot equipmentslot : EquipmentSlot.values()) {
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
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(DelabItems.ABOMINATION_HAMMER.get()));

    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        boolean flag = super.doHurtTarget(entity);
        if (flag
                && this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()
                && entity instanceof LivingEntity living
                && this.random.nextFloat() < 0.35F)
        {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            DelabMobEffects.addMudInMudMobeEffect(living, this, 60*(int)f);
        }
        return flag;
    }

    public static Item getEquipmentForSlot(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD -> DelabItems.ABOMINATION_HELMET.get();
            case CHEST -> DelabItems.ABOMINATION_CHESTPLATE.get();
            case LEGS -> DelabItems.ABOMINATION_LEGGINGS.get();
            case FEET -> DelabItems.ABOMINATION_BOOTS.get();
            default -> null;
        };
    }
    @Override
    protected int decreaseAirSupply(int air) {
        return this.getMaxAirSupply();
    }



}
