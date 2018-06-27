package com.vandendaelen.k9.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class K9Recipes {

    public static void init() {
        GameRegistry.addSmelting(K9Blocks.ore_plaurium, new ItemStack(K9Items.ingot_plaurium, 1), 0.2f);
    }
}
