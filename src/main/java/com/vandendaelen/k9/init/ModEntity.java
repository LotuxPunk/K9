package com.vandendaelen.k9.init;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.entity.EntityK9;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntity {

    public static void registerEntities(){
        registerEntity("K9",EntityK9.class,Reference.ENTITY_K9,40,16711680,6245729);
    }

    public static void registerEntity(String name, Class<? extends Entity> entity,int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID+":"+name),entity,name,id, K9.instance,range,1,true,color1,color2);
    }
}
