package com.vandendaelen.k9.entities.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;

/**
 * Created by Swirtzly
 * on 26/03/2020 @ 11:54
 */
public class RenderText {

    public int x, y;
    public boolean isSmall;
    public TextFormatting fontColor;
    public String text;
    private float fontScale = 0.0125F, fontScaleSmall = 0.75F;
    private FontRenderer fr;

    public RenderText(String text) {
        this(text, TextFormatting.WHITE);
    }

    public RenderText(String text, TextFormatting textFormatting) {
        this.text = text;
        this.fontColor = textFormatting;
        this.fr = Minecraft.getMinecraft().fontRenderer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getFontScale() {
        return fontScale;
    }

    public void setFontScale(float fontScale) {
        this.fontScale = fontScale;
    }

    public float getFontScaleSmall() {
        return fontScaleSmall;
    }

    public TextFormatting getFontColor() {
        return fontColor;
    }

    public void setFontColor(TextFormatting fontColor) {
        this.fontColor = fontColor;
    }

    public FontRenderer getFontRenderer() {
        return fr;
    }

    public void setFontRenderer(FontRenderer fr) {
        this.fr = fr;
    }

    public RenderText setSmall(boolean small) {
        this.isSmall = small;
        return this;
    }

    public RenderText setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public RenderText centerText() {
        this.x = this.fr.getStringWidth(this.text) / -2;
        return this;
    }

    public void renderText() {
        GlStateManager.pushMatrix();
        GlStateManager.scale(isSmall ? fontScaleSmall : fontScale, isSmall ? fontScaleSmall * 0.85F : fontScale, isSmall ? fontScaleSmall : fontScale);
        this.fr.drawString(this.text, this.x, this.y, fontColor.getColorIndex(), false);
        GlStateManager.popMatrix();
    }

    public interface IRenderText {
        void addText();

        void renderAllText();
    }
}
