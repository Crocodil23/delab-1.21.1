package net.crocodil.delab.enchants;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.HashMap;


public class DelabEnchantmentHelper
{

    public static int getEnchantmentLvl(Level level, ResourceKey<Enchantment> enchantment, ItemStack stack)
    {
        Registry<Enchantment> enchRegistry = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Holder.Reference<Enchantment> EncHolder = enchRegistry.getHolderOrThrow(enchantment);
        return  EnchantmentHelper.getItemEnchantmentLevel(EncHolder, stack);
    }

    public static ItemStack getHeadForEntity(LivingEntity entity) {
        if (entity.getType() == EntityType.ZOMBIE)
        {
            return new ItemStack(Items.ZOMBIE_HEAD);
        } else if (entity.getType() == EntityType.CREEPER)
        {
            return new ItemStack(Items.CREEPER_HEAD);
        } else if (entity.getType() == EntityType.SKELETON)
        {
            return new ItemStack(Items.SKELETON_SKULL);
        } else if (entity.getType() == EntityType.WITHER_SKELETON)
        {
            return new ItemStack(Items.WITHER_SKELETON_SKULL);
        }
        else if (entity.getType() == EntityType.PIGLIN)
        {
            return new ItemStack(Items.PIGLIN_HEAD);
        }
        return ItemStack.EMPTY;
    }
}
