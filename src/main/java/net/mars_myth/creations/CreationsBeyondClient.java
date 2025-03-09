package net.mars_myth.creations;

import net.fabricmc.api.ClientModInitializer;
import net.mars_myth.creations.init.ModScreenHandlers;
import net.mars_myth.creations.screen.custom.DougherScreen;
import net.mars_myth.creations.screen.custom.InfuserScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CreationsBeyondClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.DOUGHER_SCREEN_HANDLER, DougherScreen::new);
        HandledScreens.register(ModScreenHandlers.INFUSER_SCREEN_HANDLER, InfuserScreen::new);
    }
}
