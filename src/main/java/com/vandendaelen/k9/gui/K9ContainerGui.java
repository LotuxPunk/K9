package com.vandendaelen.k9.gui;

import com.vandendaelen.k9.objects.containers.K9Container;
import com.vandendaelen.k9.objects.tilesentities.K9ContainerTileEntity;
import com.vandendaelen.k9.utils.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class K9ContainerGui extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(Reference.MODID, "textures/gui/k9container.png");

    public K9ContainerGui(K9ContainerTileEntity tileEntity, K9Container container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
