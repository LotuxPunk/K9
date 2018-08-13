package com.vandendaelen.k9.init;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.objects.items.ItemBase;
import com.vandendaelen.k9.objects.items.ItemK9Remote;
import com.vandendaelen.k9.objects.items.ItemK9Spawner;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid=Reference.MODID)
public class K9Items {

    public static List<Item> ITEMS = new ArrayList<Item>();

    public static Item ingot_plaurium, spawner_k9, remote_k9;

    public static void init(){
        ingot_plaurium = new ItemBase("ingot_plaurium");
        spawner_k9 = new ItemK9Spawner<>("spawner_k9",EntityK9::new);
        remote_k9 = new ItemK9Remote("k9_remote");

        ITEMS.add(ingot_plaurium);
        ITEMS.add(spawner_k9);
        ITEMS.add(remote_k9);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item:ITEMS) {
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (Item item:ITEMS){
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
        }
    }
}
