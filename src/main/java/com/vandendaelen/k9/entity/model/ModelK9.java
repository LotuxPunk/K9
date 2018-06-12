package com.vandendaelen.k9.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelWolf - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelK9 extends ModelBase {
    public ModelRenderer k9_tail;
    public ModelRenderer k9_noze;
    public ModelRenderer k9_head;
    public ModelRenderer ear_right;
    public ModelRenderer ear_left;
    public ModelRenderer K9_body;
    public ModelRenderer k9_low_body;

    public ModelK9() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.k9_head = new ModelRenderer(this, 0, 0);
        this.k9_head.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.k9_head.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.k9_low_body = new ModelRenderer(this, 0, 0);
        this.k9_low_body.mirror = true;
        this.k9_low_body.setRotationPoint(-4.0F, 17.0F, -4.9F);
        this.k9_low_body.addBox(0.0F, 0.0F, 0.0F, 8, 7, 13, 0.0F);
        this.k9_tail = new ModelRenderer(this, 9, 18);
        this.k9_tail.setRotationPoint(-1.0F, 13.4F, 8.0F);
        this.k9_tail.addBox(0.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.K9_body = new ModelRenderer(this, 0, 0);
        this.K9_body.setRotationPoint(0.0F, 14.1F, -3.0F);
        this.K9_body.addBox(-3.0F, -2.0F, -3.0F, 6, 12, 4, 0.0F);
        this.setRotateAngle(K9_body, 1.5707963267948966F, 0.0F, 0.017453292519943295F);
        this.ear_right = new ModelRenderer(this, 16, 14);
        this.ear_right.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.ear_right.addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.ear_left = new ModelRenderer(this, 16, 14);
        this.ear_left.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.ear_left.addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.k9_noze = new ModelRenderer(this, 0, 10);
        this.k9_noze.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.k9_noze.addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.k9_head.render(f5);
        this.k9_low_body.render(f5);
        this.k9_tail.render(f5);
        this.K9_body.render(f5);
        this.ear_right.render(f5);
        this.ear_left.render(f5);
        this.k9_noze.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn){
        this.k9_head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.k9_head.rotateAngleX = headPitch * 0.017453292F;
        this.k9_noze.rotateAngleY = netHeadYaw * 0.017453292F;
        this.k9_noze.rotateAngleX = headPitch * 0.017453292F;
        this.ear_left.rotateAngleY = netHeadYaw * 0.017453292F;
        this.ear_left.rotateAngleX = headPitch * 0.017453292F;
        this.ear_right.rotateAngleY = netHeadYaw * 0.017453292F;
        this.ear_right.rotateAngleX = headPitch * 0.017453292F;
    }
}
