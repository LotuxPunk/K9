package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.entity.EntityK9;
import com.vandendaelen.k9.entity.render.RenderK9;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityK9.class, new IRenderFactory<EntityK9>() {
            @Override
            public Render<? super EntityK9> createRenderFor(RenderManager manager) {
                return new RenderK9(manager);
            }
        });
    }
}
