package com.vandendaelen.k9.entity.render;

import com.vandendaelen.k9.entity.EntityK9;
import com.vandendaelen.k9.entity.model.ModelK9;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderK9 extends RenderLiving<EntityK9> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID+":textures/entity/k9.png");
    public RenderK9(RenderManager manager) {
        super(manager, new ModelK9(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityK9 entity) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityK9 entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
