package com.pineypiney.plp;

import com.pineypiney.plp.registry.ModCommands;
import net.fabricmc.api.ModInitializer;

public class PLP implements ModInitializer {
    @Override
    public void onInitialize() {
        ModCommands.registerCommands();
    }
}
