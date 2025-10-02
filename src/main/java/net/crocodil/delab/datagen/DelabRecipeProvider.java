package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.DelabTags;
import net.crocodil.delab.blocks.DelabBlocks;
import net.crocodil.delab.datagen.builder.AlloysFurnaceRecipeBuilder;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class DelabRecipeProvider extends RecipeProvider {

    public DelabRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput out) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.WOODEN_DAGGER)
                .pattern("A")
                .pattern("B")
                .define('A', ItemTags.PLANKS)
                .define('B', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.STONE_DAGGER)
                .pattern("A")
                .pattern("B")
                .define('A', ItemTags.STONE_TOOL_MATERIALS)
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.IRON_DAGGER)
                .pattern("A")
                .pattern("B")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.GOLDEN_DAGGER)
                .pattern("A")
                .pattern("B")
                .define('A', Items.GOLD_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.DIAMOND_DAGGER)
                .pattern("A")
                .pattern("B")
                .define('A', Items.DIAMOND)
                .define('B', Items.STICK)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(out);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(DelabItems.DIAMOND_DAGGER.get()),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.COMBAT,
                DelabItems.NETHERITE_DAGGER.get())
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(out, Delab.MODID + ":netherite_dagger");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.WOODEN_HAMMER)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', ItemTags.PLANKS)
                .define('B', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.STONE_HAMMER)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', ItemTags.STONE_TOOL_MATERIALS)
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.IRON_HAMMER)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.GOLDEN_HAMMER)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', Items.GOLD_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.DIAMOND_HAMMER)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', Items.DIAMOND)
                .define('B', Items.STICK)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(out);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(DelabItems.DIAMOND_HAMMER.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.NETHERITE_HAMMER.get())
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(out, Delab.MODID + ":netherite_hammer");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(DelabItems.IRON_HAMMER.get()),
                        Ingredient.of(DelabItems.ABOMINATION_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.ABOMINATION_HAMMER.get())
                .unlocks("has_abomination_ingot", has(DelabItems.ABOMINATION_INGOT))
                .save(out, Delab.MODID + ":abomination_hammer");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.IRON_AXE),
                        Ingredient.of(DelabItems.FROZEN_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.FROZEN_AXE.get())
                .unlocks("has_frozen_ingot", has(DelabItems.ABOMINATION_INGOT))
                .save(out, Delab.MODID + ":frozen_axe");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.BOW),
                        Ingredient.of(DelabItems.ABOMINATION_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.ABOMINATION_BOW.get())
                .unlocks("has_abomination_ingot", has(DelabItems.ABOMINATION_INGOT))
                .save(out, Delab.MODID + ":abomination_bow");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.WOODEN_SPEAR)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', ItemTags.PLANKS)
                .define('B', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.STONE_SPEAR)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', ItemTags.STONE_TOOL_MATERIALS)
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.IRON_SPEAR)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.GOLDEN_SPEAR)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', Items.GOLD_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DelabItems.DIAMOND_SPEAR)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', Items.DIAMOND)
                .define('B', Items.STICK)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(out);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(DelabItems.DIAMOND_SPEAR.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.NETHERITE_SPEAR.get())
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(out, Delab.MODID + ":netherite_spear");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DelabBlocks.ALLOYS_FURNACE)
                .pattern("ACA")
                .pattern("BBB")
                .pattern("ABA")
                .define('A', Items.COPPER_BLOCK)
                .define('B', Items.STONE_BRICKS)
                .define('C', Items.FURNACE)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(out);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.COPPER_INGOT)
                .define('B', DelabTags.Items.ALLOYS_TIER_1)
                .define('C',DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_adventure_upgrade_smithing_template", has(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE))
                .save(out);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, DelabItems.MUD_BALL, 8)
                .requires(Items.MUD)
                .requires(DelabItems.ABOMINATION_DUST)
                .unlockedBy("has_abomination_dust", has(DelabItems.ABOMINATION_DUST))
                .save(out);


        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.IRON_HELMET),
                        Ingredient.of(DelabItems.ABOMINATION_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.ABOMINATION_HELMET.get())
                .unlocks("has_abomination_ingot", has(DelabItems.ABOMINATION_INGOT))
                .save(out, Delab.MODID + ":abomination_helmet");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.IRON_CHESTPLATE),
                        Ingredient.of(DelabItems.ABOMINATION_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.ABOMINATION_CHESTPLATE.get())
                .unlocks("has_abomination_ingot", has(DelabItems.ABOMINATION_INGOT))
                .save(out, Delab.MODID + ":abomination_chestplate");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.IRON_LEGGINGS),
                        Ingredient.of(DelabItems.ABOMINATION_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.ABOMINATION_LEGGINGS.get())
                .unlocks("has_abomination_ingot", has(DelabItems.ABOMINATION_INGOT))
                .save(out, Delab.MODID + ":abomination_leggings");

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.IRON_BOOTS),
                        Ingredient.of(DelabItems.ABOMINATION_INGOT),
                        RecipeCategory.COMBAT,
                        DelabItems.ABOMINATION_BOOTS.get())
                .unlocks("has_abomination_ingot", has(DelabItems.ABOMINATION_INGOT))
                .save(out, Delab.MODID + ":abomination_boots");

       foodCooking(DelabItems.FROZEN_FLESH.get(), DelabItems.FRESH_FLESH.get(), out);

       AlloysFurnaceRecipeBuilder.alloysFurnaceRecipe(DelabItems.ABOMINATION_INGOT.get())
               .addIngredient(Items.IRON_INGOT)
               .addIngredient(DelabItems.ABOMINATION_DUST)
               .addIngredient(Items.SLIME_BALL)
               .build(out);

        AlloysFurnaceRecipeBuilder.alloysFurnaceRecipe(DelabItems.FROZEN_INGOT.get())
                .addIngredient(Items.IRON_INGOT)
                .addIngredient(DelabItems.FROZEN_CORE)
                .addIngredient(Items.SNOW_BLOCK)
                .build(out);
    }

    private static String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    private static void foodCooking(Item input,Item result, RecipeOutput out)
    {
        String name = getItemName(result);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F,
                        200)
                .unlockedBy("has_frozen_flesh", has(input))
                .save(out, Delab.MODID + ":" + name + "_smelting");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F,
                        200)
                .unlockedBy("has_frozen_flesh", has(input))
                .save(out, Delab.MODID + ":" + name + "_smoking");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F,
                        200)
                .unlockedBy("has_frozen_flesh", has(input))
                .save(out, Delab.MODID + ":" + name + "_campfire_cooking");
    }
}
