package net.crocodil.delab.datagen;

import net.crocodil.delab.items.DelabItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class DelabDataMaProvider extends DataMapProvider {
    protected DelabDataMaProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(DelabItems.WOODEN_DAGGER, new FurnaceFuel(200), false)
                .add(DelabItems.WOODEN_HAMMER, new FurnaceFuel(200), false)
                .add(DelabItems.WOODEN_SPEAR, new FurnaceFuel(200), false);
    }
}