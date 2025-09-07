package net.crocodil.delab.items.Hammers;

import net.crocodil.delab.DelabSounds;
import net.crocodil.delab.effects.DelabMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class AbominationHammerItem extends HammerItem
{

    public AbominationHammerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft)
    {
        super.releaseUsing(stack, level, entityLiving, timeLeft);
        if (entityLiving instanceof Player player) {
            BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
            if (hit.getType() == HitResult.Type.BLOCK) {
                player.playSound(DelabSounds.EARTH_STRIKE_SOUND.get(), 1f, 1f);
                BlockPos pos = hit.getBlockPos();
                boolean flag = level.getBlockState(pos).getBlock() != Blocks.AIR;
                if (flag) {
                    if (!level.isClientSide)
                    {
                        AABB aabb = new AABB(pos.getX() - 4, pos.getY()+2, pos.getZ() - 4,
                                pos.getX() + 4, pos.getY()+1 , pos.getZ() + 4);
                        List<LivingEntity> targets = level.getEntitiesOfClass(
                                LivingEntity.class,aabb);
                        for (LivingEntity target : targets) {
                            if (target == player) continue;
                            target.addEffect(new MobEffectInstance(DelabMobEffects.IN_MUD, 160, 0));
                        }
                    }
                }
            }
        }
    }
}
