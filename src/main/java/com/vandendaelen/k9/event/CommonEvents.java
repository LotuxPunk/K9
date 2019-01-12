package com.vandendaelen.k9.event;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.utils.K9Teleporter;
import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.helpers.K9Helper;
import com.vandendaelen.k9.world.K9WorldSavedData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class CommonEvents {

    public static K9WorldSavedData data;

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
        EntityPlayer player = e.player;
        if (!player.world.isRemote) {
            ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
            if (version.status == ForgeVersion.Status.OUTDATED) {
                TextComponentString url = new TextComponentString(TextFormatting.DARK_AQUA + TextFormatting.BOLD.toString() + "UPDATE");
                url.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/k9-mod"));
                url.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open link")));

                player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[K9 Mod] : ").appendSibling(url));
                String changes = String.valueOf(version.changes).replace("{" + version.target + "=", "").replace("}", "");
                player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Changelog: " + TextFormatting.AQUA + changes));
            }
        }
    }

    @SubscribeEvent
    public static void loadK9s(WorldEvent.Load event){
        data = (K9WorldSavedData)event.getWorld().getMapStorage().getOrLoadData(K9WorldSavedData.class,"k9");
        if(data == null) data = new K9WorldSavedData("k9");
    }

    @SubscribeEvent
    public static void saveK9s(WorldEvent.Save event){
        event.getWorld().getMapStorage().setData("k9",data);
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event){
        if (K9Helper.hasK9(event.player.getUniqueID())){
            EntityK9 k9 = (EntityK9) event.player.world.getMinecraftServer().getEntityFromUuid(K9Helper.getK9(event.player.getUniqueID()));
            if (k9 != null) K9Teleporter.move(k9,event.toDim,event.player.getPosition());
        }
    }
}
