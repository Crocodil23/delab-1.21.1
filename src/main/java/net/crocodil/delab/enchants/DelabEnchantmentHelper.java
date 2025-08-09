package net.crocodil.delab.enchants;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class DelabEnchantmentHelper
{
    public static int getEnchantmentLvl(Level level, ResourceKey<Enchantment> enchantment, ItemStack stack)
    {
        Registry<Enchantment> enchRegistry = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Holder.Reference<Enchantment> EncHolder = enchRegistry.getHolderOrThrow(enchantment);
        return  EnchantmentHelper.getItemEnchantmentLevel(EncHolder, stack);
    }
    
}
