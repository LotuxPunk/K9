package com.vandendaelen.k9.event;

import com.vandendaelen.k9.init.ModBlocks;
import com.vandendaelen.k9.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.rmi.registry.Registry;

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
}