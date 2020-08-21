package com.vandendaelen.k9.entity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

/**
 * K9 - RebelT
 * Created using Tabula 7.1.0
 */
public class K9Model extends EntityModel<K9Entity> {
    public RendererModel base;
    public RendererModel body;
    public RendererModel keypad;
    public RendererModel neck;
    public RendererModel tail;
    public RendererModel tail2;
    public RendererModel joint;
    public RendererModel head;
    public RendererModel snout;
    public RendererModel scanner;
    public RendererModel earRight;
    public RendererModel earLeft;
    public RendererModel gun;
    public RendererModel scanner2;
    public RendererModel earRight2;
    public RendererModel earLeft2;

    public K9Model() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.keypad = new RendererModel(this, 0, 22);
        this.keypad.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.keypad.addBox(-2.0F, -9.0F, -3.0F, 4, 1, 5, 0.0F);
        this.tail2 = new RendererModel(this, 41, 0);
        this.tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail2.addBox(-0.5F, -0.5F, 2.0F, 1, 1, 6, 0.0F);
        this.base = new RendererModel(this, 0, 48);
        this.base.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.base.addBox(-5.0F, 0.0F, -7.0F, 10, 2, 13, 0.0F);
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-3.0F, -3.0F, -5.0F, 6, 6, 6, 0.0F);
        this.body = new RendererModel(this, 0, 28);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.5F, -7.9F, -6.5F, 9, 8, 12, 0.0F);
        this.scanner = new RendererModel(this, 0, 15);
        this.scanner.setRotationPoint(0.0F, -1.0F, -5.0F);
        this.scanner.addBox(-0.5F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
        this.setRotateAngle(scanner, -0.091106186954104F, 0.0F, 0.0F);
        this.neck = new RendererModel(this, 20, 12);
        this.neck.setRotationPoint(0.0F, -6.0F, -6.0F);
        this.neck.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 6, 0.0F);
        this.setRotateAngle(neck, -0.9599310885968813F, 0.0F, 0.0F);
        this.gun = new RendererModel(this, 0, 0);
        this.gun.setRotationPoint(0.0F, 0.6F, -5.0F);
        this.gun.addBox(-0.5F, -0.5F, -5.0F, 1, 1, 2, 0.0F);
        this.snout = new RendererModel(this, 24, 0);
        this.snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.snout.addBox(-1.5F, 0.0F, -8.0F, 3, 3, 3, 0.0F);
        this.earRight = new RendererModel(this, 0, 12);
        this.earRight.setRotationPoint(-2.0F, -3.0F, -4.0F);
        this.earRight.addBox(-1.5F, -3.0F, -0.5F, 3, 2, 1, 0.0F);
        this.setRotateAngle(earRight, 0.0F, 0.4553564018453205F, 0.0F);
        this.earLeft2 = new RendererModel(this, 0, 15);
        this.earLeft2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earLeft2.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.joint = new RendererModel(this, 0, 18);
        this.joint.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.joint.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(joint, 0.9599310885968813F, 0.0F, 0.0F);
        this.earRight2 = new RendererModel(this, 0, 15);
        this.earRight2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earRight2.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.tail = new RendererModel(this, 36, 0);
        this.tail.setRotationPoint(0.0F, -6.0F, 5.5F);
        this.tail.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(tail, 0.4553564018453205F, 0.0F, 0.0F);
        this.scanner2 = new RendererModel(this, 20, 0);
        this.scanner2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.scanner2.addBox(-1.0F, -1.0F, -6.01F, 2, 2, 0, 0.0F);
        this.earLeft = new RendererModel(this, 0, 12);
        this.earLeft.setRotationPoint(2.0F, -3.0F, -4.0F);
        this.earLeft.addBox(-1.5F, -3.0F, -0.5F, 3, 2, 1, 0.0F);
        this.setRotateAngle(earLeft, 0.0F, -0.36425021489121656F, 0.0F);
        this.base.addChild(this.keypad);
        this.tail.addChild(this.tail2);
        this.joint.addChild(this.head);
        this.base.addChild(this.body);
        this.head.addChild(this.scanner);
        this.base.addChild(this.neck);
        this.snout.addChild(this.gun);
        this.head.addChild(this.snout);
        this.head.addChild(this.earRight);
        this.earLeft.addChild(this.earLeft2);
        this.neck.addChild(this.joint);
        this.earRight.addChild(this.earRight2);
        this.body.addChild(this.tail);
        this.scanner.addChild(this.scanner2);
        this.head.addChild(this.earLeft);
    }

    @Override
    public void render(K9Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.base.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
