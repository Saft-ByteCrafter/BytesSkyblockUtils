package io.github.saft_bytecrafter.bytesskyblockutils;


import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.Trackers;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    private static Configuration config;
    private static final String lootTrackingFile = "config/BytesSkyblockUtils/trackers.cfg";
    private static final String mytho = "mythological trackers";

    /*   public static void init(){
           config = new Configuration(new File(configFile));
           try {
               config.load();
           }
           catch (Exception e){
               e.printStackTrace();
           }
           finally {
               config.save();
           }
       } //Base for this stuff
   */
    public static int getInt(String category, String key) {
        config = new Configuration(new File(lootTrackingFile));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, 0).getInt();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return 0;
    }

    public static void writeIntConfig(String category, String key, int value) {
        config = new Configuration(new File(lootTrackingFile));
        try {
            config.load();
            int set = config.get(category, key, value).getInt();
            config.getCategory(category).get(key).set(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static boolean hasKey(String category, String key) {
        config = new Configuration(new File(lootTrackingFile));
        try {
            config.load();
            if (!config.hasCategory(category)) return false;
            return config.getCategory(category).containsKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            config.save();
        }
        return false;
    }

    public static int initInt(String category, String key, int defaultValue) {
        if (!hasKey(category, key)) {
            writeIntConfig(category, key, defaultValue);
            return defaultValue;
        } else {
            return getInt(category, key);
        }
    }

    public static void reloadConfig(){
        //for burrows:
        Trackers.setStartBurrows(initInt(mytho, "Start Burrows", 0));
        Trackers.setMidBurrows(initInt(mytho, "Mid Burrows", 0));
        Trackers.setEndBurrows(initInt(mytho, "End Burrows", 0));
        //for creatures:
        Trackers.setHunters(initInt(mytho, "Minos Hunters", 0));
        Trackers.setLynxes(initInt(mytho, "Siamese Lynxes", 0));
        Trackers.setMinotaurs(initInt(mytho, "Minotaurs", 0));
        Trackers.setGaias(initInt(mytho, "Gaia Constructs", 0));
        Trackers.setChamps(initInt(mytho, "Minos Champions", 0));
        Trackers.setInquis(initInt(mytho, "Minos Inquisitors", 0));
        //for deaths to creatures:
        Trackers.setDeathsHunters(initInt(mytho, "Deaths Minos Hunters", 0));
        Trackers.setDeathsLynxes(initInt(mytho, "Deaths Siames Lynxes", 0));
        Trackers.setDeathsMinotaurs(initInt(mytho, "Deaths Minotaurs", 0));
        Trackers.setDeathsGaias(initInt(mytho, "Deaths Gaia Constructs", 0));
        Trackers.setDeathsChamps(initInt(mytho, "Deaths Minos Champions", 0));
        Trackers.setDeathsInquis(initInt(mytho, "Deaths Minos Inquisitors", 0));
        //for rare drops:
        Trackers.setCoins(initInt(mytho, "Coins", 0));
        Trackers.setFeathers(initInt(mytho, "Griffin Feathers", 0));
        Trackers.setCogs(initInt(mytho, "Crowns of Greed", 0));
        Trackers.setWashedUps(initInt(mytho, "Washed up Souvenirs", 0));
        Trackers.setRemedis(initInt(mytho, "Antique Remedies", 0));
        Trackers.setCrochets(initInt(mytho, "Crochet Tiger Plushies", 0));
        Trackers.setShelms(initInt(mytho, "Dwarf Turtle Shelmets", 0));
        Trackers.setSticks(initInt(mytho, "Daedalus Sticks", 0));
        Trackers.setRelics(initInt(mytho, "Minos Relics", 0));
        Trackers.setChimeras(initInt(mytho, "Chimeras", 0));
        //for other drops:
        Trackers.setGold(initInt(mytho, "Enchanted Gold", 0));
        Trackers.setIron(initInt(mytho, "Enchanted Iron", 0));
        Trackers.setEnchClaws(initInt(mytho, "Enchanted Ancient Claw", 0));
        Trackers.setClaws(initInt(mytho, "Ancient Claw", 0));
    }

}
