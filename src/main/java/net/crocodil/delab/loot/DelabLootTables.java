package net.crocodil.delab.loot;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.Set;

public class DelabLootTables
{
    private static final Set<ResourceKey<LootTable>> DELAB_LOOT_TABLES = Sets.newHashSet();
    private static final Set<ResourceKey<LootTable>> DELAB_IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(DELAB_LOOT_TABLES);

    public static Set<ResourceKey<LootTable>> allBuiltin() {
        return DELAB_IMMUTABLE_LOCATIONS;
    }
}
