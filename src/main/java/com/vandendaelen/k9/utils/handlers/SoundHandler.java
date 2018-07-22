package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.utils.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundHandler {
    public static SoundEvent ENTITY_K9_AMBIENT, ENTITY_K9_HURT, ENTITY_K9_DEATH, ENTITY_K9_LASER_SHOOT;

    public static void registerSounds(){
        ENTITY_K9_AMBIENT = registerSound("k9_ambient");
        ENTITY_K9_HURT = registerSound("k9_hurt");
        ENTITY_K9_DEATH = registerSound("k9_death");
        ENTITY_K9_LASER_SHOOT = registerSound("k9_laser_shoot");
    }

    private static SoundEvent registerSound(String name){
        ResourceLocation location = new ResourceLocation(Reference.MODID,name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
