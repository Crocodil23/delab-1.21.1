package net.crocodil.delab.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.crocodil.delab.Delab;
import net.crocodil.delab.blocks.DelabBlocks;
import net.crocodil.delab.gui.AlloysFurnace.AlloysFurnaceScreen;
import net.crocodil.delab.recipes.DelabRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;


@JeiPlugin
public class JeiDelabPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Delab.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AlloysFurnaceRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
       registration.addRecipes(DelabRecipesTypes.ALLOYS_FURNACE, recipeManager.getAllRecipesFor(DelabRecipes.ALLOYS_FURNACE_TYPE.get()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AlloysFurnaceScreen.class, 68, 26, 31, 48,
                AlloysFurnaceRecipeCategory.ALLOYS_FURNACE_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(DelabBlocks.ALLOYS_FURNACE.get().asItem()),
                AlloysFurnaceRecipeCategory.ALLOYS_FURNACE_RECIPE_RECIPE_TYPE);
    }
}