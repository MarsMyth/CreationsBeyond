package net.mars_myth.creations;

import net.fabricmc.api.ModInitializer;

import net.mars_myth.creations.init.ModBlockEntities;
import net.mars_myth.creations.init.*;
import net.mars_myth.creations.init.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreationsBeyond implements ModInitializer {
	public static final String MOD_ID = "creations";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");


		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();


	}
}