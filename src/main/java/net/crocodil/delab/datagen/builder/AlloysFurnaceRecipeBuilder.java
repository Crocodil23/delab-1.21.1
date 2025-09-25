package net.crocodil.delab.datagen.builder;

import net.crocodil.delab.Delab;
import net.crocodil.delab.recipes.alloysFurnace.AlloysFurnaceRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

public class AlloysFurnaceRecipeBuilder implements RecipeBuilder {


    private NonNullList<Ingredient> ingredients = NonNullList.create();
    private Item result;

    private AlloysFurnaceRecipeBuilder(Item result)
    {;
        this.result = result;
    }
    public static AlloysFurnaceRecipeBuilder alloysFurnaceRecipe(Item result)
    {
        return new AlloysFurnaceRecipeBuilder( result);
    }
    public AlloysFurnaceRecipeBuilder addIngredient(Ingredient ingredientIn) {
        ingredients.add(ingredientIn);
        return this;
    }
    public AlloysFurnaceRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return addIngredient(Ingredient.of(tagIn));
    }

    public AlloysFurnaceRecipeBuilder addIngredient(ItemLike itemIn) {
        return addIngredient(Ingredient.of(itemIn));
    }
    @Override
    public RecipeBuilder unlockedBy(String s, Criterion<?> criterion) {
        return null;
    }

    @Override
    public RecipeBuilder group(@Nullable String s) {
        return null;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    public void build(RecipeOutput output) {
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(result);
        save(output, ResourceLocation.fromNamespaceAndPath(Delab.MODID, location.getPath()));
    }
    @Override
    public void save(RecipeOutput output, ResourceLocation id)
    {
        ResourceLocation recipeId = id.withPrefix("alloys/");
        Advancement.Builder advancementBuilder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId));

        AlloysFurnaceRecipe recipe = new AlloysFurnaceRecipe(
                ingredients.get(0),
                ingredients.get(1),
                ingredients.get(2),
                new ItemStack(result)
        );

        output.accept(recipeId, recipe, advancementBuilder.build(id.withPrefix("recipes/alloys/")));
    }
}
