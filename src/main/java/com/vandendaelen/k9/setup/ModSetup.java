package com.vandendaelen.k9.setup;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {
    public static final ItemGroup ITEM_GROUP = new ItemGroup("k9") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Blocks.DIRT);
        }
    };
    public static void init(final FMLCommonSetupEvent event) {

    }
}
