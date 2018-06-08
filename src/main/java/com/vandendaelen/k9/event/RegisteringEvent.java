package com.vandendaelen.k9.event;

import com.vandendaelen.k9.init.ModBlocks;
import com.vandendaelen.k9.init.ModEntity;
import com.vandendaelen.k9.init.ModItems;
import com.vandendaelen.k9.utils.handlers.RenderHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringEvent {
    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> e){
        ModItems.init();
        e.getRegistry().registerAll(ModItems.getItems());
    }

    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> e){
        ModBlocks.init();
        e.getRegistry().registerAll(ModBlocks.getBlocks());
    }

    public static void preInitRegister(){
        ModEntity.registerEntities();

        RenderHandler.registerEntityRenders();
    }
}
