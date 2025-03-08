package net.mars_myth.creations.init;

import net.mars_myth.creations.CreationsBeyond;
import net.mars_myth.creations.recipe.DougherRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<DougherRecipe> DOUGHER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(CreationsBeyond.MOD_ID, "doughing"), new DougherRecipe.Serializer());
    public static final RecipeType<DougherRecipe> DOUGHER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(CreationsBeyond.MOD_ID, "doughing"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "doughing";
                }
            });

    public static void registerRecipes() {
        CreationsBeyond.LOGGER.info("Registering Custom Recipes for " + CreationsBeyond.MOD_ID);
    }
}
