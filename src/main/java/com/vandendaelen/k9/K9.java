package com.vandendaelen.k9;

import com.vandendaelen.k9.init.K9Blocks;
import com.vandendaelen.k9.init.K9Items;
import com.vandendaelen.k9.init.K9Recipes;
import com.vandendaelen.k9.proxy.CommonProxy;
import com.vandendaelen.k9.tabs.K9Tab;
import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.handlers.RegistryHandler;
import com.vandendaelen.k9.world.gen.OreGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name =Reference.NAME , version =Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class K9 {
    @Instance(Reference.MODID)
    public static K9 instance;

    public static final CreativeTabs k9Tab = new K9Tab("k9tab");

    @SidedProxy(clientSide = Reference.CLIENT_PROXY,serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e){
        System.out.println(Reference.MODID + " : preInit");

        RegistryHandler.preInitRegistries();
        RegistryHandler.otherRegisteries();

        K9Items.init();
        K9Blocks.init();

        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e){
        System.out.println(Reference.MODID + " : init");
        RegistryHandler.initRegisteries();

        K9Recipes.init();

        GameRegistry.registerWorldGenerator(new OreGen(), 0);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){
        System.out.println(Reference.MODID + " : postInit");
    }
}
