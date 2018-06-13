package com.vandendaelen.k9.init;

import com.vandendaelen.k9.objects.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block BLOCK_PLAURIUM = new BlockBase("block_plaurium", Material.IRON);
}
