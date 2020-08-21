package com.vandendaelen.k9.entity.renderer;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.entity.K9Entity;
import com.vandendaelen.k9.entity.models.K9ModelMark2;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class K9EntityMark2Renderer extends MobRenderer<K9Entity, K9ModelMark2> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(K9.MOD_ID, "textures/entity/k92.png");

    public K9EntityMark2Renderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new K9ModelMark2(), 0.5f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(K9Entity entity) {
        return TEXTURE;
    }
}
