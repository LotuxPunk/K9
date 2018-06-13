package com.vandendaelen.k9.objects.blocks;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.init.BlockInit;
import com.vandendaelen.k9.init.ItemInit;
import com.vandendaelen.k9.utils.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(K9.k9Tab);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

    }

    @Override
    public void registerModels() {
        K9.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0,"inventory");
    }
}
