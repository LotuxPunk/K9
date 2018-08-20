package com.vandendaelen.k9.entities.render;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.entities.model.ModelK9;
import com.vandendaelen.k9.init.K9Items;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderK9 extends RenderLiving<EntityK9> {

    private final ResourceLocation TEXTURES_OLD = new ResourceLocation(Reference.MODID+":textures/entity/k9.png");

    private ModelBase modelK9 = new ModelK9();
    private Item item = K9Items.dummy_k9;

    public RenderK9(RenderManager manager) {
        super(manager, new ModelK9(), 0.5F);
        mainModel = modelK9;
    }

    @Override
    protected void applyRotations(EntityK9 entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

    @Override
    public void doRender(EntityK9 entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.isMarkII()){
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
        else {
            super.doRender(entity,x,y,z,entityYaw,partialTicks);
        }

    }


    private ItemStack getStackToRender() {
        return new ItemStack(this.item);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityK9 entity) {
        if (entity.isMarkII()){
            return TextureMap.LOCATION_BLOCKS_TEXTURE;
        }
        return TEXTURES_OLD;
    }
}
