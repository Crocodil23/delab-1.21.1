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

    public static final ResourceKey<BiomeModifier> REMOVE_ZOMBIE_FROM_SWAMPS = registerKey("remove_zombie_from_swamps");
    public static final ResourceKey<BiomeModifier> MUDAUR_SPAWN = registerKey("mudaur_spawn");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        var entities = context.lookup(Registries.ENTITY_TYPE);

        /*
        context.register(REMOVE_ZOMBIE_FROM_SWAMPS, new BiomeModifiers.RemoveSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                HolderSet.direct(entities.getOrThrow(EntityType.ZOMBIE.builtInRegistryHolder().key()))));

        context.register(MUDAUR_SPAWN, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                List.of(new MobSpawnSettings.SpawnerData(DelabEntityTypes.MUDAUR.get(), 20, 2, 4),
                        new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 1000, 2, 4))));
                        
         */



    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Delab.MODID, name));
    }
}
