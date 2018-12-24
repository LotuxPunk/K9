package com.vandendaelen.k9.entities.model;

import com.vandendaelen.k9.entities.render.ModelRendererGlow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelK9 - VampireRedEye
 * Created using Tabula 7.0.0
 */
public class ModelK9 extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer keypad;
    public ModelRenderer keypad2;
    public ModelRenderer collar;
    public ModelRenderer neck;
    public ModelRenderer snout;
    public ModelRenderer eyes;
    public ModelRenderer earl;
    public ModelRenderer earr;

    public ModelK9() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.collar = new ModelRenderer(this, 52, 0);
        this.collar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.collar.addBox(-2.0F, -6.5F, -6.8F, 4, 4, 1, 0.0F);
        this.neck = new ModelRenderer(this, 38, 0);
        this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neck.addBox(-1.0F, 0.5F, -11.3F, 2, 2, 4, 0.0F);
        this.setRotateAngle(neck, -0.8651597102135892F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 0, 24);
        this.snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.snout.addBox(-1.5F, -1.0F, -6.0F, 3, 2, 4, 0.0F);
		this.eyes = new ModelRendererGlow(this, 0, 20);
        this.eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.eyes.addBox(-1.5F, -1.5F, -4.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(eyes, -0.3490658503988659F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 24, 34);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, -3.0F, -6.0F, 8, 3, 12, 0.0F);
        this.earl = new ModelRenderer(this, 0, 0);
        this.earl.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earl.addBox(1.0F, -5.0F, -1.0F, 2, 2, 1, 0.0F);
        this.tail = new ModelRenderer(this, 0, 0);
        this.tail.setRotationPoint(0.0F, 18.0F, 4.0F);
        this.tail.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 8, 0.0F);
        this.body2 = new ModelRenderer(this, 24, 19);
        this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body2.addBox(-3.5F, -6.0F, -6.0F, 7, 3, 11, 0.0F);
        this.base = new ModelRenderer(this, 16, 49);
        this.base.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.base.addBox(-5.0F, 0.0F, -7.0F, 10, 1, 14, 0.0F);
        this.head = new ModelRenderer(this, 0, 10);
        this.head.setRotationPoint(0.0F, 16.0F, -8.0F);
        this.head.addBox(-2.0F, -3.0F, -3.0F, 4, 4, 4, 0.0F);
        this.keypad2 = new ModelRenderer(this, 27, 0);
        this.keypad2.setRotationPoint(0.0F, 0.0F, 1.9F);
        this.keypad2.addBox(-1.5F, -7.5F, -3.5F, 3, 1, 2, 0.0F);
        this.earr = new ModelRenderer(this, 0, 0);
        this.earr.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earr.addBox(-3.0F, -5.0F, -1.0F, 2, 2, 1, 0.0F);
        this.body3 = new ModelRenderer(this, 26, 8);
        this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body3.addBox(-3.0F, -7.0F, -6.0F, 6, 1, 10, 0.0F);
        this.keypad = new ModelRenderer(this, 16, 0);
        this.keypad.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.keypad.addBox(-1.5F, -8.0F, -3.5F, 3, 1, 2, 0.0F);
        this.base.addChild(this.collar);
        this.base.addChild(this.neck);
        this.head.addChild(this.snout);
        this.head.addChild(this.eyes);
        this.base.addChild(this.body);
        this.head.addChild(this.earl);
        this.base.addChild(this.body2);
        this.base.addChild(this.keypad2);
        this.head.addChild(this.earr);
        this.base.addChild(this.body3);
        this.base.addChild(this.keypad);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.tail.render(f5);
        this.base.render(f5);
	
		GlStateManager.pushMatrix();
		float rot = (float) Math.toDegrees(f4 / (180F / (float) Math.PI)) / 1.5F;
		GlStateManager.rotate(rot, 1, 0, 0);
        this.head.render(f5);
		GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
