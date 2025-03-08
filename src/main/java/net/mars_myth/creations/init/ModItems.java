package net.mars_myth.creations.init;

import net.mars_myth.creations.CreationsBeyond;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SIMPLE_ALLOY = registerItem("simple_alloy",
            new Item(new Item.Settings()));

    public static final Item WHEAT_FLOUR = registerItem("wheat_flour",
            new Item(new Item.Settings()));
    public static final Item DOUGH = registerItem("dough",
            new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CreationsBeyond.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CreationsBeyond.LOGGER.info("Registering Mod Items for " + CreationsBeyond.MOD_ID);
    }
}
