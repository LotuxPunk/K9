package com.vandendaelen.k9.gui;

import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
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
    public GuiTextField xField;
    public GuiTextField yField;
    public GuiTextField zField;

    public final int BUTTON_CONFIRM = 0;

    public final int X_FIELD = 0;
    public final int Y_FIELD = 1;
    public final int Z_FIELD = 2;

    public int xTravel = 0;
    public int yTravel = 0;
    public int zTravel = 0;

    @Override
    public void initGui() {
        int centerX = (width/2) - guiWidth/2;
        int centerY = (height/2) - guiHeight/2;
        int guiLastX = (width/2) + guiWidth/2;
        int guiLastY = (height/2) + guiHeight/2;

        buttonList.clear();
        buttonList.add(btnConfirm = new GuiButton(BUTTON_CONFIRM,(guiLastX - guiWidth/2)- btnWidth/2,(guiLastY - 10) - btnHeight,btnWidth,btnHeight,btnConfirmText));
        xField = new GuiTextField(X_FIELD,fontRenderer,centerX + 10,centerY + 20,btnWidth,btnHeight);
        yField = new GuiTextField(Y_FIELD,fontRenderer,centerX + 10,centerY + 30 + btnHeight,btnWidth,btnHeight);
        zField = new GuiTextField(Z_FIELD,fontRenderer,centerX + 10,centerY + 40 + btnHeight * 2,btnWidth,btnHeight);

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case BUTTON_CONFIRM:
                //Todo communicate with TARDIS
                sendChatMessage("x:" + xTravel +", y:" + yTravel +", z:" + zTravel);
                break;
            default:
                break;
        }
        super.actionPerformed(button);
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

        xField.drawTextBox();
        yField.drawTextBox();
        zField.drawTextBox();
    }

    @Override
    public void drawDefaultBackground() {
        super.drawDefaultBackground();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        xField.mouseClicked(mouseX,mouseY,mouseButton);
        yField.mouseClicked(mouseX,mouseY,mouseButton);
        zField.mouseClicked(mouseX,mouseY,mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        xField.textboxKeyTyped(typedChar,keyCode);
        yField.textboxKeyTyped(typedChar,keyCode);
        zField.textboxKeyTyped(typedChar,keyCode);

        xTravel = updateField(xField);
        yTravel = updateField(yField);
        zTravel = updateField(zField);

        super.keyTyped(typedChar, keyCode);
    }

    private int updateField(GuiTextField field){
        if (!field.getText().isEmpty())
            return Integer.parseInt(field.getText());
        return 0;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
