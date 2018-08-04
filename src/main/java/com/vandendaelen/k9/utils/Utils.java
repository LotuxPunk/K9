package com.vandendaelen.k9.utils;

import com.vandendaelen.k9.entities.EntityK9;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.UUID;

public class Utils {

    public static EntityPlayer getPlayer (UUID id){
        return Minecraft.getMinecraft().world.getPlayerEntityByUUID(id);
    }

    public static EntityK9 isAClickOnK9(EntityPlayer player, BlockPos pos){
        List<Entity> entities = Minecraft.getMinecraft().world.getLoadedEntityList();
        for (Entity entity : entities) {
            if (entity.getPosition() == pos && entity instanceof EntityK9)
                return (EntityK9)entity;
        }
        return null;
    }

}
