package com.vandendaelen.k9.proxy;

import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.handlers.RenderHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        RenderHandler.registerEntityRenders();
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID,filename), id));
    }

    @Override
    public void Register(Object o) {
        if(o instanceof Block) {
            Block toRegister = (Block) o;
            String name = toRegister.getRegistryName().toString();
            registerItemRenderer(Item.getItemFromBlock(toRegister), 0, name);
        } else if (o instanceof Item) {
            Item toRegister = (Item) o;
            String name = toRegister.getRegistryName().toString();
            registerItemRenderer(toRegister, 0, name);
        } else {
            String s = o.toString();
            Throwable t = new Throwable(s);
            IllegalArgumentException exeption = new IllegalArgumentException("Please give a registerable object as an Argument!!", t);
        }
    }
}
