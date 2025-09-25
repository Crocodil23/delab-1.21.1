package net.crocodil.delab.datagen;

import net.crocodil.delab.blocks.DelabBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class DelabLootTableProvider extends BlockLootSubProvider
{
    protected DelabLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(DelabBlocks.SEA_WORKSHOP.get());
        dropSelf(DelabBlocks.ALLOYS_FURNACE.get());
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return DelabBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
