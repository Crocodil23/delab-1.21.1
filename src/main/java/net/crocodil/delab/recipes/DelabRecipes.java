package net.crocodil.delab.recipes;

import net.crocodil.delab.Delab;
import net.crocodil.delab.recipes.alloysFurnace.AlloysFurnaceRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DelabRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Delab.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Delab.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AlloysFurnaceRecipe>> ALLOYS_FURNACE_SERIALIZER =
            SERIALIZERS.register("alloys_furnace", AlloysFurnaceRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<AlloysFurnaceRecipe>> ALLOYS_FURNACE_TYPE =
            TYPES.register("alloys_furnace", () -> new RecipeType<AlloysFurnaceRecipe>() {
                @Override
                public String toString() {
                    return "alloys_furnace";
                }
            });


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
