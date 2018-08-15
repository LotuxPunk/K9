package com.vandendaelen.k9.gui;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.packets.MessageK9Teleport;
import com.vandendaelen.k9.utils.K9Strings;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

import java.io.IOException;
import java.util.UUID;

public class RemoteGui extends GuiScreen {
    public final ResourceLocation texture = new ResourceLocation(Reference.MODID,"textures/gui/gui.png");
    private final int guiHeight = 166;
    private final int guiWidth = 248;
    private final int btnHeight = 20;
    private final int btnWidth = 200;

    private GuiButton btnTeleport;
    private GuiButton btnMode;


    public final int BUTTON_TELEPORT = 0;
    public final int BUTTON_MODE = 1;

    private String btnTextMode;

    private UUID uuid;
    private int mode;

    public RemoteGui(UUID uuid, int mode) {
        this.uuid = uuid;
        this.mode = mode;
        btnTextMode = getModeString(mode);
    }

    @Override
    public void initGui() {
        int guiFirstX = width/2 - guiWidth/2;
        int guiFirstY = height/2 - guiHeight/2;
        int guiLastX = width/2 + guiWidth/2;
        int guiLastY = height/2 + guiHeight/2;
        int guiCenterX = guiFirstX + guiWidth/2;

        buttonList.clear();
        buttonList.add(btnTeleport = new GuiButton(BUTTON_TELEPORT,guiCenterX - btnWidth/2,guiFirstY + 10 ,btnWidth,btnHeight,new TextComponentTranslation(K9Strings.GuiText.K9_TELEPORT).getFormattedText()));
        buttonList.add(btnMode = new GuiButton(BUTTON_MODE,guiCenterX - btnWidth/2,guiFirstY + 20 + btnHeight,btnWidth, btnHeight, new TextComponentTranslation(btnTextMode).getFormattedText()));

        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int centerX = (width/2) - guiWidth/2;
        int centerY = (height/2) - guiHeight/2;

        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(centerX,centerY,0,0,guiWidth,guiHeight);
        btnTeleport.drawButton(mc,mouseX,mouseY,0F);
        btnMode.drawButton(mc,mouseX,mouseY,0F);
        switch (mode){
            case 0:
                btnMode.displayString = new TextComponentTranslation(K9Strings.Mode.PEACEFUL).getFormattedText();
                break;
            case 1:
                btnMode.displayString = new TextComponentTranslation(K9Strings.Mode.MOB_PROTECTION).getFormattedText();
                break;
            default:
                btnMode.displayString = new TextComponentTranslation(K9Strings.Mode.FULL_PROTECTION).getFormattedText();
                break;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case BUTTON_TELEPORT:
                K9.NETWORK.sendToServer(new MessageK9Teleport(uuid));
                break;
            case BUTTON_MODE:
                mode = clickMode(mode);
            default:
                break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private String getModeString(int value){
        switch (value){
            case 0 :
                return K9Strings.Mode.PEACEFUL;
            case 1 :
                return K9Strings.Mode.MOB_PROTECTION;
            default :
                return K9Strings.Mode.FULL_PROTECTION;
        }
    }

    private int clickMode(int value){
        switch (value){
            case 2:
                return 0;
            default:
                return value+1;
        }
    }
}
