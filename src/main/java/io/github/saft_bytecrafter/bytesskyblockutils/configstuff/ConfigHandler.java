package io.github.saft_bytecrafter.bytesskyblockutils.configstuff;


import io.github.saft_bytecrafter.bytesskyblockutils.guis.MinionEfficiencyGui;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.Trackers;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    private static Configuration config;
    private static final String lootTrackingFile = "config/BytesSkyblockUtils/trackers.cfg";
    private static final String modConfigFile = "config/BytesSkyblockUtils/modconfig.cfg";
    private static final String minionConfig = "config/BytesSkyblockUtils/minionconfig.cfg";
    private static final String minionTimes = "config/BytesSkyblockUtils/miniontimes.cfg";
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

    public static String getString(String file, String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, "").getString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return "";
    }

    public static void writeStringConfig(String file, String category, String key, String value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            String set = config.get(category, key, value).getString();
            config.getCategory(category).get(key).set(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static String initString(String file, String category, String key, String defaultValue) {
        if (!hasKey(file, category, key)) {
            writeStringConfig(file, category, key, defaultValue);
            return defaultValue;
        } else {
            return getString(file, category, key);
        }
    }

    public static double getDouble(String file, String category, String key) {
        config = new Configuration(new File(file));
        try {
            config.load();
            if (config.getCategory(category).containsKey(key)) {
                return config.get(category, key, 0D).getDouble();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return 0D;
    }

    public static void writeDoubleConfig(String file, String category, String key, double value) {
        config = new Configuration(new File(file));
        try {
            config.load();
            double set = config.get(category, key, value).getDouble();
            config.getCategory(category).get(key).set(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            config.save();
        }
    }

    public static double initDouble(String file, String category, String key, double defaultValue) {
        if (!hasKey(file, category, key)) {
            writeDoubleConfig(file, category, key, defaultValue);
            return defaultValue;
        } else {
            return getDouble(file, category, key);
        }
    }

    public static void reloadConfig(){
        //config:

        //trackerconfig:
        OnOffConfigs.setMythoTracker(initInt(modConfigFile, "trackers", "Mythological-Tracker", 1));

        initString(modConfigFile, "general", "API-Key", "");

        //minionefficiency config stuff
        MinionEfficiencyGui.setIntBazaarNpc(initInt(minionConfig, " ", "Bazaar or NPC", 0));//bazaar -> 0
        MinionEfficiencyGui.setIntFuel(initInt(minionConfig, " ", "Fuel", 0));
        MinionEfficiencyGui.setIntUpgrade1(initInt(minionConfig, " ", "Upgrade 1", 0));
        MinionEfficiencyGui.setIntUpgrade2(initInt(minionConfig, " ", "Upgrade 2", 0));

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

    public static String getMinionConfig(){
        return minionConfig;
    }

}
