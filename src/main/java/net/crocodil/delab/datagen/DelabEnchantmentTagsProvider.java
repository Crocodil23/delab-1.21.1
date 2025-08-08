package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DelabEnchantmentTagsProvider extends EnchantmentTagsProvider {
    public DelabEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                        @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, Delab.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        /*
        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .add(DelabEnchantments.DOUBLE_STRIKE)
                */
    }
}
