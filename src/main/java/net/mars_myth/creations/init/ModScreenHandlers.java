package net.mars_myth.creations.init;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.mars_myth.creations.CreationsBeyond;
import net.mars_myth.creations.screen.custom.DougherScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {




    public static final ScreenHandlerType<DougherScreenHandler> DOUGHER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CreationsBeyond.MOD_ID, "dougher_screen_handler"),
                    new ExtendedScreenHandlerType<>(DougherScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        CreationsBeyond.LOGGER.info("Registering Screen Handlers for " + CreationsBeyond.MOD_ID);
    }
}
