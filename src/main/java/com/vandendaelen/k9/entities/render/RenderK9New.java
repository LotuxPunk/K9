package com.vandendaelen.k9.entities.render;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.entities.model.ModelK9;
import com.vandendaelen.k9.init.K9Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderK9New extends RenderLiving<EntityK9> {

    private Item item = K9Items.dummy_k9;

    public RenderK9New(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelK9(), 0.5F);
    }

    @Override
    public void doRender(EntityK9 entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y + 0.4, z);
        float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        float f8 = this.handleRotationFloat(entity, partialTicks);
        float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
        this.applyRotations(entity, f8, f, partialTicks);
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getMinecraft().getRenderItem().renderItem(getStackToRender(), ItemCameraTransforms.TransformType.GROUND);
        GlStateManager.popMatrix();
    }


    private ItemStack getStackToRender() {
        return new ItemStack(this.item);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityK9 entity) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
