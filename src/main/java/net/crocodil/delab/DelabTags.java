package net.crocodil.delab;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DelabTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Delab.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DAGGERS_ENCHANTABLE = createTag("enchantable/dagger");
        public static final TagKey<Item> MOB_BONUS_ENCHANTABLE = createTag("enchantable/mob_bonus");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Delab.MODID, name));
        }
    }
}