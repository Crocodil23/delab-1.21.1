package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.DelabTags;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DelabItemTagsProvider extends ItemTagsProvider {

    public DelabItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                 CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Delab.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DelabTags.Items.DAGGER_ENCHANTABLE)
                .add(DelabItems.WOODEN_DAGGER.asItem())
                .add(DelabItems.STONE_DAGGER.asItem())
                .add(DelabItems.IRON_DAGGER.asItem())
                .add(DelabItems.GOLDEN_DAGGER.asItem())
                .add(DelabItems.DIAMOND_DAGGER.asItem())
                .add(DelabItems.NETHERITE_DAGGER.asItem());

        tag(DelabTags.Items.HAMMER_ENCHANTABLE)
                .add(DelabItems.WOODEN_HAMMER.asItem())
                .add(DelabItems.STONE_HAMMER.asItem())
                .add(DelabItems.IRON_HAMMER.asItem())
                .add(DelabItems.GOLDEN_HAMMER.asItem())
                .add(DelabItems.DIAMOND_HAMMER.asItem())
                .add(DelabItems.NETHERITE_HAMMER.asItem());

        tag(DelabTags.Items.SPEAR_ENCHANTABLE)
                .add(DelabItems.WOODEN_SPEAR.asItem())
                .add(DelabItems.STONE_SPEAR.asItem())
                .add(DelabItems.IRON_SPEAR.asItem())
                .add(DelabItems.GOLDEN_SPEAR.asItem())
                .add(DelabItems.DIAMOND_SPEAR.asItem())
                .add(DelabItems.NETHERITE_SPEAR.asItem());

        tag(DelabTags.Items.MOB_BONUS_ENCHANTABLE)
                .addTag(DelabTags.Items.DAGGER_ENCHANTABLE)
                .addTag(ItemTags.SWORD_ENCHANTABLE)
                .addTag(DelabTags.Items.HAMMER_ENCHANTABLE)
                .addTag(DelabTags.Items.SPEAR_ENCHANTABLE);

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(DelabTags.Items.DAGGER_ENCHANTABLE)
                .addTag(DelabTags.Items.HAMMER_ENCHANTABLE)
                .addTag(DelabTags.Items.SPEAR_ENCHANTABLE);

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .addTag(DelabTags.Items.DAGGER_ENCHANTABLE)
                .addTag(DelabTags.Items.HAMMER_ENCHANTABLE)
                .addTag(DelabTags.Items.SPEAR_ENCHANTABLE);

        tag(ItemTags.MINING_ENCHANTABLE)
                .addTag(DelabTags.Items.HAMMER_ENCHANTABLE);

        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(DelabTags.Items.HAMMER_ENCHANTABLE);

        tag(DelabTags.Items.CRUSHING_ENCHANTABLE)
                .addTag(DelabTags.Items.HAMMER_ENCHANTABLE)
                .add(Items.MACE);

        tag(DelabTags.Items.LOYALTY_ENCHANTABLE)
                .addTag(DelabTags.Items.SPEAR_ENCHANTABLE)
                .add(Items.TRIDENT);
    }
}
