package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.init.BlockInit;
import com.vandendaelen.k9.init.ItemInit;
import com.vandendaelen.k9.init.ModEntity;
import com.vandendaelen.k9.utils.interfaces.IHasModel;
import com.vandendaelen.k9.world.gen.WorldGenCustomOres;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent e) {
        for (Item item:ItemInit.ITEMS) {
            if (item instanceof IHasModel){
                ((IHasModel)item).registerModels();
            }
            else{
                K9.proxy.Register(item);
            }
        }

        for (Block block : BlockInit.BLOCKS){
            if (block instanceof IHasModel){
                ((IHasModel)block).registerModels();
            }
            else{
                K9.proxy.Register(block);
            }
        }
    }

    public static void otherRegisteries(){
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(),0);
    }

    public static void preInitRegistries(){
        ModEntity.registerEntities();
    }

    public static void initRegisteries(){
        SoundHandler.registerSounds();
    }
}
