package com.vandendaelen.k9.entities.model;

import com.vandendaelen.k9.entities.render.RenderText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class ModelK9 extends ModelBase implements RenderText.IRenderText {

    private final ModelRenderer tail;
    private final ModelRenderer headjoint;
    private final ModelRenderer head;
    private final ModelRenderer earleft;
    private final ModelRenderer earright;
    private final ModelRenderer eyeball;
    private final ModelRenderer eyetop;
    private final ModelRenderer eyebottom;
    private final ModelRenderer muzzleleft;
    private final ModelRenderer noseleft;
    private final ModelRenderer muzzleright;
    private final ModelRenderer noseright;
    private final ModelRenderer guntop;
    private final ModelRenderer snooter;
    private final ModelRenderer K9;
    private final ModelRenderer computerpanelangle;
    private final ModelRenderer frontangletop;
    private final ModelRenderer backangletop;
    private final ModelRenderer frontanglebottom;
    private final ModelRenderer backanglebottom;
    private final ModelRenderer bottomleft;
    private final ModelRenderer bottomright;
    private final ModelRenderer leftpanel;
    private final ModelRenderer leftpaneldeeperangle;
    private final ModelRenderer leftpanelwooble;
    private final ModelRenderer rightpanel;
    private final ModelRenderer rightpaneldeeperangle;
    private final ModelRenderer rightpanelwooble;

    private final List<RenderText> textToRender = new ArrayList<>();


    public ModelK9() {
        textureWidth = 64;
        textureHeight = 64;

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 17.0F, 4.0F);
        tail.cubeList.add(new ModelBox(tail, 0, 32, -0.5F, -0.5F, -1.0F, 1, 1, 4, 0.0F, false));

        headjoint = new ModelRenderer(this);
        headjoint.setRotationPoint(0.0F, 18.0F, -5.0F);
        setRotationAngle(headjoint, -0.4363F, 0.0F, 0.0F);
        headjoint.cubeList.add(new ModelBox(headjoint, 18, 18, -1.0F, -2.0F, -2.0F, 2, 2, 2, 0.0F, false));

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -2.0F, -2.0F);
        setRotationAngle(head, 0.4363F, 0.0F, 0.0F);
        headjoint.addChild(head);
        head.cubeList.add(new ModelBox(head, 28, 18, -1.5F, -2.0F, -2.0F, 3, 4, 3, 0.0F, false));
        head.cubeList.add(new ModelBox(head, 16, 2, -1.0F, 0.0F, -3.5F, 2, 2, 3, 0.0F, false));

        earleft = new ModelRenderer(this);
        earleft.setRotationPoint(0.75F, -2.0F, -0.5F);
        setRotationAngle(earleft, 0.0F, 0.7854F, 0.0F);
        head.addChild(earleft);
        earleft.cubeList.add(new ModelBox(earleft, 20, 27, -0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F, false));

        earright = new ModelRenderer(this);
        earright.setRotationPoint(-0.75F, -2.0F, -0.5F);
        setRotationAngle(earright, 0.0F, 0.7854F, 0.0F);
        head.addChild(earright);
        earright.cubeList.add(new ModelBox(earright, 20, 27, -0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F, false));

        eyeball = new ModelRenderer(this);
        eyeball.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(eyeball);
        eyeball.cubeList.add(new ModelBox(eyeball, 34, 12, -0.5F, -1.5F, -3.0F, 1, 1, 3, 0.0F, false));

        eyetop = new ModelRenderer(this);
        eyetop.setRotationPoint(0.0F, -2.0F, -2.0F);
        setRotationAngle(eyetop, -0.2618F, 0.0F, 0.0F);
        head.addChild(eyetop);
        eyetop.cubeList.add(new ModelBox(eyetop, 16, 7, -1.5F, 0.0F, 0.0F, 3, 1, 0, 0.0F, false));

        eyebottom = new ModelRenderer(this);
        eyebottom.setRotationPoint(0.0F, 0.0F, -2.0F);
        setRotationAngle(eyebottom, 0.2618F, 0.0F, 0.0F);
        head.addChild(eyebottom);
        eyebottom.cubeList.add(new ModelBox(eyebottom, 16, 7, -1.5F, -1.0F, 0.0F, 3, 1, 0, 0.0F, false));

        muzzleleft = new ModelRenderer(this);
        muzzleleft.setRotationPoint(1.5F, 0.0F, -2.0F);
        setRotationAngle(muzzleleft, 0.0F, -1.309F, 0.0F);
        head.addChild(muzzleleft);
        muzzleleft.cubeList.add(new ModelBox(muzzleleft, 0, 5, -3.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F, false));

        noseleft = new ModelRenderer(this);
        noseleft.setRotationPoint(-3.0F, 0.0F, 0.0F);
        setRotationAngle(noseleft, 0.0F, -0.2618F, 0.0F);
        muzzleleft.addChild(noseleft);
        noseleft.cubeList.add(new ModelBox(noseleft, 6, 32, 0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F, false));

        muzzleright = new ModelRenderer(this);
        muzzleright.setRotationPoint(-1.5F, 0.0F, -2.0F);
        setRotationAngle(muzzleright, 0.0F, 1.309F, 0.0F);
        head.addChild(muzzleright);
        muzzleright.cubeList.add(new ModelBox(muzzleright, 0, 5, 0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F, false));

        noseright = new ModelRenderer(this);
        noseright.setRotationPoint(3.0F, 0.0F, 0.0F);
        setRotationAngle(noseright, 0.0F, 0.2618F, 0.0F);
        muzzleright.addChild(noseright);
        noseright.cubeList.add(new ModelBox(noseright, 6, 32, -2.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F, false));

        guntop = new ModelRenderer(this);
        guntop.setRotationPoint(0.0F, 0.0F, -2.8978F);
        setRotationAngle(guntop, -0.0873F, 0.0F, 0.0F);
        head.addChild(guntop);
        guntop.cubeList.add(new ModelBox(guntop, 0, 14, -0.5F, 0.0F, -2.0F, 1, 1, 2, 0.0F, false));

        snooter = new ModelRenderer(this);
        snooter.setRotationPoint(0.0F, 0.0F, -4.8978F);
        setRotationAngle(snooter, -0.1745F, 0.0F, 0.0F);
        head.addChild(snooter);
        snooter.cubeList.add(new ModelBox(snooter, 28, 11, -0.5F, 0.0F, 0.0F, 1, 1, 1, 0.0F, false));

        K9 = new ModelRenderer(this);
        K9.setRotationPoint(0.0F, 24.0F, 0.0F);
        K9.cubeList.add(new ModelBox(K9, 20, 27, -1.5F, -8.5F, -1.5F, 3, 2, 4, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 34, 8, -1.0F, -8.75F, -1.0F, 2, 1, 3, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 0, 0, -2.0F, -8.0F, -4.0F, 4, 2, 8, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 0, 23, -1.0F, -8.0F, -5.0F, 2, 2, 1, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 29, 3, -2.5F, -6.0F, -4.0F, 5, 2, 1, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 29, 3, -2.5F, -6.0F, 3.0F, 5, 2, 1, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 29, 8, -1.0F, -5.5F, -4.5F, 2, 1, 1, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 29, 1, -3.0F, -1.0F, -4.5F, 6, 1, 1, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 0, 10, -3.0F, -1.0F, -3.5F, 6, 1, 7, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 26, 11, 3.0F, -1.0F, -3.0F, 1, 1, 6, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 26, 11, -4.0F, -1.0F, -3.0F, 1, 1, 6, 0.0F, false));
        K9.cubeList.add(new ModelBox(K9, 29, 1, -3.0F, -1.0F, 3.5F, 6, 1, 1, 0.0F, false));

        computerpanelangle = new ModelRenderer(this);
        computerpanelangle.setRotationPoint(0.0F, -8.5F, -1.0F);
        setRotationAngle(computerpanelangle, -0.2618F, 0.0F, 0.0F);
        K9.addChild(computerpanelangle);
        computerpanelangle.cubeList.add(new ModelBox(computerpanelangle, 19, 11, -1.5F, 0.0F, -1.5F, 3, 2, 3, 0.0F, false));
        computerpanelangle.cubeList.add(new ModelBox(computerpanelangle, 12, 29, -1.0F, -0.25F, -1.25F, 2, 1, 1, 0.0F, false));

        frontangletop = new ModelRenderer(this);
        frontangletop.setRotationPoint(0.0F, -4.0F, -4.0F);
        setRotationAngle(frontangletop, -0.7854F, 0.0F, 0.0F);
        K9.addChild(frontangletop);
        frontangletop.cubeList.add(new ModelBox(frontangletop, 0, 29, -2.5F, -1.0F, 0.0F, 5, 1, 2, 0.0F, false));

        backangletop = new ModelRenderer(this);
        backangletop.setRotationPoint(0.0F, -4.0F, 4.0F);
        setRotationAngle(backangletop, 0.7854F, 0.0F, 0.0F);
        K9.addChild(backangletop);
        backangletop.cubeList.add(new ModelBox(backangletop, 0, 29, -2.5F, -1.0F, -2.0F, 5, 1, 2, 0.0F, false));

        frontanglebottom = new ModelRenderer(this);
        frontanglebottom.setRotationPoint(0.0F, -1.0F, -4.0F);
        setRotationAngle(frontanglebottom, -0.3491F, 0.0F, 0.0F);
        K9.addChild(frontanglebottom);
        frontanglebottom.cubeList.add(new ModelBox(frontanglebottom, 16, 0, -3.0F, -1.0F, 0.0F, 6, 1, 1, 0.0F, false));
        frontanglebottom.cubeList.add(new ModelBox(frontanglebottom, 18, 22, -0.25F, -2.0F, 0.0F, 3, 1, 1, 0.0F, false));
        frontanglebottom.cubeList.add(new ModelBox(frontanglebottom, 29, 6, -2.5F, -3.0F, 0.0F, 5, 1, 1, 0.0F, false));
        frontanglebottom.cubeList.add(new ModelBox(frontanglebottom, 18, 22, -2.75F, -2.0F, 0.0F, 3, 1, 1, 0.0F, false));

        backanglebottom = new ModelRenderer(this);
        backanglebottom.setRotationPoint(0.0F, -1.0F, 4.0F);
        setRotationAngle(backanglebottom, 0.3491F, 0.0F, 0.0F);
        K9.addChild(backanglebottom);
        backanglebottom.cubeList.add(new ModelBox(backanglebottom, 16, 0, -3.0F, -1.0F, -1.0F, 6, 1, 1, 0.0F, false));
        backanglebottom.cubeList.add(new ModelBox(backanglebottom, 18, 22, -0.25F, -2.0F, -1.0F, 3, 1, 1, 0.0F, false));
        backanglebottom.cubeList.add(new ModelBox(backanglebottom, 29, 6, -2.5F, -3.0F, -1.0F, 5, 1, 1, 0.0F, false));
        backanglebottom.cubeList.add(new ModelBox(backanglebottom, 18, 22, -2.75F, -2.0F, -1.0F, 3, 1, 1, 0.0F, false));

        bottomleft = new ModelRenderer(this);
        bottomleft.setRotationPoint(3.5F, -1.0F, 0.0F);
        setRotationAngle(bottomleft, 0.0F, 0.0F, -0.3491F);
        K9.addChild(bottomleft);
        bottomleft.cubeList.add(new ModelBox(bottomleft, 28, 28, -1.0F, -3.0F, -3.0F, 1, 3, 6, 0.0F, false));

        bottomright = new ModelRenderer(this);
        bottomright.setRotationPoint(-3.5F, -1.0F, 0.0F);
        setRotationAngle(bottomright, 0.0F, 0.0F, 0.3491F);
        K9.addChild(bottomright);
        bottomright.cubeList.add(new ModelBox(bottomright, 28, 28, 0.0F, -3.0F, -3.0F, 1, 3, 6, 0.0F, false));

        leftpanel = new ModelRenderer(this);
        leftpanel.setRotationPoint(2.0F, -8.0F, 0.0F);
        setRotationAngle(leftpanel, 0.0F, 0.0F, -0.3491F);
        K9.addChild(leftpanel);
        leftpanel.cubeList.add(new ModelBox(leftpanel, 0, 18, -1.0F, 1.0F, -4.0F, 1, 2, 3, 0.0F, false));
        leftpanel.cubeList.add(new ModelBox(leftpanel, 0, 0, -1.0F, 1.0F, 1.0F, 1, 2, 3, 0.0F, false));
        leftpanel.cubeList.add(new ModelBox(leftpanel, 0, 10, -1.25F, 1.0F, -1.0F, 1, 2, 2, 0.0F, false));
        leftpanel.cubeList.add(new ModelBox(leftpanel, 18, 18, -1.0F, 0.0F, -4.0F, 1, 1, 8, 0.0F, false));

        leftpaneldeeperangle = new ModelRenderer(this);
        leftpaneldeeperangle.setRotationPoint(0.0F, 3.0F, 0.0F);
        setRotationAngle(leftpaneldeeperangle, 0.0F, 0.0F, 0.1745F);
        leftpanel.addChild(leftpaneldeeperangle);
        leftpaneldeeperangle.cubeList.add(new ModelBox(leftpaneldeeperangle, 10, 27, -1.0F, 0.0F, -4.0F, 1, 1, 8, 0.0F, false));
        leftpaneldeeperangle.cubeList.add(new ModelBox(leftpaneldeeperangle, 10, 18, -1.0F, 1.0F, -4.0F, 1, 4, 1, 0.0F, false));
        leftpaneldeeperangle.cubeList.add(new ModelBox(leftpaneldeeperangle, 10, 18, -1.0F, 1.0F, 3.0F, 1, 4, 1, 0.0F, false));

        leftpanelwooble = new ModelRenderer(this);
        leftpanelwooble.setRotationPoint(0.0F, 1.0F, 0.0F);
        setRotationAngle(leftpanelwooble, 0.0F, 0.0F, -0.7854F);
        leftpaneldeeperangle.addChild(leftpanelwooble);
        leftpanelwooble.cubeList.add(new ModelBox(leftpanelwooble, 10, 18, -1.0F, -1.0F, -3.0F, 1, 1, 6, 0.0F, false));

        rightpanel = new ModelRenderer(this);
        rightpanel.setRotationPoint(-2.0F, -8.0F, 0.0F);
        setRotationAngle(rightpanel, 0.0F, 0.0F, 0.3491F);
        K9.addChild(rightpanel);
        rightpanel.cubeList.add(new ModelBox(rightpanel, 0, 18, 0.0F, 0.0F, -4.0F, 1, 3, 8, 0.0F, false));

        rightpaneldeeperangle = new ModelRenderer(this);
        rightpaneldeeperangle.setRotationPoint(0.0F, 3.0F, 0.0F);
        setRotationAngle(rightpaneldeeperangle, 0.0F, 0.0F, -0.1745F);
        rightpanel.addChild(rightpaneldeeperangle);
        rightpaneldeeperangle.cubeList.add(new ModelBox(rightpaneldeeperangle, 19, 2, 0.0F, 0.0F, -4.0F, 1, 1, 8, 0.0F, false));
        rightpaneldeeperangle.cubeList.add(new ModelBox(rightpaneldeeperangle, 10, 18, 0.0F, 1.0F, -4.0F, 1, 4, 1, 0.0F, false));
        rightpaneldeeperangle.cubeList.add(new ModelBox(rightpaneldeeperangle, 10, 18, 0.0F, 1.0F, 3.0F, 1, 4, 1, 0.0F, false));

        rightpanelwooble = new ModelRenderer(this);
        rightpanelwooble.setRotationPoint(0.0F, 1.0F, 0.0F);
        setRotationAngle(rightpanelwooble, 0.0F, 0.0F, 0.7854F);
        rightpaneldeeperangle.addChild(rightpanelwooble);
        rightpanelwooble.cubeList.add(new ModelBox(rightpanelwooble, 10, 18, 0.0F, -1.0F, -3.0F, 1, 1, 6, 0.0F, false));

        addText();

    }


    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        tail.render(f5);
        headjoint.render(f5);
        K9.render(f5);

        renderAllText();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

    }

    @Override
    public void addText() {
        this.textToRender.add(new RenderText("K-9").setPosition(-54, 0));
    }

    @Override
    public void renderAllText() {
        FontRenderer fr = (Minecraft.getMinecraft()).fontRenderer;
        float fontZOffset = -0.975F;
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -0.86F, 0.0F);
        if (fr != null)
            GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, fontZOffset);
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.translate(0, 0, -0.2);
        for (RenderText textPiece : this.textToRender) {
            textPiece.renderText();
        }
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }
}