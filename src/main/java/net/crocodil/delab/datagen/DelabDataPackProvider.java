package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.crocodil.delab.worldgen.DelabBiomeModifiers;
import net.crocodil.delab.worldgen.DelabConfiguredFeatures;
import net.crocodil.delab.worldgen.DelabPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DelabDataPackProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, DelabEnchantments::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, DelabConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, DelabPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, DelabBiomeModifiers::bootstrap);

    public DelabDataPackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, BUILDER, Set.of(Delab.MODID));
    }
}
