package com.vandendaelen.k9;

import com.vandendaelen.k9.proxy.ClientProxy;
import com.vandendaelen.k9.proxy.CommonProxy;
import com.vandendaelen.k9.tabs.K9Tab;
import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name =Reference.NAME , version =Reference.VERSION )
public class K9 {
    @Instance(Reference.MODID)
    public static K9 instance;

    public static final CreativeTabs k9Tab = new K9Tab("k9tab");

    @SidedProxy(clientSide = Reference.CLIENT_PROXY,serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e){
        RegistryHandler.preInitRegistries();
        RegistryHandler.otherRegisteries();
    }

    @EventHandler
    public void init(FMLInitializationEvent e){
        RegistryHandler.initRegisteries();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){}
}
