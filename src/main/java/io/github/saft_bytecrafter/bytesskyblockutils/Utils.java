package io.github.saft_bytecrafter.bytesskyblockutils;

import net.minecraft.util.EnumChatFormatting;

public class Utils {

    private static boolean inSkyblock;

    public static String getColoredBool(boolean bool){
        return bool ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF";
    }

    public static boolean isInSkyblock() {
        return inSkyblock;
    }

    public static void setInSkyblock(boolean inSkyblock) {
        Utils.inSkyblock = inSkyblock;
    }
}
