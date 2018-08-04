package com.vandendaelen.k9.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.util.UUID;

public class Utils {

    public static EntityPlayer getPlayer (UUID id){
        return Minecraft.getMinecraft().world.getPlayerEntityByUUID(id);
    }

}
