package de.komendera.bsu;


import de.komendera.bsu.itemtracking.Trackers;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.List;

public class ConfigHandler {

    private static Configuration config;
    private static final String configFile = "config/SkyBlockUtilities.cfg";

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
        config = new Configuration(new File(configFile));
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
        config = new Configuration(new File(configFile));
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
        config = new Configuration(new File(configFile));
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
        Trackers.setStartBurrows(initInt("mythological trackers", "Start Burrows", 0));
        Trackers.setMidBurrows(initInt("mythological trackers", "Mid Burrows", 0));
        Trackers.setEndBurrows(initInt("mythological trackers", "End Burrows", 0));
        Trackers.setHunters(initInt("mythological trackers", "Minos Hunters", 0));
        Trackers.setLynxes(initInt("mythological trackers", "Siamese Lynxes", 0));
        Trackers.setMinotaurs(initInt("mythological trackers", "Minotaurs", 0));
        Trackers.setGaias(initInt("mythological trackers", "Gaia Constructs", 0));
        Trackers.setChamps(initInt("mythological trackers", "Minos Champions", 0));
        Trackers.setInquis(initInt("mythological trackers", "Minos Inquisitors", 0));
        Trackers.setCoins(initInt("mythological trackers", "Coins", 0));
        Trackers.setFeathers(initInt("mythological trackers", "Griffin Feathers", 0));
        Trackers.setRemedis(initInt("mythological trackers", "Antique Remedies", 0));
        Trackers.setCrochets(initInt("mythological trackers", "Crochet Tiger Plushies", 0));
        Trackers.setShelms(initInt("mythological trackers", "Dwarf Turtle Shelmets", 0));
        Trackers.setSticks(initInt("mythological trackers", "Daedalus Sticks", 0));
        Trackers.setRelics(initInt("mythological trackers", "Minos Relics", 0));
        Trackers.setChimeras(initInt("mythological trackers", "Chimeras", 0));
        Trackers.setGold(initInt("mythological trackers", "Enchanted Gold", 0));
        Trackers.setIron(initInt("mythological trackers", "Enchanted Iron", 0));
        Trackers.setEnchClaws(initInt("mythological trackers", "Enchanted Ancient Claw", 0));
        Trackers.setClaws(initInt("mythological trackers", "Ancient Claw", 0));
    }

}
