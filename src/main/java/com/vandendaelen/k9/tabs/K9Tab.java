package com.vandendaelen.k9.tabs;

import com.vandendaelen.k9.init.K9Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class K9Tab extends CreativeTabs {

    public K9Tab(String label){
        super("k9tab");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(K9Items.ingot_plaurium);
    }
}
