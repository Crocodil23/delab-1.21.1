package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.DelabTags;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DelabEnchantmentTagsprovider extends EnchantmentTagsProvider {
    public DelabEnchantmentTagsprovider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Delab.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.NON_TREASURE)
                .addOptional(DelabEnchantments.DOUBLE_STRIKE.location())
                .addOptional(DelabEnchantments.HEAD_HUNTER.location())
                .addOptional(DelabEnchantments.SHADOW_STRIKE.location())
                .addOptional(DelabEnchantments.DESTROYER.location())
                .addOptional(DelabEnchantments.EARTH_STRIKE.location())
                .addOptional(DelabEnchantments.POWERFUL_THROW.location())
                .addOptional(DelabEnchantments.CAVALRY_STRIKE.location());;


        tag(EnchantmentTags.DAMAGE_EXCLUSIVE)
                .addOptional(DelabEnchantments.DOUBLE_STRIKE.location())
                .addOptional(DelabEnchantments.SHADOW_STRIKE.location())
                .addOptional(DelabEnchantments.EARTH_STRIKE.location())
                .addOptional(DelabEnchantments.POWERFUL_THROW.location())
                .addOptional(DelabEnchantments.CAVALRY_STRIKE.location());;

        tag(DelabTags.Enchantments.LOOTING_EXCLUSIVE)
                .addOptional(Enchantments.LOOTING.location())
                .addOptional(DelabEnchantments.HEAD_HUNTER.location());
        tag(DelabTags.Enchantments.EFFICIENCY_EXCLUSIVE)
                .addOptional(Enchantments.EFFICIENCY.location())
                .addOptional(DelabEnchantments.DESTROYER.location());
    }
}
