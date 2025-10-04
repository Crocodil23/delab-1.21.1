package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.datagen.loot.DelabGlobalLootModifierProvider;
import net.crocodil.delab.datagen.loot.DelabLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;
@SuppressWarnings("unused")
@EventBusSubscriber(modid = Delab.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DelabDataGenerators
{
    @SubscribeEvent
    public static void gaterData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        PackOutput out = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();



        BlockTagsProvider blockTags = new DelabBlockTagProvider(out, lookupProvider, existingFileHelper);

        gen.addProvider(event.includeServer(), new DelabLootTableProvider(out, lookupProvider));
        gen.addProvider(event.includeServer(), new DelabDataPackProvider(out, lookupProvider));
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new DelabItemTagsProvider(out,
                lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        gen.addProvider(event.includeServer(), new DelabEnchantmentTagsprovider(out, lookupProvider, existingFileHelper));
        gen.addProvider(event.includeServer(), new DelabRecipeProvider(out, lookupProvider));
        gen.addProvider(event.includeServer(),new DelabDataMaProvider(out, lookupProvider));
        gen.addProvider(event.includeServer(), new DelabGlobalLootModifierProvider(out, lookupProvider));


        gen.addProvider(event.includeClient(), new DelabBlockStateProvider(out, existingFileHelper));
        gen.addProvider(event.includeClient(), new DelabItemModelProvider(out, existingFileHelper));
    }
}
