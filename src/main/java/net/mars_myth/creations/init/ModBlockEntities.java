package net.mars_myth.creations.init;

import net.mars_myth.creations.CreationsBeyond;
import net.mars_myth.creations.block.custom.entity.custom.DougherBlockEntity;
import net.mars_myth.creations.block.custom.entity.custom.InfuserBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModBlockEntities {


    public static final BlockEntityType<DougherBlockEntity> DOUGHER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(CreationsBeyond.MOD_ID, "dougher_be"),
                    BlockEntityType.Builder.create(DougherBlockEntity::new, ModBlocks.DOUGHER).build(null));

    public static final BlockEntityType<InfuserBlockEntity> INFUSER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(CreationsBeyond.MOD_ID, "infuser_be"),
                    BlockEntityType.Builder.create(InfuserBlockEntity::new, ModBlocks.INFUSER).build(null));

    public static void registerBlockEntities() {
        CreationsBeyond.LOGGER.info("Registering Block Entities for " + CreationsBeyond.MOD_ID);
    }
}