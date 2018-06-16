package com.vandendaelen.k9.init;

import com.vandendaelen.k9.objects.blocks.BlockBase;
import com.vandendaelen.k9.objects.blocks.BlockOres;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //Blocks
    public static final Block BLOCK_PLAURIUM = new BlockBase("block_plaurium", Material.IRON).setHardness(5F);;

    //Ores
    //public static final Block ORE_PLAURIUM = new BlockOres("ore_overworld_plaurium","overworld");
    public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld").setHardness(50F);
}
