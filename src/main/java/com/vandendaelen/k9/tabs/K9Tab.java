package com.vandendaelen.k9.tabs;

import com.vandendaelen.k9.init.BlockInit;
import com.vandendaelen.k9.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class K9Tab extends CreativeTabs {

    public K9Tab(String label){
        super("k9tab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(BlockInit.BLOCK_PLAURIUM));
    }
}
