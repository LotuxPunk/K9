package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.init.BlockInit;
import com.vandendaelen.k9.init.ItemInit;
import com.vandendaelen.k9.init.ModEntity;
import com.vandendaelen.k9.utils.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> e){
        e.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> e){
        e.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public void onModelRegister(ModelRegistryEvent e) {
        for (Item item:ItemInit.ITEMS) {
            if (item instanceof IHasModel){
                ((IHasModel)item).registerModels();
            }
        }

        for (Block block : BlockInit.BLOCKS){
            if (block instanceof IHasModel){
                ((IHasModel)block).registerModels();
            }
        }
    }

    public static void preInitRegistries(){
        ModEntity.registerEntities();

        RenderHandler.registerEntityRenders();
    }

    public static void initRegisteries(){
        SoundHandler.registerSounds();
    }
}
