package com.vandendaelen.k9.init;

import com.vandendaelen.k9.utils.Reference;
import jline.internal.Nullable;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModItems {

    public static Item diamond_stick;

    private static Item[] items;

    public static void init(){
        diamond_stick=new Item().setRegistryName("diamond_stick").setUnlocalizedName("diamond_stick");

        items=new Item[]{diamond_stick};
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent e){
        for (Item item : items){
            registerModel(item);
        }
    }

    private static void registerModel(Item item){
        ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(new ResourceLocation(Reference.MODID,item.getUnlocalizedName().substring(5)),"inventory"));
    }

    public static Item[] getItems(){
        return items;
    }
}
