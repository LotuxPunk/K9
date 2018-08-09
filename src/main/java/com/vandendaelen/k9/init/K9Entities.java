package com.vandendaelen.k9.init;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.entities.EntityK9Ray;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class K9Entities {

    public static void registerEntities(){
        registerEntity("K9",EntityK9.class,Reference.ENTITY_K9,40,16711680,6245729);
        registerEntity("Ray",EntityK9Ray.class, Reference.ENTITY_K9_RAY,30);
    }

    public static void registerEntity(String name, Class<? extends Entity> entity,int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID+":"+name),entity,name,id, K9.instance,range,1,true,color1,color2);
    }

    public static void registerEntity(String name, Class<? extends Entity> entity,int id, int range){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID+":"+name),entity,name,id, K9.instance,range,1,true);
    }


}