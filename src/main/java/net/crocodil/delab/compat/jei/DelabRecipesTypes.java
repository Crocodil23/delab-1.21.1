package net.crocodil.delab.compat.jei;

import mezz.jei.api.recipe.RecipeType;
import net.crocodil.delab.recipes.DelabRecipes;
import net.crocodil.delab.recipes.alloysFurnace.AlloysFurnaceRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public class DelabRecipesTypes {
    public static final RecipeType<RecipeHolder<AlloysFurnaceRecipe>> ALLOYS_FURNACE
            = RecipeType.createFromVanilla(DelabRecipes.ALLOYS_FURNACE_TYPE.get());
}
