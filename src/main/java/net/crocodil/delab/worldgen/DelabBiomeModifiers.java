package net.crocodil.delab.worldgen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.DelabEntityTypes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;


public class DelabBiomeModifiers {

    public static final ResourceKey<BiomeModifier> FROZEN_COWING_SPAWN = registerKey("frozen_cowing_spawn");
    public static final ResourceKey<BiomeModifier> EVIL_SPITTER_SPAWN = registerKey("evil_spitter_spawn");
    public static final ResourceKey<BiomeModifier> EVIL_SPITTER_SPAWN_SUB = registerKey("evil_spitter_spawn_sub");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        var entities = context.lookup(Registries.ENTITY_TYPE);


        context.register(FROZEN_COWING_SPAWN, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SNOWY_PLAINS),
                        biomes.getOrThrow(Biomes.SNOWY_TAIGA),
                        biomes.getOrThrow(Biomes.ICE_SPIKES),
                        biomes.getOrThrow(Biomes.FROZEN_RIVER)),
                List.of(new MobSpawnSettings.SpawnerData(DelabEntityTypes.FROZEN_COWING.get(), 80, 2, 4))));

        context.register(EVIL_SPITTER_SPAWN, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE)),
                List.of(new MobSpawnSettings.SpawnerData(DelabEntityTypes.EVIL_SPITTER.get(), 100, 1, 1))));

        context.register(EVIL_SPITTER_SPAWN_SUB, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE)),
                List.of(new MobSpawnSettings.SpawnerData(DelabEntityTypes.EVIL_SPITTER.get(), 400, 1, 1))));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Delab.MODID, name));
    }
}
