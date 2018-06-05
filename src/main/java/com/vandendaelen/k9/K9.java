package com.vandendaelen.k9;

import com.vandendaelen.k9.event.RegisteringEvent;
import com.vandendaelen.k9.proxy.CommonProxy;
import com.vandendaelen.k9.utils.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = Reference.MODID, name =Reference.NAME , version =Reference.VERSION )
public class K9 {
    @Mod.Instance(Reference.MODID)
    public static K9 instance;

    public K9() {
        MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
    }

    @SidedProxy(clientSide = Reference.CLIENT_PROXY,serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(){
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(){
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(){
        proxy.postInit();
    }
}
