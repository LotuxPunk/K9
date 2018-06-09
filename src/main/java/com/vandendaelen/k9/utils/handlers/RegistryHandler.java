package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.init.ModEntity;

public class RegistryHandler {
//    @SubscribeEvent
//    public void onItemRegister(RegistryEvent.Register<Item> e){
//
//    }
//
//    @SubscribeEvent
//    public void onBlockRegister(RegistryEvent.Register<Block> e){
//
//    }

    public static void preInitRegistries(){
        ModEntity.registerEntities();

        RenderHandler.registerEntityRenders();
    }

    public static void initRegisteries(){
        SoundHandler.registerSounds();
    }
}
