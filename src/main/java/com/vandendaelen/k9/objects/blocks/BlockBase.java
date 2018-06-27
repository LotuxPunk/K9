package com.vandendaelen.k9.objects.blocks;

import com.vandendaelen.k9.K9;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(K9.k9Tab);
    }
}
