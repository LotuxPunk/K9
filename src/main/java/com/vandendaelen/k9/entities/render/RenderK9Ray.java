package com.vandendaelen.k9.entities.render;

import com.vandendaelen.k9.entities.model.ModelRay;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderK9Ray extends Render {
    Minecraft mc;
    public ModelRay model = new ModelRay();
    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/ray.png");

    public RenderK9Ray() {
        super(Minecraft.getMinecraft().getRenderManager());
        mc = Minecraft.getMinecraft();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y + 0.1, z);
        GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
        GlStateManager.rotate(-entity.rotationPitch, 1, 0, 0);
        mc.getTextureManager().bindTexture(TEXTURE);
        GlStateManager.disableFog();
        GlStateManager.disableLighting();
        mc.entityRenderer.disableLightmap();
        model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
        mc.entityRenderer.enableLightmap();
        GlStateManager.enableFog();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
}
