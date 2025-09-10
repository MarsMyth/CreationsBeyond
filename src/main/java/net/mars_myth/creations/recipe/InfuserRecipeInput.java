package net.mars_myth.creations.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public class InfuserRecipeInput implements RecipeInput {
    public static ItemStack input1;
    public static ItemStack input2;

    public InfuserRecipeInput(ItemStack input1, ItemStack input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> input1;
            case 1 -> input2;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return input1.isEmpty() && input2.isEmpty();
    }
}
