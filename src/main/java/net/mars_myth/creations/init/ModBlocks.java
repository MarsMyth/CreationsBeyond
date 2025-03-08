package net.mars_myth.creations.init;

import net.mars_myth.creations.CreationsBeyond;
import net.mars_myth.creations.block.connections.FoundationBlocks;
import net.mars_myth.creations.block.custom.DougherBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {


    public static final Block INFUSED_FOUNDATION = registerBlock("infused_foundation",
            new FoundationBlocks(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));

    public static final Block DOUGHER = registerBlock("dougher",
            new DougherBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CreationsBeyond.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CreationsBeyond.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        CreationsBeyond.LOGGER.info("Registering ModBlocks for " + CreationsBeyond.MOD_ID);
    }
}
