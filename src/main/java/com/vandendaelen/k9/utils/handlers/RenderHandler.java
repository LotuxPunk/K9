package com.vandendaelen.k9.utils.handlers;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.entities.EntityK9Ray;
import com.vandendaelen.k9.entities.render.RenderK9New;
import com.vandendaelen.k9.entities.render.RenderK9Ray;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(EntityK9.class, RenderK9New::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityK9Ray.class, RenderK9Ray::new);
    }
}
