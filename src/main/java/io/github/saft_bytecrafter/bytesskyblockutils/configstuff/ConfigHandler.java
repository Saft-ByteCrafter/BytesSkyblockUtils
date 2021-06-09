package io.github.saft_bytecrafter.bytesskyblockutils.configstuff;


import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.Trackers;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    private static Configuration config;
    private static final String lootTrackingFile = "config/BytesSkyblockUtils/trackers.cfg";
    private static final String modConfigFile = "config/BytesSkyblockUtils/modconfig.cfg";
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
    public static boolean hasKey(String file, String category, String key) {
        config = new Configuration(new File(file));
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

    public static boolean getBoolean(String file, String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, 0).getBoolean();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return true;
    }

    public static void writeBoolConfig(String file, String category, String key, boolean value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            boolean set = config.get(category, key, value).getBoolean();
            config.getCategory(category).get(key).set(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static boolean initBool(String file, String category, String key, boolean defaultValue) {
        if (!hasKey(file, category, key)) {
            writeBoolConfig(file, category, key, defaultValue);
            return defaultValue;
        } else {
            return getBoolean(file, category, key);
        }
    }

    public static int getInt(String file, String category, String key) {
        config = new Configuration(new File(file));
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

    public static void writeIntConfig(String file, String category, String key, int value) {
        config = new Configuration(new File(file));
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

    public static int initInt(String file, String category, String key, int defaultValue) {
        if (!hasKey(file, category, key)) {
            writeIntConfig(file, category, key, defaultValue);
            return defaultValue;
        } else {
            return getInt(file, category, key);
        }
    }

    public static void reloadConfig(){
        //config:

        //trackerconfig:
        OnOffConfigs.setMythoTracker(initBool(modConfigFile, "trackers", "Mythological-Tracker", true));


        //trackers:
        //mytho:
        //for burrows:
        Trackers.setStartBurrows(initInt(lootTrackingFile, mytho, "Start Burrows", 0));
        Trackers.setMidBurrows(initInt(lootTrackingFile, mytho, "Mid Burrows", 0));
        Trackers.setEndBurrows(initInt(lootTrackingFile, mytho, "End Burrows", 0));
        //for creatures:
        Trackers.setHunters(initInt(lootTrackingFile, mytho, "Minos Hunters", 0));
        Trackers.setLynxes(initInt(lootTrackingFile, mytho, "Siamese Lynxes", 0));
        Trackers.setMinotaurs(initInt(lootTrackingFile, mytho, "Minotaurs", 0));
        Trackers.setGaias(initInt(lootTrackingFile, mytho, "Gaia Constructs", 0));
        Trackers.setChamps(initInt(lootTrackingFile, mytho, "Minos Champions", 0));
        Trackers.setInquis(initInt(lootTrackingFile, mytho, "Minos Inquisitors", 0));
        //for deaths to creatures:
        Trackers.setDeathsHunters(initInt(lootTrackingFile, mytho, "Deaths Minos Hunters", 0));
        Trackers.setDeathsLynxes(initInt(lootTrackingFile, mytho, "Deaths Siames Lynxes", 0));
        Trackers.setDeathsMinotaurs(initInt(lootTrackingFile, mytho, "Deaths Minotaurs", 0));
        Trackers.setDeathsGaias(initInt(lootTrackingFile, mytho, "Deaths Gaia Constructs", 0));
        Trackers.setDeathsChamps(initInt(lootTrackingFile, mytho, "Deaths Minos Champions", 0));
        Trackers.setDeathsInquis(initInt(lootTrackingFile, mytho, "Deaths Minos Inquisitors", 0));
        //for rare drops:
        Trackers.setCoins(initInt(lootTrackingFile, mytho, "Coins", 0));
        Trackers.setFeathers(initInt(lootTrackingFile, mytho, "Griffin Feathers", 0));
        Trackers.setCogs(initInt(lootTrackingFile, mytho, "Crowns of Greed", 0));
        Trackers.setWashedUps(initInt(lootTrackingFile, mytho, "Washed up Souvenirs", 0));
        Trackers.setRemedis(initInt(lootTrackingFile, mytho, "Antique Remedies", 0));
        Trackers.setCrochets(initInt(lootTrackingFile, mytho, "Crochet Tiger Plushies", 0));
        Trackers.setShelms(initInt(lootTrackingFile, mytho, "Dwarf Turtle Shelmets", 0));
        Trackers.setSticks(initInt(lootTrackingFile, mytho, "Daedalus Sticks", 0));
        Trackers.setRelics(initInt(lootTrackingFile, mytho, "Minos Relics", 0));
        Trackers.setChimeras(initInt(lootTrackingFile, mytho, "Chimeras", 0));
        //for other drops:
        Trackers.setGold(initInt(lootTrackingFile, mytho, "Enchanted Gold", 0));
        Trackers.setIron(initInt(lootTrackingFile, mytho, "Enchanted Iron", 0));
        Trackers.setEnchClaws(initInt(lootTrackingFile, mytho, "Enchanted Ancient Claw", 0));
        Trackers.setClaws(initInt(lootTrackingFile, mytho, "Ancient Claw", 0));
    }

    public static String getLootTrackingFile(){
        return lootTrackingFile;
    }

    public static String getModConfigFile(){
        return modConfigFile;
    }

}
