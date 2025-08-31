package net.crocodil.delab.recipes.alloysFurnace;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record AlloysFurnaceRecipeInput(NonNullList<ItemStack> inputs) implements RecipeInput
{

    @Override
    public ItemStack getItem(int i) {
        return switch (i)
        {
            case 0 -> inputs.get(0);
            case 1 -> inputs.get(1);
            case 2 -> inputs.get(2);
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + i);
        };
    }

    @Override
    public int size() {
        return 3;
    }
}
