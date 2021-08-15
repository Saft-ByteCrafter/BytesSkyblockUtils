package io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff;

import io.github.saft_bytecrafter.bytesskyblockutils.Utils;

import java.util.LinkedHashMap;
import java.util.List;

public class MinionMaps {

    private static final LinkedHashMap<String, List<Double>> minionTimingMaps = new LinkedHashMap<>();
    static {
        minionTimingMaps.put("COBBLESTONE",
                Utils.createList(null, 14.0, 14.0, 12.0, 12.0, 10.0, 10.0, 9.0, 9.0, 8.0, 8.0, 7.0, 6.0));
        minionTimingMaps.put("COAL",
                Utils.createList());
    } //TODO add all the other stuff here

    private static final LinkedHashMap<String, LinkedHashMap<String, Double>> minionOutputMap = new LinkedHashMap<>();
    static {
        minionOutputMap.put("COBBLESTONE",
                new LinkedHashMap<String, Double>(){{
                    put("ENCHANTED_COBBLESTONE", 1.0);
                }});
        minionOutputMap.put("COAL",
                new LinkedHashMap<String, Double>(){{
                    put("ENCHANTED_COAL", 1.0);
                }});
    } //TODO change the first "COBBLESTONE" to whatever the api returns for the minion-name (COBBLESTONE is right) and the second COBBLESTONE to whatever the api returns for ench. cobble

    public static LinkedHashMap<String, List<Double>> getMinionTimingMaps(){
        return minionTimingMaps;
    }

    public static LinkedHashMap<String, LinkedHashMap<String, Double>> getMinionOutputMap(){
        return minionOutputMap;
    }

}
