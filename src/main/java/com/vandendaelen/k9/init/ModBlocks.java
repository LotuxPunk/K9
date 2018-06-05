package com.vandendaelen.k9.init;

import com.vandendaelen.k9.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModBlocks {
    public static Block dark_diamond_ore;

    private static Block[] blocks;

    public static void init(){
        dark_diamond_ore=new Block(Material.ROCK).setRegistryName("dark_diamond_ore").setUnlocalizedName("dark_diamond_ore").setHardness(7.0f).setResistance(20.0f);
        blocks = new Block[]{dark_diamond_ore};
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent e){
        for (Block b: blocks) {
            registerModel(b);

        }
    }

    public static void registerModel(Block block){
        ItemBlock ib = new ItemBlock(block);
        ib.setRegistryName(block.getRegistryName());
        GameRegistry.findRegistry(Item.class).register(ib);

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),0, new ModelResourceLocation(new ResourceLocation(Reference.MODID,block.getUnlocalizedName().substring(5)),"inventory"));
    }

    public static Block[] getBlocks() {
        return blocks;
    }
}
