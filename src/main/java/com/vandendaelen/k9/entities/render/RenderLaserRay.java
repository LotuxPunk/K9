package com.vandendaelen.k9.entities.render;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.entities.EntityLaserRay;
import com.vandendaelen.k9.utils.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RenderLaserRay extends Render<EntityLaserRay> {

    public RenderLaserRay(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRenderShadowAndFire(Entity entityIn, double x, double y, double z, float yaw, float partialTicks) {

    }

    @Override
    public void doRender(EntityLaserRay entity, double x, double y, double z, float entityYaw, float partialTicks) {
        Utils.setupRenderLightning();
        GlStateManager.translate(x, y, z);

        Entity thrower = entity.getEntityWorld().getEntityByID(entity.getThrowerID());
        if(thrower != null && thrower instanceof EntityK9) {
            Vec3d vec1 = new Vec3d(thrower.getLookVec().x, thrower.getEyeHeight(), thrower.getLookVec().z);
            Vec3d vec2 = thrower.getPositionVector().subtract(entity.getPositionVector());
            Utils.drawGlowingLine(vec1, vec2, 0.5F, entity.color);
        }

        Utils.finishRenderLightning();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLaserRay entity) {
        return null;
    }

}
