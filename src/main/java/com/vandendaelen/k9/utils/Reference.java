package com.vandendaelen.k9.utils;

public class Reference {
    public static final String MODID ="k9";
    public static final String NAME = "K9 Mod";
    public static final String DEPENDENCIES = "required:forge@[14.23.5.2768,)";

    public static final String UPDATE_JSON = "https://raw.githubusercontent.com/LotuxPunk/K9/master/update.json";

    public static final String CLIENT_PROXY = "com.vandendaelen.k9.proxy.ClientProxy";
    public static final String SERVER_PROXY = "com.vandendaelen.k9.proxy.ServerProxy";

    public static final String TARDIS_MODID = "tardis";

    public static final int GUI_ID_CONTAINER = 1;
    public static final int GUI_ID_REMOTE = 2;

    public static class Version{
        public static final String MCVERSION = "1.12.2";
        public static final String MAJORMOD = "1";
        public static final String MAJORAPI = "3";
        public static final String MINOR = "1";
        public static final String PATCH = "2";
        public static final String VERSION = MCVERSION+"-"+MAJORMOD+"."+MAJORAPI+"."+MINOR+"."+PATCH;
    }
}
