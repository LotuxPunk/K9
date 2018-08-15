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
    private final int btnWidth = 100;

    public GuiButton btnTeleport;

    public final int BUTTON_TELEPORT = 0;

    private UUID uuid;

    public RemoteGui(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void initGui() {
        int centerX = (width/2) - guiWidth/2;
        int centerY = (height/2) - guiHeight/2;
        int guiLastX = (width/2) + guiWidth/2;
        int guiLastY = (height/2) + guiHeight/2;

        buttonList.clear();
        buttonList.add(btnTeleport = new GuiButton(BUTTON_TELEPORT,centerX + 10,centerY + 10 ,btnWidth,btnHeight,new TextComponentTranslation(K9Strings.GuiText.K9_TELEPORT).getFormattedText()));

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
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case BUTTON_TELEPORT:
                K9.NETWORK.sendToServer(new MessageK9Teleport(uuid));
                break;
                default:
                    break;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
