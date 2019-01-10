package com.vandendaelen.k9.init;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.entities.EntityLaserRay;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class K9Entities {
    public static int id = 0;

    public static void registerEntities(){
        registerEntity("K9",EntityK9.class,40,16711680,6245729);
        registerProjectiles(EntityLaserRay.class,"k9_ray");
    }

    public static void registerEntity(String name, Class<? extends Entity> entity, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID+":"+name),entity,name,++id, K9.instance,range,1,true,color1,color2);
    }

    public static void registerProjectiles(Class<? extends EntityThrowable> entityClass, String name) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, name), entityClass, name, ++id, K9.instance, 64, 5, true);
    }
}
