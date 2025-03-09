package net.mars_myth.creations.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record InfuserRecipeInput(ItemStack input, ItemStack input2) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot == 0) {
            return input;
        }
        return input2;
    }

    @Override
    public int getSize() {
        return 1;
    }
}