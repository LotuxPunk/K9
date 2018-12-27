package com.vandendaelen.k9.utils.helpers;

import com.vandendaelen.k9.event.CommonEvents;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class K9Helper {
    public static Map<UUID,UUID> k9Owners = new HashMap<>(); //UUID Key : Player, UUID value : K-9

    public static boolean hasK9(UUID id){
        return k9Owners.containsKey(id);
    }

    public static void addK9(UUID playerID, UUID k9ID){
        k9Owners.put(playerID,k9ID);
        if (CommonEvents.data != null)
            CommonEvents.data.markDirty();
    }

    public static void removeK9(UUID playerID, UUID k9ID){
        k9Owners.remove(playerID, k9ID);
    }

    public static  UUID getK9(UUID playerID){
        return k9Owners.get(playerID);
    }
}
