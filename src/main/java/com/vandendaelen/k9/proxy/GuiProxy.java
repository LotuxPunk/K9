package com.vandendaelen.k9.proxy;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.gui.K9Gui;
import com.vandendaelen.k9.gui.RemoteGui;
import com.vandendaelen.k9.objects.containers.K9Container;
import com.vandendaelen.k9.utils.Reference;

import net.minecraft.entity.Entity;
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
		if (ID == Reference.GUI_ID_CONTAINER) {
			Entity e = world.getEntityByID(x);
			if(e!=null||!(e instanceof EntityK9)) {
				return new K9Container(player.inventory, (EntityK9) e);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (ID == Reference.GUI_ID_CONTAINER) {
			Entity e = world.getEntityByID(x);
			if(e!=null||!(e instanceof EntityK9)) {
				return new K9Gui(new K9Container(player.inventory, (EntityK9) e), ((EntityK9) e), player, world);
			}
		}
		else if (ID == Reference.GUI_ID_REMOTE){
			Entity e = world.getEntityByID(x);
			if(e!=null||!(e instanceof EntityK9))
				return new RemoteGui(e.getUniqueID());
		}
		return null;
	}
}
