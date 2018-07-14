package com.vandendaelen.k9.proxy;

import com.vandendaelen.k9.objects.tilesentities.K9ContainerTileEntity;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.vandendaelen.k9.K9.instance;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){}

    public void init(FMLInitializationEvent e){
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
    }

    public void registerItemRenderer(Item item, int meta, String id){}

    public void registerVariantRenderer(Item item, int meta, String filename, String id){}

    public void Register(Object o) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        GameRegistry.registerTileEntity(K9ContainerTileEntity.class, Reference.MODID + "_k9containerblock");
    }
}
