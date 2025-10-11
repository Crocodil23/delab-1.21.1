package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.DelabTags;
import net.crocodil.delab.blocks.DelabBlocks;
import net.crocodil.delab.datagen.builder.AlloysFurnaceRecipeBuilder;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
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

        spearCraft(ItemTags.PLANKS, DelabItems.WOODEN_SPEAR.get(), out);
        spearCraft(ItemTags.STONE_TOOL_MATERIALS, DelabItems.STONE_SPEAR.get(), out);
        spearCraft(Items.IRON_INGOT, DelabItems.IRON_SPEAR.get(), out);
        spearCraft(Items.GOLD_INGOT, DelabItems.GOLDEN_SPEAR.get(), out);
        spearCraft(Items.DIAMOND, DelabItems.DIAMOND_SPEAR.get(), out);

        daggerCraft(ItemTags.PLANKS, DelabItems.WOODEN_DAGGER.get(), out);
        daggerCraft(ItemTags.STONE_TOOL_MATERIALS, DelabItems.STONE_DAGGER.get(), out);
        daggerCraft(Items.IRON_INGOT, DelabItems.IRON_DAGGER.get(), out);
        daggerCraft(Items.GOLD_INGOT, DelabItems.GOLDEN_DAGGER.get(), out);
        daggerCraft(Items.DIAMOND, DelabItems.DIAMOND_DAGGER.get(), out);

        hammerCraft(ItemTags.PLANKS, DelabItems.WOODEN_HAMMER.get(), out);
        hammerCraft(ItemTags.STONE_TOOL_MATERIALS, DelabItems.STONE_HAMMER.get(), out);
        hammerCraft(Items.IRON_INGOT, DelabItems.IRON_HAMMER.get(), out);
        hammerCraft(Items.GOLD_INGOT, DelabItems.GOLDEN_HAMMER.get(), out);
        hammerCraft(Items.DIAMOND, DelabItems.DIAMOND_HAMMER.get(), out);

        netheriteSmithing(DelabItems.DIAMOND_DAGGER.get(), DelabItems.NETHERITE_DAGGER.get(), out);
        netheriteSmithing(DelabItems.DIAMOND_HAMMER.get(), DelabItems.NETHERITE_HAMMER.get(), out);
        netheriteSmithing(DelabItems.DIAMOND_SPEAR.get(), DelabItems.NETHERITE_SPEAR.get(), out);

        abominationSmithing(DelabItems.IRON_HAMMER.get(), DelabItems.ABOMINATION_HAMMER.get(), out);
        abominationSmithing(Items.BOW, DelabItems.ABOMINATION_BOW.get(), out);
        abominationSmithing(Items.IRON_HELMET, DelabItems.ABOMINATION_HELMET.get(), out);
        abominationSmithing(Items.IRON_CHESTPLATE, DelabItems.ABOMINATION_CHESTPLATE.get(), out);
        abominationSmithing(Items.IRON_LEGGINGS, DelabItems.ABOMINATION_LEGGINGS.get(), out);
        abominationSmithing(Items.IRON_BOOTS, DelabItems.ABOMINATION_BOOTS.get(), out);

        frozenSmithing(Items.IRON_AXE, DelabItems.FROZEN_AXE.get(), out);
        frozenSmithing(Items.BOW, DelabItems.FROZEN_BOW.get(), out);
        frozenSmithing(Items.IRON_HELMET, DelabItems.FROZEN_HELMET.get(), out);
        frozenSmithing(Items.IRON_CHESTPLATE, DelabItems.FROZEN_CHESTPLATE.get(), out);
        frozenSmithing(Items.IRON_LEGGINGS, DelabItems.FROZEN_LEGGINGS.get(), out);
        frozenSmithing(Items.IRON_BOOTS, DelabItems.FROZEN_BOOTS.get(), out);

        wildSmithing(Items.IRON_SWORD, DelabItems.WILD_KATANA.get(), out);
        wildSmithing(DelabItems.IRON_DAGGER.get(), DelabItems.WILD_WAKIZASHI.get(), out);
        wildSmithing(Items.IRON_HELMET, DelabItems.WILD_HELMET.get(), out);
        wildSmithing(Items.IRON_CHESTPLATE, DelabItems.WILD_CHESTPLATE.get(), out);
        wildSmithing(Items.IRON_LEGGINGS, DelabItems.WILD_LEGGINGS.get(), out);
        wildSmithing(Items.IRON_BOOTS, DelabItems.WILD_BOOTS.get(), out);



        foodCooking(DelabItems.FROZEN_FLESH.get(), DelabItems.FRESH_FLESH.get(), out);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelabItems.DEFENDER_OFFERING, 1)
                .requires(Items.BOWL)
                .requires(DelabItems.HARD_LEAF)
                .requires(DelabItems.EVIL_SPITTER_SEED)
                .requires(DelabItems.ANCIENT_TOFU)
                .unlockedBy("has_ancient_tofu", has(DelabItems.ANCIENT_TOFU))
                .save(out);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, DelabItems.MUD_BALL, 8)
                .requires(Items.MUD)
                .requires(DelabItems.ABOMINATION_DUST)
                .unlockedBy("has_abomination_dust", has(DelabItems.ABOMINATION_DUST))
                .save(out);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, DelabItems.FROZEN_BALL, 8)
                .requires(Items.SNOW_BLOCK)
                .requires(DelabItems.FROZEN_CORE)
                .unlockedBy("has_frozen_core", has(DelabItems.FROZEN_CORE))
                .save(out);


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

        AlloysFurnaceRecipeBuilder.alloysFurnaceRecipe(DelabItems.WILD_INGOT.get())
                .addIngredient(Items.IRON_INGOT)
                .addIngredient(DelabItems.FURIOUS_SPIRIT)
                .addIngredient(DelabItems.HARD_LEAF)
                .build(out);
    }

    private static String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    private static String getItemTagsName(TagKey<Item> tag)
    {
        String name = "";
        if(tag == ItemTags.PLANKS)
            name = getItemName(Items.STICK);
        else if(tag == ItemTags.STONE_TOOL_MATERIALS)
            name = getItemName(Items.COBBLESTONE);
        return name;
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> getUnlocked(TagKey<Item> tag)
    {
        if(tag == ItemTags.PLANKS)
            return has(Items.STICK);
        else return has(tag);
    }

    private static void spearCraft(Item material, Item spear, RecipeOutput out)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, spear)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy("has_" + getItemName(material), has(material))
                .save(out);
    }

    private static void spearCraft(TagKey<Item> material, Item spear, RecipeOutput out)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, spear)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy("has_" + getItemTagsName(material), getUnlocked(material))
                .save(out);
    }

    private static void daggerCraft(Item material, Item dagger, RecipeOutput out)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, dagger)
                .pattern("A")
                .pattern("B")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy("has_" + getItemName(material), has(material))
                .save(out);
    }

    private static void daggerCraft(TagKey<Item> material, Item dagger, RecipeOutput out)
    {

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, dagger)
                .pattern("A")
                .pattern("B")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy("has_" + getItemTagsName(material), getUnlocked(material))
                .save(out);
    }

    private static void hammerCraft(Item material, Item hammer, RecipeOutput out)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, hammer)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy("has_"+getItemName(material), has(material))
                .save(out);
    }

    private static void hammerCraft(TagKey<Item> material, Item hammer, RecipeOutput out)
    {

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, hammer)
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', material)
                .define('B', Items.STICK)
                .unlockedBy("has_"+getItemTagsName(material), getUnlocked(material))
                .save(out);
    }

    private static void netheriteSmithing(Item item, Item upgrade, RecipeOutput out)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(item),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        upgrade)
                .unlocks("has_" + getItemName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(out, Delab.MODID + ":" + getItemName(upgrade));
    }

    private static void abominationSmithing(Item item, Item upgrade, RecipeOutput out)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(item),
                        Ingredient.of(DelabItems.ABOMINATION_INGOT),
                        RecipeCategory.COMBAT,
                        upgrade)
                .unlocks("has_" + getItemName(DelabItems.ABOMINATION_INGOT), has((DelabItems.ABOMINATION_INGOT)))
                .save(out, Delab.MODID + ":" + getItemName(upgrade));
    }

    private static void frozenSmithing(Item item, Item upgrade, RecipeOutput out)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(item),
                        Ingredient.of(DelabItems.FROZEN_INGOT),
                        RecipeCategory.COMBAT,
                        upgrade)
                .unlocks("has_" + getItemName(DelabItems.FROZEN_INGOT), has((DelabItems.FROZEN_INGOT)))
                .save(out, Delab.MODID + ":" + getItemName(upgrade));
    }

    private static void wildSmithing(Item item, Item upgrade, RecipeOutput out)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(item),
                        Ingredient.of(DelabItems.WILD_INGOT),
                        RecipeCategory.COMBAT,
                        upgrade)
                .unlocks("has_" + getItemName(DelabItems.WILD_INGOT), has((DelabItems.WILD_INGOT)))
                .save(out, Delab.MODID + ":" + getItemName(upgrade));
    }

    private static void foodCooking(Item input,Item result, RecipeOutput out)
    {
        String name_result = getItemName(result);
        String name_input = getItemName(input);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F,
                        200)
                .unlockedBy("has_" + name_input, has(input))
                .save(out, Delab.MODID + ":" + name_result + "_smelting");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F,
                        200)
                .unlockedBy("has_" + name_input, has(input))
                .save(out, Delab.MODID + ":" + name_result + "_smoking");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input),
                        RecipeCategory.FOOD,
                        result,
                        0.35F,
                        200)
                .unlockedBy("has_" + name_input, has(input))
                .save(out, Delab.MODID + ":" + name_result + "_campfire_cooking");
    }
}
