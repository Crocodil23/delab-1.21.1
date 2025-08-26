package net.crocodil.delab.items;

import io.netty.util.internal.ThreadLocalRandom;
import net.crocodil.delab.DelabSounds;
import net.crocodil.delab.enchants.DelabEnchantmentHelper;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;



public class HammerItem extends PickaxeItem {

    public HammerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
    private static final ResourceLocation BASE_ATTACK_KNOCKBACK_ID =
             ResourceLocation.parse("delab:base_attack_knockback");
    public static ItemAttributeModifiers createAttributes(Tier tier, float attackDamage, float attackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID,
                                (double)(attackDamage + tier.getAttackDamageBonus()),
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID,
                                (double)attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(BASE_ATTACK_KNOCKBACK_ID, 1,AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .build();
    }
    public static List<BlockPos> getBlocksToBeDestroyed(BlockPos pos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();

        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if(traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }

        if(traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for(int x = -1; x <= 1; x++) {
                for(int y = -1; y <= 1; y++) {
                    positions.add(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + y));
                }
            }
        }

        if(traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for(int x = -1; x <= 1; x++) {
                for(int y = -1; y <= 1; y++) {
                    positions.add(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ()));
                }
            }
        }

        if(traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for(int x = -1; x <= 1; x++) {
                for(int y = -1; y <= 1; y++) {
                    positions.add(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ() + x));
                }
            }
        }

        return positions;
    }
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(!player.getCooldowns().isOnCooldown(this))
        {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
        return InteractionResultHolder.fail(itemstack);
    }
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
            if (hit.getType() == HitResult.Type.BLOCK) {
                player.playSound(DelabSounds.EARTH_STRIKE_SOUND.get(), 1f, 1f);
                BlockPos pos = hit.getBlockPos();
                boolean flag = level.getBlockState(pos).getBlock() != Blocks.AIR;
                if(flag)
                {
                    if (!level.isClientSide) {
                        player.getCooldowns().addCooldown(this, 200);
                        player.awardStat(Stats.ITEM_USED.get(this));
                        AABB aabb = new AABB(pos.getX() - 4, pos.getY()+2, pos.getZ() - 4,
                                pos.getX() + 4, pos.getY()+1 , pos.getZ() + 4);
                        List<LivingEntity> targets = level.getEntitiesOfClass(
                                LivingEntity.class,aabb);
                        for (LivingEntity target : targets) {
                            if (target == player) continue;
                            DamageSource source =  player.damageSources().playerAttack(player);
                            TieredItem item = (TieredItem) stack.getItem();
                            Tier tier = item.getTier();
                            int ESLlvl = DelabEnchantmentHelper.getEnchantmentLvl(level, DelabEnchantments.EARTH_STRIKE, stack);
                            float dmg = 2.0F + (tier.getAttackDamageBonus() * 0.5F) + ESLlvl;
                            boolean damaged = target.hurt(source, dmg);

                            if (damaged) {
                                double res = target.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getBaseValue();
                                stack.hurtAndBreak(1, target, EquipmentSlot.MAINHAND);
                                target.setDeltaMovement(target.getDeltaMovement().add((double)0.0F, 0.50F - res, (double)0.0F));
                            }
                        }

                    }
                    else
                    {
                        for(int x = -4; x <= 4; x++)
                        {
                            for(int z = -4; z <= 4; z++)
                            {
                                BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                                BlockState blockState = level.getBlockState(tmp);
                                System.out.println(blockState);
                                if(!blockState.isAir())
                                {
                                    QuakeParticle(level, blockState, tmp);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static void QuakeParticle(Level level, BlockState blockState, BlockPos pos)
    {
        int counts = 8 + ThreadLocalRandom.current().nextInt(5);
        for(int i = 0; i < counts; i++)
        {
            float x = 0.01F * (ThreadLocalRandom.current().nextInt(50) - 25);
            float z = 0.01F * (ThreadLocalRandom.current().nextInt(50) - 25);

            ParticleOptions particleOpt = new BlockParticleOption(
                    ParticleTypes.BLOCK, blockState);
            level.addParticle(
                    particleOpt,
                    pos.getX() + x,
                    pos.getY() + 1,
                    pos.getZ() + z,
                    0,
                    0,
                    0
            );
        }
    }




    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        float base = super.getDestroySpeed(stack, state);
        return base * 0.70F;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
    {
        if(!Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.delab.shift_down"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.delab.hammer_tooltip"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
