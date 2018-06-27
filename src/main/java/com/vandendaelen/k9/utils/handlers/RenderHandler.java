package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.entities.EntityK9Ray;
import com.vandendaelen.k9.entities.render.RenderK9;
import com.vandendaelen.k9.entities.render.RenderK9Ray;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler {

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityK9.class, new IRenderFactory<EntityK9>() {
            @Override
            public Render<? super EntityK9> createRenderFor(RenderManager manager) {
                return new RenderK9(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityK9Ray.class, new IRenderFactory<EntityK9Ray>() {
            @Override
            public Render<? super EntityK9Ray> createRenderFor(RenderManager manager) {
                return new RenderK9Ray(manager);
            }
        });

        //RenderingRegistry.registerEntityRenderingHandler(EntityK9Ray.class, new RenderK9Ray());


    }
}
