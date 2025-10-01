package net.crocodil.delab.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;


public class MagicalFrostEffect extends MobEffect {
    protected MagicalFrostEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        boolean flag = true;
        Iterable<ItemStack> armors = livingEntity.getArmorSlots();
        for (ItemStack itemStack : armors)
            if(itemStack.getItem() instanceof ArmorItem item)
                if ((item.getMaterial() == ArmorMaterials.LEATHER))
                    flag = false;
        if(flag)
        {
            int add = 3 + amplifier;
            int current = livingEntity.getTicksFrozen();
            livingEntity.setTicksFrozen(current + add);
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
