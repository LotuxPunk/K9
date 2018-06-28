package com.vandendaelen.k9.init;

import com.vandendaelen.k9.objects.blocks.BlockBase;
import com.vandendaelen.k9.objects.blocks.BlockOres;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid=Reference.MODID)
public class K9Blocks {

    public static List<Block> BLOCKS = new ArrayList<Block>();

    public static Block block_plaurium, ore_plaurium;

    public static void init() {
        block_plaurium = new BlockBase("block_plaurium",Material.IRON);
        ore_plaurium = new BlockOres("ore_plaurium",Material.ROCK);

        block_plaurium.setHardness(6.0F).setHarvestLevel("pickaxe", 2);
        ore_plaurium.setHardness(6.0F).setHarvestLevel("pickaxe", 3);

        BLOCKS.add(block_plaurium);
        BLOCKS.add(ore_plaurium);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for(Block block : BLOCKS){
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for(Block block : BLOCKS){
            ItemBlock itemBlock = new ItemBlock(block);
            event.getRegistry().registerAll(itemBlock.setRegistryName(block.getRegistryName()));
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for(Block block: BLOCKS){
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
    }
}
