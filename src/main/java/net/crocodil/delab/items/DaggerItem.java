package net.crocodil.delab.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

import java.util.List;
public class DaggerItem extends TieredItem {
    public DaggerItem(Tier tier, Properties properties) {
        super(tier, properties.component(DataComponents.TOOL, createToolProperties()));
    }
    public static Tool createToolProperties() {
        return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int attackDamage, float attackSpeed) {
        return createAttributes(tier, (float)attackDamage, attackSpeed);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, float damege, float attack_speed1) {
        return ItemAttributeModifiers.builder().
                add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                        BASE_ATTACK_DAMAGE_ID,
                        (double)damege + tier.getAttackDamageBonus() * 0.5,
                                AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.MAINHAND).
                add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)attack_speed1,
                        AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).
                add(Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, -0.5, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).build();
    }
    public float getDaggerDamage()
    {
        return (float) (this.getTier().getAttackDamageBonus() * 0.5);
    }

    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        boolean isClient = entity.level().isClientSide();
        Item mainHand =  player.getMainHandItem().getItem();
        boolean isTwoDaggers = false;
        if(mainHand instanceof DaggerItem)
            isTwoDaggers = true;
        if (!isClient && hand == InteractionHand.OFF_HAND
                && !player.getCooldowns().isOnCooldown(this)
                && isTwoDaggers)
        {
            DamageSource source =  player.damageSources().playerAttack(player);

            float damage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);

            DaggerItem MainDagger = (DaggerItem) mainHand;
            DaggerItem OffDagger = (DaggerItem) stack.getItem();
            damage += OffDagger.getDaggerDamage() - MainDagger.getDaggerDamage();


            boolean isCrit = player.fallDistance > 0.0F
                    && !player.onGround()
                    && !player.onClimbable()
                    && !player.isInWater()
                    && !player.hasEffect(MobEffects.BLINDNESS)
                    && !player.isPassenger()
                    && !player.isSprinting();
            CriticalHitEvent critEvent = CommonHooks.fireCriticalHit(player, entity, isCrit, isCrit ? 1.5F : 1.0F);
            isCrit = critEvent.isCriticalHit();
            if (isCrit)
            {
                damage *= critEvent.getDamageMultiplier();
                player.level().playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 1.0F);
                player.crit(entity);
            }
            else
            {
                player.level().playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, player.getSoundSource(), 1.0F, 1.0F);
            }
            System.out.println(damage);
            entity.hurt(source, damage);


            player.getCooldowns().addCooldown(this, 15);
            player.awardStat(Stats.ITEM_USED.get(this));
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));

            return InteractionResult.SUCCESS;

        }
            return InteractionResult.FAIL;

        }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return DelabItemAbilites.DEFAULT_DAGGER_ACTIONS.contains(itemAbility);
    }
}
