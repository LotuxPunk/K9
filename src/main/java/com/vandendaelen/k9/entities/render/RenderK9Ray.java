package com.vandendaelen.k9.entities.render;

import com.vandendaelen.k9.entities.EntityK9Ray;
import com.vandendaelen.k9.entities.model.ModelRay;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderK9Ray extends Render<EntityK9Ray> {

    Minecraft mc;
    ModelRay model = new ModelRay();
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID+":textures/entity/ray.png");

    public RenderK9Ray(RenderManager manager) {
        super(manager);
        mc = Minecraft.getMinecraft();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityK9Ray entity) {
        return TEXTURES;
    }

    @Override
    public void doRender(EntityK9Ray entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        mc.getTextureManager().bindTexture(TEXTURES);
        model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
    }
}
