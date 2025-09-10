package net.mars_myth.creations.recipe;

import net.mars_myth.creations.init.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class InfuserRecipe implements Recipe<InfuserRecipeInput> {
    private final Ingredient inputItem1;
    private final Ingredient inputItem2;
    private final ItemStack output;

    public InfuserRecipe(Ingredient inputItem1, Ingredient inputItem2, ItemStack output) {
        this.inputItem1 = inputItem1;
        this.inputItem2 = inputItem2;
        this.output = output;
    }

    @Override
    public boolean matches(InfuserRecipeInput input, World world) {
        return (inputItem1.test(input.getStackInSlot(0)) && inputItem2.test(input.getStackInSlot(1))) ||
                (inputItem1.test(input.getStackInSlot(1)) && inputItem2.test(input.getStackInSlot(0)));
    }

    @Override
    public ItemStack craft(InfuserRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.INFUSING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.INFUSING_TYPE;
    }

    public ItemStack output() {
        return output;
    }

    public Ingredient getinputItem1() {
        return inputItem1;
    }

    public Ingredient getinputItem2() {
        return inputItem2;
    }
}