package com.vandendaelen.k9.gui;

import java.io.IOException;
import java.util.UUID;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.init.K9Entity;
import com.vandendaelen.k9.packets.MessageK9Piloting;
import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.helpers.PlayerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.util.helpers.TardisHelper;

public class K9Gui extends GuiScreen {

    public final ResourceLocation texture = new ResourceLocation(Reference.MODID,"textures/gui/gui.png");
    private final int guiHeight = 166;
    private final int guiWidth = 248;
    private final int btnHeight = 20;
    private final int btnWidth = 100;
    private final int fieldHeight = 10;
    private final int fieldWidth = 80;
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
    private BlockPos pos;
    private EntityK9 entity;

    public K9Gui(UUID id, int dim, EntityPlayer p, World w, BlockPos po, EntityK9 entity) {
        this.id = id;
        this.dim = dim;
        this.player = p;
        this.world = w;
        this.pos = po;
        
        this.entity = entity;
    }

    @Override
    public void initGui() {
        int centerX = (width/2) - guiWidth/2;
        int centerY = (height/2) - guiHeight/2;
        int guiLastX = (width/2) + guiWidth/2;
        int guiLastY = (height/2) + guiHeight/2;

        buttonList.clear();
        buttonList.add(btnConfirm = new GuiButton(BUTTON_CONFIRM,(guiLastX - guiWidth/2)- btnWidth/2,(guiLastY - 10) - btnHeight,btnWidth,btnHeight,btnConfirmText));
        buttonList.add(btnContainer = new GuiButton(BUTTON_CONTAINER,(guiLastX-guiWidth/2)-btnWidth/2,(guiLastY-10)- btnHeight*2,btnWidth, btnHeight, btnContainerText));
        xField = new GuiTextField(X_FIELD,fontRenderer,centerX + 20,centerY + 20,fieldWidth,fieldHeight);
        yField = new GuiTextField(Y_FIELD,fontRenderer,centerX + 20,centerY + 30 + fieldHeight,fieldWidth,fieldHeight);
        zField = new GuiTextField(Z_FIELD,fontRenderer,centerX + 20,centerY + 40 + fieldHeight * 2,fieldWidth,fieldHeight);

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case BUTTON_CONFIRM:
                sendChatMessage("x:" + xTravel +", y:" + yTravel +", z:" + zTravel);
                if(Loader.isModLoaded(Reference.TARDIS_MODID)){
                    if (TardisHelper.hasTardis(id)){
                        if (dim == TDimensions.id){
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
            case BUTTON_CONTAINER:
                player.openGui(K9.instance, Reference.GUI_ID_CONTAINER, world, entity.getEntityId(), 0, 0);
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
        drawCenteredString(Minecraft.getMinecraft().fontRenderer,"K9's Dashboard",width/2,centerY + 10,0xFFFFFF);

        fontRenderer.drawString("X :",centerX+5,centerY + 20,0xFFFFFF);
        fontRenderer.drawString("Y :",centerX+5,centerY + 30 + fieldHeight,0xFFFFFF);
        fontRenderer.drawString("Z :",centerX+5,centerY + 40 + fieldHeight * 2,0xFFFFFF);

        //super.drawScreen(mouseX, mouseY, partialTicks);
        btnConfirm.drawButton(mc,mouseX,mouseY,0F);
        btnContainer.drawButton(mc,mouseX,mouseY,0F);

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
