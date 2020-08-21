package com.vandendaelen.k9.entity;

import com.vandendaelen.k9.K9;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class K9EntityRenderer extends MobRenderer<K9Entity, K9Model> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(K9.MOD_ID, "textures/entity/k9.png");

    public K9EntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new K9Model(), 0.5f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(K9Entity entity) {
        return TEXTURE;
    }
}
