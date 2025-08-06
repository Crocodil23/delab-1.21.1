package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.blocks.DelabBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DelabBlockTagProvider extends BlockTagsProvider {
    public DelabBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Delab.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DelabBlocks.SEA_WORKSHOP.get());
        tag((BlockTags.NEEDS_DIAMOND_TOOL))
                .add(DelabBlocks.SEA_WORKSHOP.get());
    }
}
