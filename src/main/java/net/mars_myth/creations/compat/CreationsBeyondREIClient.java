package net.mars_myth.creations.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.mars_myth.creations.init.ModBlocks;
import net.mars_myth.creations.init.ModRecipes;
import net.mars_myth.creations.recipe.DougherRecipe;
import net.mars_myth.creations.recipe.InfuserRecipe;
import net.mars_myth.creations.screen.custom.DougherScreen;
import net.mars_myth.creations.screen.custom.InfuserScreen;


public class CreationsBeyondREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new DougherCatagory());

        registry.addWorkstations(DougherCatagory.DOUGHER, EntryStacks.of(ModBlocks.DOUGHER));

        registry.add(new InfuserCatagory());

        registry.addWorkstations(InfuserCatagory.INFUSER, EntryStacks.of(ModBlocks.INFUSER));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(DougherRecipe.class, ModRecipes.DOUGHER_TYPE,
                DougherDisplay::new);

        registry.registerRecipeFiller(InfuserRecipe.class, ModRecipes.INFUSER_TYPE,
                InfuserDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25),
                DougherScreen.class, DougherCatagory.DOUGHER);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25),
                InfuserScreen.class, InfuserCatagory.INFUSER);
    }
}