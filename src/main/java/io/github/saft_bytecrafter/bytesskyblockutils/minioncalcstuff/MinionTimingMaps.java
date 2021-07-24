package io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff;

import java.util.HashMap;
import java.util.Map;

public class MinionTimingMaps {

    private  Map<Integer, Double> cobbleTimingMap;
    private Map<String, Map<Integer, Double>> timingMaps;

    public  MinionTimingMaps(){
        cobbleTimingMap = new HashMap<>();
        cobbleTimingMap.put(1, 14.0);
        cobbleTimingMap.put(2, 14.0);
        cobbleTimingMap.put(3, 12.0);
        cobbleTimingMap.put(4, 12.0);
        cobbleTimingMap.put(5, 10.0);
        cobbleTimingMap.put(6, 10.0);
        cobbleTimingMap.put(7, 9.0);
        cobbleTimingMap.put(8, 9.0);
        cobbleTimingMap.put(9, 8.0);
        cobbleTimingMap.put(10, 8.0);
        cobbleTimingMap.put(11, 7.0);
        cobbleTimingMap.put(12, 6.0);

        timingMaps = new HashMap<>();
        timingMaps.put("Cobblestone", cobbleTimingMap); //TODO change the "Cobblestone" to whatever the api returns for the minion-name
    }

    public Map<Integer, Double> getTimingMap(String type){
        return timingMaps.get(type);
    }

}
