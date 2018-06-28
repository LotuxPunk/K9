package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.init.K9Entity;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegistryHandler {

    public static void otherRegisteries(){}

    public static void preInitRegistries(){
        K9Entity.registerEntities();
    }

    public static void initRegisteries(){
        SoundHandler.registerSounds();
    }
}
