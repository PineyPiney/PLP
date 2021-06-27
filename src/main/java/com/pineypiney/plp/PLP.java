package com.pineypiney.plp;

import com.pineypiney.plp.registry.ModCommands;
import com.pineypiney.plp.registry.ModSounds;
import net.fabricmc.api.ModInitializer;

public class PLP implements ModInitializer {
    @Override
    public void onInitialize() {
        ModCommands.registerCommands();
        ModSounds.registerSounds();
    }
}
