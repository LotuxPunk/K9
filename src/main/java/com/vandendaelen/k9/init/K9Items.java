package com.vandendaelen.k9.init;

import com.vandendaelen.k9.objects.items.ItemBase;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

    public static Item ingot_plaurium;

    public static void init(){
        ingot_plaurium = new ItemBase("ingot_plaurium");

        ITEMS.add(ingot_plaurium);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item:ITEMS) {
            event.getRegistry().registerAll(item);
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (Item item:ITEMS){
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
        }
    }
}
