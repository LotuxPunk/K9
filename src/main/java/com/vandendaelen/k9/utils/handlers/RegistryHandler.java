package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.init.K9Entity;
import com.vandendaelen.k9.world.gen.WorldGenCustomOres;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

    public static void otherRegisteries(){
        //GameRegistry.registerWorldGenerator(new WorldGenCustomOres(),0);
    }

    public static void preInitRegistries(){
        K9Entity.registerEntities();
    }

    public static void initRegisteries(){
        SoundHandler.registerSounds();
    }
}
