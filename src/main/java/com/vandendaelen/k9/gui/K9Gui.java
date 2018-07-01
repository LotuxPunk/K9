package com.vandendaelen.k9.gui;

import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class K9Gui extends GuiScreen {

    public final ResourceLocation texture = new ResourceLocation(Reference.MODID,"textures/gui/gui.png");
    private final int guiHeight = 166;
    private final int guiWidth = 248;
    private final int btnHeight = 20;
    private final int btnWidth = 100;
    private final String btnConfirmText = "Allons-y !";

    public GuiButton btnConfirm;

    public final int BUTTON_CONFIRM = 0;

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case BUTTON_CONFIRM:
                //Todo communicate with TARDIS
                break;
            default:
                break;
        }
        super.actionPerformed(button);
    }

    @Override
    public void initGui() {
        int guiLastX = (width/2) + guiWidth/2;
        int guiLastY = (height/2) + guiHeight/2;

        buttonList.clear();
        buttonList.add(btnConfirm = new GuiButton(BUTTON_CONFIRM,(guiLastX - guiWidth/2)- btnWidth/2,(guiLastY - 10) - btnHeight,btnWidth,btnHeight,btnConfirmText));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int centerX = (width/2) - guiWidth/2;
        int centerY = (height/2) - guiHeight/2;

        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(centerX,centerY,0,0,guiWidth,guiHeight);
        drawCenteredString(Minecraft.getMinecraft().fontRenderer,"K9 Dashboard",width/2,centerY + 10,0xFFFFFF);

        //super.drawScreen(mouseX, mouseY, partialTicks);
        btnConfirm.drawButton(mc,mouseX,mouseY,0F);
    }

    @Override
    public void drawDefaultBackground() {
        super.drawDefaultBackground();
    }
}
