package net.crocodil.delab.recipes.alloysFurnace;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.crocodil.delab.recipes.DelabRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record AlloysFurnaceRecipe(Ingredient ingredient1,
                                  Ingredient ingredient2,
                                  Ingredient ingredient3,
                                  ItemStack output) implements Recipe<AlloysFurnaceRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingridients = NonNullList.create();
        ingridients.add(ingredient1);
        ingridients.add(ingredient2);
        ingridients.add(ingredient3);
        return ingridients;
    }

    @Override
    public boolean matches(AlloysFurnaceRecipeInput input, Level level) {
        if (level.isClientSide)
            return false;
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            result = false;
            for (int j = 0; j < 3; j++) {
                result = getIngredients().get(i).test(input.getItem(j));
                if (result)
                    break;
                ;
            }
            if (!result)
                break;
        }
        return result;
    }

    @Override
    public ItemStack assemble(AlloysFurnaceRecipeInput alloysFurnaceRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DelabRecipes.ALLOYS_FURNACE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return DelabRecipes.ALLOYS_FURNACE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AlloysFurnaceRecipe> {

        public static final MapCodec<AlloysFurnaceRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient_1").forGetter(AlloysFurnaceRecipe::ingredient1),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient_2").forGetter(AlloysFurnaceRecipe::ingredient2),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient_3").forGetter(AlloysFurnaceRecipe::ingredient3),
                ItemStack.CODEC.fieldOf("result").forGetter(AlloysFurnaceRecipe::output)
        ).apply(inst, AlloysFurnaceRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, AlloysFurnaceRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, AlloysFurnaceRecipe::ingredient1,
                        Ingredient.CONTENTS_STREAM_CODEC, AlloysFurnaceRecipe::ingredient2,
                        Ingredient.CONTENTS_STREAM_CODEC, AlloysFurnaceRecipe::ingredient3,
                        ItemStack.STREAM_CODEC, AlloysFurnaceRecipe::output,
                        AlloysFurnaceRecipe::new);

        @Override
        public MapCodec<AlloysFurnaceRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AlloysFurnaceRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
