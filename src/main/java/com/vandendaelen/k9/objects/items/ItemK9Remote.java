package com.vandendaelen.k9.objects.items;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.utils.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemK9Remote extends ItemBase {

    public ItemK9Remote(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EntityK9 k9 = Utils.isAClickOnK9(player,pos);
        if(!worldIn.isRemote && k9 != null){
            this.setK9ID(player.getActiveItemStack(), k9.getEntityId());
            return EnumActionResult.SUCCESS;
        }
        return  EnumActionResult.PASS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.K9ID)) {
            tooltip.add("ID K9" + this.getK9ID(stack));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getItem() != newStack.getItem();
    }

    public static void setK9ID(ItemStack stack, int id){
        if(stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger(NBT.K9ID,id);
    }

    public static int getK9ID(ItemStack stack){
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.K9ID))
            return stack.getTagCompound().getInteger(NBT.K9ID);
        return 0;
    }



    public static class NBT {
        public final static String K9ID = "k9_id";
    }
}