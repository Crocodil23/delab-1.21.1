package net.crocodil.delab.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class DelabToolTiers {
    public static final Tier ABOMINATION_INGOT = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            313,
            7.5F,
            2.5F,
            18,
            () -> Ingredient.of(DelabItems.ABOMINATION_INGOT));
}
