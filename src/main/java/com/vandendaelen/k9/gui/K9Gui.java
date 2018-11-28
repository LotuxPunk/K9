package com.vandendaelen.k9.gui;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.packets.MessageK9Piloting;
import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.helpers.PlayerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.util.common.helpers.TardisHelper;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

import static java.awt.Event.*;

public class K9Gui extends GuiContainer {

    public final ResourceLocation texture = new ResourceLocation(Reference.MODID,"textures/gui/k9container.png");
    private final int guiHeight = 152;
    private final int guiWidth = 180;
    private final int btnHeight = 20;
    private final int btnWidth = 60;
    private final int fieldHeight = 10;
    private final int fieldWidth = 50;
    private final String btnConfirmText = "Allons-y !";
    private final String btnContainerText = "Inventory";

    public GuiButton btnConfirm;
    public GuiButton btnContainer;
    public GuiTextField xField;
    public GuiTextField yField;
    public GuiTextField zField;

    public final int BUTTON_CONFIRM = 0;
    public final int BUTTON_CONTAINER = 1;

    public final int X_FIELD = 0;
    public final int Y_FIELD = 1;
    public final int Z_FIELD = 2;

    public int xTravel = 0;
    public int yTravel = 0;
    public int zTravel = 0;
    public int dimTravel = 0;

    private UUID id;
    private int dim;
    private EntityPlayer player;
    private World world;
    private EntityK9 entity;

    public K9Gui(Container container, EntityK9 entity, EntityPlayer player, World world) {
        super(container);
        this.id = entity.getOwnerId();
        this.dim = entity.dimension;
        this.player = player;
        this.world = world;

        xSize = guiWidth;
        ySize = guiHeight;
        
        this.entity = entity;
    }

    @Override
    public void initGui() {
        int centerX = (width/2) - guiWidth/2;
        int centerY = (height/2) - guiHeight/2;
        int guiLastX = (width/2) + guiWidth/2;
        int guiLastY = (height/2) + guiHeight/2;

        buttonList.clear();
        buttonList.add(btnConfirm = new GuiButton(BUTTON_CONFIRM,guiLastX - 10 - btnWidth,centerY + 25,btnWidth,btnHeight,btnConfirmText));
        //buttonList.add(btnContainer = new GuiButton(BUTTON_CONTAINER,guiLastX -10 - btnWidth,centerY + 25 + btnHeight + 2 ,btnWidth, btnHeight, btnContainerText));
        xField = new GuiTextField(X_FIELD,fontRenderer,centerX + 20,centerY + 26,fieldWidth,fieldHeight);
        yField = new GuiTextField(Y_FIELD,fontRenderer,centerX + 20,centerY + 26 + fieldHeight + 5,fieldWidth,fieldHeight);
        zField = new GuiTextField(Z_FIELD,fontRenderer,centerX + 20,centerY + 26 + fieldHeight * 2 + 10,fieldWidth,fieldHeight);

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case BUTTON_CONFIRM:
                //sendChatMessage("x:" + xTravel +", y:" + yTravel +", z:" + zTravel);
                if(Loader.isModLoaded(Reference.TARDIS_MODID)){
                    if (TardisHelper.hasTardis(id)){
                        if (dim == TDimensions.TARDIS_ID){
                            BlockPos tardisBP = new BlockPos(TardisHelper.getTardis(id));
                            BlockPos destination = new BlockPos(xTravel,yTravel,zTravel);

                            K9.NETWORK.sendToServer(new MessageK9Piloting(destination,dimTravel,tardisBP));
                            Minecraft.getMinecraft().displayGuiScreen(null);
                        }
                        else{
                            PlayerHelper.sendMessage(player,"You must be in the TARDIS to set coordinates with K9",true);
                        }
                    }
                    else{
                        PlayerHelper.sendMessage(player,"You must own a TARDIS before",true);
                    }
                }
                else{
                    PlayerHelper.sendMessage(player,"TARDIS MOD not found",true);
                }

                break;
            default:
                break;
        }
        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        int centerX = (width/2) - guiWidth/2;
        int centerY = (height/2) - guiHeight/2;
        int guiLastX = (width/2) + guiWidth/2;
        int guiLastY = (height/2) + guiHeight/2;

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        //drawTexturedModalRect(centerX,centerY,0,0,guiWidth,guiHeight);
        fontRenderer.drawString("Battery level : "+ entity.getLevelEnergy()+"%",centerX + guiWidth/2 - 10,centerY + 27 + fieldHeight * 2 + 10,Color.red.getRGB(),false);

        fontRenderer.drawString("X :",centerX+5,centerY + 26,Color.black.getRGB());
        fontRenderer.drawString("Y :",centerX+5,centerY + 26 + fieldHeight + 5,Color.black.getRGB());
        fontRenderer.drawString("Z :",centerX+5,centerY + 26 + fieldHeight * 2 + 10,Color.black.getRGB());

        btnConfirm.drawButton(mc,mouseX,mouseY,0F);
        //btnContainer.drawButton(mc,mouseX,mouseY,0F);

        xField.drawTextBox();
        yField.drawTextBox();
        zField.drawTextBox();

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
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
        if(Character.isDigit(typedChar) || typedChar == BACK_SPACE || typedChar == ESCAPE || typedChar == DELETE){
            xField.textboxKeyTyped(typedChar,keyCode);
            yField.textboxKeyTyped(typedChar,keyCode);
            zField.textboxKeyTyped(typedChar,keyCode);

            if(xField.getText().length() > 9){
                xField.setText(xField.getText().substring(0, xField.getText().length() - 1));
            } else{
                if(yField.getText().length() > 9){
                    yField.setText(yField.getText().substring(0, yField.getText().length() - 1));
                }
                else {
                    if (zField.getText().length() > 9) {
                        zField.setText(zField.getText().substring(0, zField.getText().length() - 1));
                    }
                }
            }

            xTravel = updateField(xField);
            yTravel = updateField(yField);
            zTravel = updateField(zField);

            super.keyTyped(typedChar, keyCode);
        }
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
