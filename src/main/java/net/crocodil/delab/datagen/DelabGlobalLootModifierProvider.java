package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.items.DelabItems;
import net.crocodil.delab.loot.DelabOneItemLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class DelabGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public DelabGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, Delab.MODID);
    }

    @Override
    protected void start() {

        addItemWithEnchantBonusFromEntity(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE.asItem(), EntityType.HUSK, 0.05F, 0.05F);
        addItemWithEnchantBonusFromEntity(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE.asItem(), EntityType.STRAY, 0.05F, 0.05F);
        addItemWithEnchantBonusFromEntity(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE.asItem(), EntityType.DROWNED, 0.05F, 0.05F);
        addItemWithEnchantBonusFromEntity(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE.asItem(), EntityType.BOGGED, 0.05F, 0.05F);
        addItemWithEnchantBonusFromEntity(DelabItems.ABOMINATION_DUST.asItem(), EntityType.BOGGED, 0.40F, 0.15F);
    }
    private void addItemWithEnchantBonusFromEntity(Item item, EntityType entityType, float base, float perLevelAfterFirst)
    {
        String itemName = BuiltInRegistries.ITEM.getKey(item).getPath();
        String EntityName =BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath();
        this.add(itemName + "_from_" + EntityName,
                new DelabOneItemLootModifier(new LootItemCondition[] {
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/" + EntityName)).build(),
                        LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(
                                registries,
                                base,
                                perLevelAfterFirst
                        ).build()
                },
                        item));
    }
}