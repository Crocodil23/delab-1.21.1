package net.crocodil.delab.items;

import net.crocodil.delab.Enityes.Spears.SpearMaterial;
import net.crocodil.delab.Enityes.Spears.ThrowingSpear;
import net.crocodil.delab.enchants.DelabEnchantmentHelper;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.List;

public class SpearItem extends TieredItem
{
    public SpearItem(Tier tier, Item.Properties properties) {
        super(tier, properties);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int attackDamage, float attackSpeed) {
        return createAttributes(tier, (float) attackDamage, attackSpeed);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, float damege, float attack_speed1) {
        return ItemAttributeModifiers.builder().
                add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                (double) damege + tier.getAttackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).
                add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double) attack_speed1,
                        AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).
                add(Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, +0.5, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).build();
    }
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    private static boolean isTooDamagedToUse(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (isTooDamagedToUse(itemstack)) {
            return InteractionResultHolder.fail(itemstack);
        } else if (EnchantmentHelper.getTridentSpinAttackStrength(itemstack, player) > 0.0F && !player.isInWaterOrRain()) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            int i = this.getUseDuration(stack, entityLiving) - timeLeft;
            if (i >= 10) {
                if (!level.isClientSide) {
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(entityLiving.getUsedItemHand()));
                    Tier tier = this.getTier();
                    SpearMaterial material = SpearMaterial.WOODEN;
                    if(tier == Tiers.STONE)
                        material = SpearMaterial.STONE;
                    else if(tier == Tiers.IRON)
                        material = SpearMaterial.IRON;
                    else if(tier == Tiers.GOLD)
                        material = SpearMaterial.GOLDEN;
                    else if(tier == Tiers.DIAMOND)
                        material = SpearMaterial.DIAMOND;
                    else if(tier == Tiers.NETHERITE)
                        material = SpearMaterial.NETHERITE;
                    int PTlvl = DelabEnchantmentHelper.getEnchantmentLvl(level, DelabEnchantments.POWERFUL_THROW, stack);
                    ThrowingSpear throwingSpear = new ThrowingSpear(level, player, stack, tier.getAttackDamageBonus() + 3 + PTlvl, material);
                    throwingSpear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                    if (player.hasInfiniteMaterials()) {
                        throwingSpear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(throwingSpear);
                    if (!player.hasInfiniteMaterials()) {
                        player.getInventory().removeItem(stack);
                    }
                }
            }
        }

    }
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
    {
        if(!Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.delab.shift_down"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.delab.spear_tooltip"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
