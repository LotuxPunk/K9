package com.vandendaelen.k9.entity.models;

import com.vandendaelen.k9.entity.K9Entity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

// Made by Asc4Yin

public class K9ModelMark2 extends EntityModel<K9Entity> {
    private final RendererModel neckandhead;
    private final RendererModel jaw;
    private final RendererModel head;
    private final RendererModel body;
    private final RendererModel leftflap;
    private final RendererModel barleftfront;
    private final RendererModel barleftback;
    private final RendererModel rightflap;
    private final RendererModel barrightfront;
    private final RendererModel barrightback;
    private final RendererModel frontflap;
    private final RendererModel backflap;
    private final RendererModel tail;

    public K9ModelMark2() {
        textureWidth = 64;
        textureHeight = 64;

        neckandhead = new RendererModel(this);
        neckandhead.setRotationPoint(0.0F, 17.0F, -3.0F);
        setRotationAngle(neckandhead, -0.5236F, 0.0F, 0.0F);
        neckandhead.cubeList.add(new ModelBox(neckandhead, 0, 0, -1.0F, -1.0F, -4.0F, 2, 2, 4, 0.0F, false));
        neckandhead.cubeList.add(new ModelBox(neckandhead, 54, 6, -1.0F, -0.7F, -1.5F, 2, 2, 1, 0.4F, false));

        jaw = new RendererModel(this);
        jaw.setRotationPoint(0.0F, -0.85F, -4.0F);
        neckandhead.addChild(jaw);
        setRotationAngle(jaw, 0.6981F, 0.0F, 0.0F);
        jaw.cubeList.add(new ModelBox(jaw, 22, 34, -3.0F, -0.01F, -3.5F, 6, 2, 4, 0.001F, false));
        jaw.cubeList.add(new ModelBox(jaw, 0, 6, -1.5F, -0.01F, -6.42F, 3, 2, 3, 0.0F, false));

        head = new RendererModel(this);
        head.setRotationPoint(0.0F, 0.0F, -3.5F);
        jaw.addChild(head);
        setRotationAngle(head, -0.1745F, 0.0F, 0.0F);
        head.cubeList.add(new ModelBox(head, 30, 23, -3.0F, -3.0F, 0.0F, 6, 3, 4, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 0, 11, -2.5F, -4.75F, 2.32F, 5, 2, 0, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 0, 13, -2.5F, -2.15F, -0.18F, 5, 2, 0, 0.0F, false));

        body = new RendererModel(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 0, 0, -4.5F, -1.0F, -7.0F, 9, 1, 14, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 30, 15, -3.0F, -9.3F, -3.35F, 6, 1, 7, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 4, 0, -1.25F, -9.55F, -2.35F, 2, 0, 4, 0.0F, false));

        leftflap = new RendererModel(this);
        leftflap.setRotationPoint(-4.5F, 0.0F, 1.0F);
        body.addChild(leftflap);
        setRotationAngle(leftflap, 0.0F, 0.0F, 0.2618F);
        leftflap.cubeList.add(new ModelBox(leftflap, 14, 19, -1.0F, -1.0F, -8.0F, 1, 1, 14, 0.001F, false));
        leftflap.cubeList.add(new ModelBox(leftflap, 0, 10, -0.75F, -10.0F, -8.0F, 0, 9, 14, 0.0F, false));

        barleftfront = new RendererModel(this);
        barleftfront.setRotationPoint(-1.0F, -1.0F, -8.0F);
        leftflap.addChild(barleftfront);
        setRotationAngle(barleftfront, -0.384F, 0.0F, 0.0F);
        barleftfront.cubeList.add(new ModelBox(barleftfront, 0, 43, 0.0F, -9.0F, 0.0F, 1, 9, 1, 0.0F, false));

        barleftback = new RendererModel(this);
        barleftback.setRotationPoint(-1.0F, -1.0F, 6.0F);
        leftflap.addChild(barleftback);
        setRotationAngle(barleftback, 0.384F, 0.0F, 0.0F);
        barleftback.cubeList.add(new ModelBox(barleftback, 0, 43, 0.0F, -9.0F, -1.0F, 1, 9, 1, 0.0F, false));

        rightflap = new RendererModel(this);
        rightflap.setRotationPoint(4.5F, 0.0F, 1.0F);
        body.addChild(rightflap);
        setRotationAngle(rightflap, 0.0F, 0.0F, -0.2618F);
        rightflap.cubeList.add(new ModelBox(rightflap, 30, 30, 0.0F, -1.0F, -8.0F, 1, 1, 14, 0.001F, false));
        rightflap.cubeList.add(new ModelBox(rightflap, 0, 1, 0.75F, -10.0F, -8.0F, 0, 9, 14, 0.0F, false));

        barrightfront = new RendererModel(this);
        barrightfront.setRotationPoint(1.0F, -1.0F, -8.0F);
        rightflap.addChild(barrightfront);
        setRotationAngle(barrightfront, -0.384F, 0.0F, 0.0F);
        barrightfront.cubeList.add(new ModelBox(barrightfront, 0, 43, -1.0F, -9.0F, 0.0F, 1, 9, 1, 0.0F, false));

        barrightback = new RendererModel(this);
        barrightback.setRotationPoint(1.0F, -1.0F, 6.0F);
        rightflap.addChild(barrightback);
        setRotationAngle(barrightback, 0.384F, 0.0F, 0.0F);
        barrightback.cubeList.add(new ModelBox(barrightback, 0, 43, -1.0F, -9.0F, -1.0F, 1, 9, 1, 0.0F, false));

        frontflap = new RendererModel(this);
        frontflap.setRotationPoint(0.0F, -1.0F, -6.5F);
        body.addChild(frontflap);
        setRotationAngle(frontflap, -0.4014F, 0.0F, 0.0F);
        frontflap.cubeList.add(new ModelBox(frontflap, 0, 34, -5.5F, -9.0F, 0.0F, 11, 9, 0, 0.0F, false));

        backflap = new RendererModel(this);
        backflap.setRotationPoint(0.0F, -1.0F, 6.5F);
        body.addChild(backflap);
        setRotationAngle(backflap, 0.3665F, 0.0F, 0.0F);
        backflap.cubeList.add(new ModelBox(backflap, 32, 0, -5.5F, -9.0F, 0.0F, 11, 9, 0, 0.0F, false));

        tail = new RendererModel(this);
        tail.setRotationPoint(0.0F, -8.25F, 3.5F);
        body.addChild(tail);
        setRotationAngle(tail, 0.2618F, 0.0F, 0.0F);
        tail.cubeList.add(new ModelBox(tail, 15, 40, -0.5F, -1.05F, 0.1F, 1, 1, 7, 0.0F, false));
    }

    @Override
    public void render(K9Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        neckandhead.render(f5);
        body.render(f5);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}