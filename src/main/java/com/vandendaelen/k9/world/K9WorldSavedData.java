package com.vandendaelen.k9.world;

import com.vandendaelen.k9.utils.helpers.K9Helper;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.Map;
import java.util.UUID;

public class K9WorldSavedData extends WorldSavedData {

    public K9WorldSavedData(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        NBTTagList list = nbtTagCompound.getTagList("k9s", Constants.NBT.TAG_COMPOUND);
        for (NBTBase base : list){
            NBTTagCompound tag = (NBTTagCompound)base;
            K9Helper.k9Owners.put(tag.getUniqueId("player"),tag.getUniqueId("k9"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        NBTTagList list = new NBTTagList();
        Map<UUID,UUID> map = K9Helper.k9Owners;
        for (int i = 0; i < map.size(); i++){
            NBTTagCompound tag = new NBTTagCompound();
            tag.setUniqueId("player", map.keySet().toArray(new UUID[]{})[i]);
            tag.setUniqueId("k9", map.values().toArray( new UUID[]{})[i]);
            list.appendTag(tag);
        }
        nbtTagCompound.setTag("k9s",list);
        return nbtTagCompound;
    }
}
