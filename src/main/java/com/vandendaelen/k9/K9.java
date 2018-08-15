package com.vandendaelen.k9;

import com.vandendaelen.k9.init.K9Blocks;
import com.vandendaelen.k9.init.K9Entities;
import com.vandendaelen.k9.init.K9Items;
import com.vandendaelen.k9.init.K9Recipes;
import com.vandendaelen.k9.packets.MessageK9Piloting;
import com.vandendaelen.k9.packets.MessageK9Teleport;
import com.vandendaelen.k9.packets.MessageRemoteOpenGUI;
import com.vandendaelen.k9.proxy.GuiProxy;
import com.vandendaelen.k9.proxy.IProxy;
import com.vandendaelen.k9.tabs.K9Tab;
import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.handlers.SoundHandler;
import com.vandendaelen.k9.world.gen.OreGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;


@Mod(modid = Reference.MODID, name =Reference.NAME , version =Reference.VERSION, dependencies = Reference.DEPENDENCIES, updateJSON = Reference.UPDATE_JSON)
public class K9 {
    @Instance(Reference.MODID)
    public static K9 instance;
    public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    public static final CreativeTabs k9Tab = new K9Tab("k9tab");
    public static Logger logger;

    @SidedProxy(modId =Reference.MODID,clientSide = Reference.CLIENT_PROXY,serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e){
        logger = e.getModLog();
        logger.info("preInit :");

        K9Entities.registerEntities();

        K9Items.init();
        K9Blocks.init();

        NETWORK.registerMessage(MessageK9Piloting.Handler.class,MessageK9Piloting.class,1,Side.SERVER);
        NETWORK.registerMessage(MessageK9Teleport.Handler.class,MessageK9Teleport.class,1,Side.SERVER);
        NETWORK.registerMessage(MessageRemoteOpenGUI.Handler.class,MessageRemoteOpenGUI.class,3,Side.CLIENT);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());

        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e){
        logger.info("init :");

        proxy.init(e);

        K9Recipes.init();

        SoundHandler.registerSounds();

        GameRegistry.registerWorldGenerator(new OreGen(), 0);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){
        logger.info("postInit :");
    }
}
