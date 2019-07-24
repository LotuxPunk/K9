package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.entities.EntityLaserRay;
import com.vandendaelen.k9.entities.render.RenderK9;
import com.vandendaelen.k9.entities.render.RenderLaserRay;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityK9.class, RenderK9::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserRay.class, RenderLaserRay::new);
    }
}
