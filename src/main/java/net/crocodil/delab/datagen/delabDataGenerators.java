package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@SuppressWarnings("unused")
@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.MOD)
public class delabDataGenerators
{
    @SubscribeEvent
    public static void gaterData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        PackOutput out = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        gen.addProvider(event.includeServer(), new LootTableProvider(out, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(DelabLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
    }
}
