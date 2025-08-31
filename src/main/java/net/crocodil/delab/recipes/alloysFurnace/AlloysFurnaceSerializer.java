package net.crocodil.delab.recipes.alloysFurnace;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class AlloysFurnaceSerializer implements RecipeSerializer<AlloysFurnaceRecipe> {

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
