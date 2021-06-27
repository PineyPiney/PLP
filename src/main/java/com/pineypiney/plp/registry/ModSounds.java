package com.pineypiney.plp.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static final Identifier NANI_ID = new Identifier("plp:nani");
    public static SoundEvent NANI_EVENT = new SoundEvent(NANI_ID);

    public static void registerSounds(){
        Registry.register(Registry.SOUND_EVENT, NANI_ID, NANI_EVENT);

        System.out.println("Registered Mod Sounds");
    }
}
