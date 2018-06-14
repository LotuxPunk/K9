package com.vandendaelen.k9.objects.items;


import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.init.ItemInit;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(K9.k9Tab);

        ItemInit.ITEMS.add(this);
    }
}
