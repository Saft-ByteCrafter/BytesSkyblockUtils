package io.github.saft_bytecrafter.bytesskyblockutils;

import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class Utils {

    private static boolean inSkyblock;

    public static String getColoredBool(boolean bool){
        return bool ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF";
    }

    public static <T> ArrayList<T> createList(T... values) {
        ArrayList<T> list = new ArrayList<>();
        for(T value : values)list.add(value);
        return list;
    }

    public static boolean isInSkyblock() {
        return inSkyblock;
    }

    public static void setInSkyblock(boolean inSkyblock) {
        Utils.inSkyblock = inSkyblock;
    }
}
