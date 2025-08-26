package net.crocodil.delab;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class DelabTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Delab.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DAGGER_ENCHANTABLE = createTag("enchantable/dagger");
        public static final TagKey<Item> HAMMER_ENCHANTABLE = createTag("enchantable/hammer");
        public static final TagKey<Item> MOB_BONUS_ENCHANTABLE = createTag("enchantable/mob_bonus");
        public static final TagKey<Item> CRUSHING_ENCHANTABLE = createTag("enchantable/crushing");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Delab.MODID, name));
        }
    }
    public static class Enchantments {

        public static final TagKey<Enchantment> LOOTING_EXCLUSIVE = createTag("exclusive_set/looting");
        public static final TagKey<Enchantment> EFFICIENCY_EXCLUSIVE = createTag("exclusive_set/efficiency");

        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace(name));
        }
    }
}