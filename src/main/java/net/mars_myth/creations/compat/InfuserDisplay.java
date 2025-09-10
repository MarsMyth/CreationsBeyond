package net.mars_myth.creations.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.mars_myth.creations.recipe.InfuserRecipe;
import net.minecraft.recipe.RecipeEntry;

import java.util.ArrayList;
import java.util.List;

public class InfuserDisplay extends BasicDisplay {
    public InfuserDisplay(RecipeEntry<InfuserRecipe> recipe) {
        super(
                getInputs(recipe),
                getOutputs(recipe)
        );

        // Debug logging
        System.out.println("Creating InfuserDisplay for recipe: " + recipe.id());
        System.out.println("Ingredients count: " + recipe.value().getIngredients().size());
        System.out.println("Has result: " + (recipe.value().getResult(null) != null));
    }

    private static List<EntryIngredient> getInputs(RecipeEntry<InfuserRecipe> recipe) {
        List<EntryIngredient> inputs = new ArrayList<>();

        try {
            EntryIngredient ingredient1 = EntryIngredients.ofIngredient(recipe.value().getinputItem1());
            EntryIngredient ingredient2 = EntryIngredients.ofIngredient(recipe.value().getinputItem2());

            System.out.println("Input 1 has " + ingredient1.size() + " possibilities");
            System.out.println("Input 2 has " + ingredient2.size() + " possibilities");

            inputs.add(ingredient1);
            inputs.add(ingredient2);
        } catch (Exception e) {
            System.err.println("Error processing recipe inputs: " + e.getMessage());
            e.printStackTrace();
        }

        return inputs;
    }


    private static List<EntryIngredient> getOutputs(RecipeEntry<InfuserRecipe> recipe) {
        List<EntryIngredient> outputs = new ArrayList<>();

        try {
            if (recipe.value().getResult(null) != null) {
                outputs.add(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null))));
                System.out.println("Output added successfully");
            } else {
                System.out.println("Recipe result is null");
            }
        } catch (Exception e) {
            System.err.println("Error processing recipe outputs: " + e.getMessage());
            e.printStackTrace();
        }

        return outputs;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return InfuserCatagory.INFUSER;
    }
}