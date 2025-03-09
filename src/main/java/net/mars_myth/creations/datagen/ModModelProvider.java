package net.mars_myth.creations.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.mars_myth.creations.init.ModBlocks;
import net.mars_myth.creations.init.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.INFUSED_FOUNDATION);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DOUGHER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.INFUSER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SIMPLE_ALLOY, Models.GENERATED);
        itemModelGenerator.register(ModItems.DOUGH, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHEAT_FLOUR, Models.GENERATED);

    }
}
