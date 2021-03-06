package com.vandendaelen.k9.objects.items;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.packets.MessageRemoteOpenGUI;
import com.vandendaelen.k9.utils.K9Strings;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemK9Remote extends ItemBase {


    public ItemK9Remote(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote){
            EntityK9 k9 = (EntityK9) worldIn.getMinecraftServer().getEntityFromUuid(getK9ID(playerIn.getHeldItem(handIn)));
            K9.NETWORK.sendTo(new MessageRemoteOpenGUI(k9.getEntityId()),(EntityPlayerMP)playerIn);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.hasTagCompound() && stack.getTagCompound().hasUniqueId(NBT.K9ID)) {
            tooltip.add("ID K9 " + this.getK9ID(stack).toString());
        }
        else {
            tooltip.add(new TextComponentTranslation(K9Strings.Remote.TOOLTIP).getUnformattedComponentText());
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