package com.vandendaelen.k9.proxy;

import com.vandendaelen.k9.gui.K9ContainerGui;
import com.vandendaelen.k9.objects.containers.K9Container;
import com.vandendaelen.k9.objects.tilesentities.K9ContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof K9ContainerTileEntity) {
            return new K9Container(player.inventory, (K9ContainerTileEntity) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof K9ContainerTileEntity) {
            K9ContainerTileEntity containerTileEntity = (K9ContainerTileEntity) te;
            return new K9ContainerGui(containerTileEntity, new K9Container(player.inventory, containerTileEntity));
        }
        return null;
    }
}
