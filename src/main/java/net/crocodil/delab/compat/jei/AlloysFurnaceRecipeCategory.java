package net.crocodil.delab.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.crocodil.delab.Delab;
import net.crocodil.delab.blocks.DelabBlocks;
import net.crocodil.delab.gui.AlloysFurnace.AlloyFurnaceMenu;
import net.crocodil.delab.recipes.DelabRecipes;
import net.crocodil.delab.recipes.alloysFurnace.AlloysFurnaceRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlloysFurnaceRecipeCategory implements IRecipeCategory<RecipeHolder <AlloysFurnaceRecipe>> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "alloys_furnace");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID,
            "textures/gui/alloys_furnace.png");
    public final IDrawableAnimated arrow;
    public final IDrawableAnimated lit;


    public static final RecipeType<AlloysFurnaceRecipe> ALLOYS_FURNACE_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, AlloysFurnaceRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public AlloysFurnaceRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 3, 5, 170, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(DelabBlocks.ALLOYS_FURNACE));

        arrow = helper.drawableBuilder(TEXTURE, 177, 0, 31, 48).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        lit = helper.drawableBuilder(TEXTURE, 177, 49, 14, 14).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<RecipeHolder<AlloysFurnaceRecipe>> getRecipeType() {
        return DelabRecipesTypes.ALLOYS_FURNACE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.delab.alloys_furnace");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<AlloysFurnaceRecipe> holder, IFocusGroup focuses) {
        AlloysFurnaceRecipe recipe = holder.value();
        builder.addSlot(RecipeIngredientRole.INPUT, 46, 15).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 46, 37).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 46, 59).addIngredients(recipe.getIngredients().get(2));

        //builder.addSlot(RecipeIngredientRole.INPUT, 12, 59).addItemStacks(getFuels());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 38).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void draw(RecipeHolder<AlloysFurnaceRecipe> holder, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 65, 21);
        lit.draw(guiGraphics, 12, 42);
    }

    private static List<ItemStack> getFuels() {
        List<ItemStack> fuels = new ArrayList<>();
        for (Item item : BuiltInRegistries.ITEM) {          // iterate the builtin item registry
            ItemStack stack = new ItemStack(item);
            if (AbstractFurnaceBlockEntity.isFuel(stack)) {
                fuels.add(stack);
            }
        }
        return fuels;
    }
}
