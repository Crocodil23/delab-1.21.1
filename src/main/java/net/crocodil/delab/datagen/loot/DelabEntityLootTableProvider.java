package net.crocodil.delab.datagen.loot;

import net.crocodil.delab.entity.DelabEntityTypes;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DelabEntityLootTableProvider extends EntityLootSubProvider {

    protected DelabEntityLootTableProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        add(DelabEntityTypes.MUDAUR.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .name("rotten_flesh")
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .name("abomination_dust")
                                .setRolls(ConstantValue.exactly(1))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.4F, 0.15F))
                                .add(LootItem.lootTableItem(DelabItems.ABOMINATION_DUST)))
                        .withPool(LootPool.lootPool()
                                .name("adventure_upgrade_smithing_template")
                                .setRolls(ConstantValue.exactly(1))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.05F, 0.05F))
                                .add(LootItem.lootTableItem(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE)))
        );

        add(DelabEntityTypes.FROZEN_COWING.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .name("frozen_flesh")
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(DelabItems.FROZEN_FLESH)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .name("frozen_core")
                                .setRolls(ConstantValue.exactly(1))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.4F, 0.15F))
                                .add(LootItem.lootTableItem(DelabItems.FROZEN_CORE)))
                        .withPool(LootPool.lootPool()
                                .name("adventure_upgrade_smithing_template")
                                .setRolls(ConstantValue.exactly(1))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registries, 0.05F, 0.05F))
                                .add(LootItem.lootTableItem(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE)))
        );

    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return DelabEntityTypes.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::value);
    }
}
