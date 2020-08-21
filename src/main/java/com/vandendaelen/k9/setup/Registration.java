package com.vandendaelen.k9.setup;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.entity.K9Entity;
import com.vandendaelen.k9.item.K9EntityEggItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, K9.MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister(ForgeRegistries.ENTITIES, K9.MOD_ID);

    public static void init(){
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<K9EntityEggItem> K9_EGG = ITEMS.register("k9_egg", K9EntityEggItem::new);

    public static final RegistryObject<EntityType<K9Entity>> K9_ENTITY = ENTITIES.register("k9", () -> EntityType.Builder.create(K9Entity::new, EntityClassification.CREATURE)
            .size(1,1)
            .setShouldReceiveVelocityUpdates(false)
            .build("k9"));
}
