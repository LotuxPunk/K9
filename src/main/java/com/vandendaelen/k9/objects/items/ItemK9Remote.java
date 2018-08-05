package com.vandendaelen.k9.objects.items;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemK9Remote extends ItemBase {

    public ItemK9Remote(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (!target.world.isRemote && target instanceof EntityK9){
            this.setK9ID(stack, target.getUniqueID());
            System.out.println(getK9ID(stack).toString());
            return true;
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    /*@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if(!target.world.isRemote){
            if (target instanceof EntityK9){
                this.setK9ID(stack, target.getUniqueID());
                return true;
            }
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }*/

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.hasTagCompound() && stack.getTagCompound().hasUniqueId(NBT.K9ID)) {
            tooltip.add("ID K9 " + this.getK9ID(stack).toString());
            System.out.println("Ca passe");
        }
        else {
            tooltip.add("You should be in survival mod to set the link between K9 and this remote");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getItem() != newStack.getItem();
    }

    public static void setK9ID(ItemStack stack, UUID id){
        if(stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setUniqueId(NBT.K9ID,id);
    }

    public static UUID getK9ID(ItemStack stack){
        if (stack.hasTagCompound() && stack.getTagCompound().hasUniqueId(NBT.K9ID))
            return stack.getTagCompound().getUniqueId(NBT.K9ID);
        return null;
    }

    public static class NBT {
        public final static String K9ID = "k9_id";
    }
}