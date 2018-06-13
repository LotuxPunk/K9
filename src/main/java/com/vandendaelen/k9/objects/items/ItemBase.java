package com.vandendaelen.k9.objects.items;


import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.init.ItemInit;
import com.vandendaelen.k9.utils.interfaces.IHasModel;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(K9.k9Tab);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        K9.proxy.registerItemRenderer(this, 0,"inventory");
    }
}
