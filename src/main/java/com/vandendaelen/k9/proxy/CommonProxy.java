package com.vandendaelen.k9.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){}

    public void registerItemRenderer(Item item, int meta, String id){}

    public void registerVariantRenderer(Item item, int meta, String filename, String id){}

    public void Register(Object o) {}
}
