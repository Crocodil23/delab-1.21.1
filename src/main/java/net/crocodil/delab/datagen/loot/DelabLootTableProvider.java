package net.crocodil.delab.datagen.loot;

import net.crocodil.delab.loot.DelabLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DelabLootTableProvider extends LootTableProvider {
    public DelabLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, DelabLootTables.allBuiltin(), List.of(
                new LootTableProvider.SubProviderEntry(DelabBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(DelabEntityLootTableProvider::new, LootContextParamSets.ENTITY)
                ), provider);

    }
    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {

    }
}
