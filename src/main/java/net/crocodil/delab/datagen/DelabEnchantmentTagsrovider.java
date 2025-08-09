package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DelabEnchantmentTagsrovider extends EnchantmentTagsProvider {
    public DelabEnchantmentTagsrovider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Delab.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ResourceLocation id = DelabEnchantments.DOUBLE_STRIKE.location();
        tag(EnchantmentTags.NON_TREASURE).addOptional(id);
    }
}
