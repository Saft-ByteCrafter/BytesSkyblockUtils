package de.komendera.bsu.itemtracking;

import de.komendera.bsu.ConfigHandler;
import de.komendera.bsu.BSUMain;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class Trackers {

    private static int startBurrows;
    private static int midBurrows;
    private static int endBurrows;
    private static int hunters;
    private static int deathsHunters;
    private static int lynxes;
    private static int deathsLynxes;
    private static int minotaurs;
    private static int deathsMinotaurs;
    private static int gaias;
    private static int deathsGaias;
    private static int champs;
    private static int deathsChamps;
    private static int inquis;
    private static int deathsInquis;
    private static int coins;
    private static int feathers;
    private static int cogs;
    private static int washedUps;
    private static int remedis;
    private static int crochets;
    private static int shelms;
    private static int sticks;
    private static int relics;
    private static int chimeras;
    private static int gold;
    private static int iron;
    private static int enchClaws;
    private static int claws;
    private static final String mytho = "mythological trackers";
    private List<DifferentItems> differentItems;

//    private SBUMain main = SBUMain.getInstance();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChat(ClientChatReceivedEvent event){

        String chatMessage = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(chatMessage.contains(":")){
            //-> don't do anything
        } else if(chatMessage.startsWith("You dug out a Griffin Burrow! (1/4)")){
            startBurrows++;
            BSUMain.compareInventories.getNewItems(Minecraft.getMinecraft().thePlayer.inventory.mainInventory); //wird am anfang (jeder chain :/) gemacht, damit das default inv geladen wird TODO so machen, dass es nich am anfang jeder chain gemacht wird
            ConfigHandler.writeIntConfig(mytho, "Start Burrows", startBurrows);
        } else if(chatMessage.startsWith("You dug out a Griffin Burrow! (2/4)") || chatMessage.startsWith("You dug out a Griffin Burrow! (3/4)")){
            midBurrows++;
            ConfigHandler.writeIntConfig(mytho, "Mid Burrows", midBurrows);
            BSUMain.compareInventories.getNewItems(Minecraft.getMinecraft().thePlayer.inventory.mainInventory);
            differentItems = BSUMain.compareInventories.getDifferentItems();
            for(DifferentItems item: differentItems){
                if(item.getItemName().contains("Enchanted Gold") && item.getItemAmount() > 0){
                    gold += item.getItemAmount();
                }
                else if(item.getItemName().contains("Enchanted Iron") && item.getItemAmount() > 0){
                    iron += item.getItemAmount();
                }
                else if(item.getItemName().contains("Enchanted Ancient Claw") && item.getItemAmount() > 0){
                    enchClaws += item.getItemAmount();
                }
                else if(item.getItemName().contains("Ancient Claw") && item.getItemAmount() > 0){
                    claws += item.getItemAmount();
                }
                else if(item.getItemName().contains("Antique Remedies")){
                    remedis++;
                    ConfigHandler.writeIntConfig(mytho, "Antique Remedies", remedis);
                }
                else if(item.getItemName().contains("Crochet Tiger Plushie")){
                    crochets++;
                    ConfigHandler.writeIntConfig(mytho, "Crochet Tiger Plushies", crochets);
                }
                else if(item.getItemName().contains("Dwarf Turtle Shelmet")){
                    shelms++;
                    ConfigHandler.writeIntConfig(mytho, "Dwarf Turtle Shelmets", shelms);
                }
                else if(item.getItemName().contains("Daedalus Stick")){
                    sticks++;
                    ConfigHandler.writeIntConfig(mytho, "Daedalus Sticks", sticks);
                }
                else if(item.getItemName().contains("Minos Relic")){
                    relics++;
                    ConfigHandler.writeIntConfig(mytho, "Minos Relics", relics);
                }
                else if(item.getItemName().contains("Enchanted Book")){
                    chimeras++;
                    ConfigHandler.writeIntConfig(mytho, "Chimeras", chimeras);
                }
            } //^the picked up items are added to the variables
            ConfigHandler.writeIntConfig(mytho, "Enchanted Gold", gold);
            ConfigHandler.writeIntConfig(mytho, "Enchanted Iron", iron);
            ConfigHandler.writeIntConfig(mytho, "Enchanted Ancient Claw", enchClaws);
            ConfigHandler.writeIntConfig(mytho, "Ancient Claw", claws); //the variables are passed to the cfg
        } else if(chatMessage.startsWith("You dug out a Griffin Burrow! (4/4)")){
            endBurrows++;
            ConfigHandler.writeIntConfig(mytho, "End Burrows" , endBurrows);
        } else if(chatMessage.contains("You dug out") && chatMessage.contains("Minos Hunter")){
            hunters++;
            ConfigHandler.writeIntConfig(mytho, "Minos Hunters", hunters);
        } else if(chatMessage.contains("You dug out") && chatMessage.contains("Siamese Lynxes")){
            lynxes++;
            ConfigHandler.writeIntConfig(mytho, "Siamese Lynxes", lynxes);
        } else if(chatMessage.contains("You dug out") && chatMessage.contains("Minotaur")){
            minotaurs++;
            ConfigHandler.writeIntConfig(mytho, "Minotaurs", minotaurs);
        } else if(chatMessage.contains("You dug out") && chatMessage.contains("Gaia Construct")){
            gaias++;
            ConfigHandler.writeIntConfig(mytho, "Gaia Constructs", gaias);
        } else if(chatMessage.contains("You dug out") && chatMessage.contains("Minos Champion")){
            champs++;
            ConfigHandler.writeIntConfig(mytho, "Minos Champions", champs);
        } else if(chatMessage.contains("You dug out") && chatMessage.contains("Minos Inquisitor")){
            inquis++; //doesn't happen rn cause hypixel bad and they send the same msg for inquis as for champs, thats why the code under this exists
            ConfigHandler.writeIntConfig(mytho, "Minos Inquisitors", inquis);
        } else if(chatMessage.contains("Actually, you dug up a ")){//TODO this kinda does not work
            inquis++;
            ConfigHandler.writeIntConfig(mytho, "Minos Inquisitors", inquis);
            champs--;
            ConfigHandler.writeIntConfig(mytho, "Minos Champions", champs);
        } else if(chatMessage.contains("You dug out ") && chatMessage.contains("coins!")){
            String[] cutChatMessage = chatMessage.split(" ");
            int amount = Integer.valueOf(cutChatMessage[4].replace(",", ""));
            coins += amount;
            ConfigHandler.writeIntConfig(mytho, "Coins", coins);
        } else if(chatMessage.contains("Griffin Feather")){
            feathers++;
            ConfigHandler.writeIntConfig(mytho, "Griffin Feathers", feathers);
        } else if(chatMessage.contains("Crown of Greed")){
            cogs++;
            ConfigHandler.writeIntConfig(mytho, "Crowns of Greed", cogs);
        } else if(chatMessage.contains("Washed up")){
            washedUps++;
            ConfigHandler.writeIntConfig(mytho, "Washed up Souvenirs", washedUps);
        } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Minos Hunter")){
            deathsHunters++;
            ConfigHandler.writeIntConfig(mytho, "Deaths Minos Hunters", deathsHunters);
        } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Siamese")){
            deathsLynxes++;
            ConfigHandler.writeIntConfig(mytho, "Deaths Siames Lynxes", deathsLynxes);
        } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Minotaur")){
            deathsMinotaurs++;
            ConfigHandler.writeIntConfig(mytho, "Deaths Minotaurs", deathsMinotaurs);
        } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Gaia Construct")){
            deathsGaias++;
            ConfigHandler.writeIntConfig(mytho, "Deaths Gaia Constructs", deathsGaias);
        } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Minos Champion")){//TODO doesn't work, inquis do work tho
            deathsChamps++;
            ConfigHandler.writeIntConfig(mytho, "Deaths Minos Champions", deathsChamps);
        } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Minos Inquisitor")){
            deathsInquis++;
            ConfigHandler.writeIntConfig(mytho, "Deaths Minos Inquisitors", deathsInquis);
        }
    }



    public static void setStartBurrows(int startBurrows) {
        Trackers.startBurrows = startBurrows;
    }

    public static void setMidBurrows(int midBurrows) {
        Trackers.midBurrows = midBurrows;
    }

    public static void setEndBurrows(int endBurrows) {
        Trackers.endBurrows = endBurrows;
    }

    public static void setHunters(int hunters){
        Trackers.hunters = hunters;
    }

    public static void setLynxes(int lynxes){
        Trackers.lynxes = lynxes;
    }

    public static void setMinotaurs(int minotaurs){
        Trackers.minotaurs = minotaurs;
    }

    public static void setGaias(int gaias){
        Trackers.gaias = gaias;
    }

    public static void setChamps(int champs){
        Trackers.champs = champs;
    }

    public static void setInquis(int inquis){
        Trackers.inquis = inquis;
    }

    public static void setCoins(int coins) {
        Trackers.coins = coins;
    }

    public static void setFeathers(int feathers) {
        Trackers.feathers = feathers;
    }

    public static void setCogs(int cogs){
        Trackers.cogs = cogs;
    }

    public static void setWashedUps(int washedUps){
        Trackers.washedUps = washedUps;
    }

    public static void setRemedis(int remedis) {
        Trackers.remedis = remedis;
    }

    public static void setCrochets(int crochets) {
        Trackers.crochets = crochets;
    }

    public static void setShelms(int shelms) {
        Trackers.shelms = shelms;
    }

    public static void setSticks(int sticks) {
        Trackers.sticks = sticks;
    }

    public static void setRelics(int relics) {
        Trackers.relics = relics;
    }

    public static void setChimeras(int chimeras) {
        Trackers.chimeras = chimeras;
    }

    public static void setGold(int gold) {
        Trackers.gold = gold;
    }

    public static void setIron(int iron) {
        Trackers.iron = iron;
    }

    public static void setEnchClaws(int enchClaws) {
        Trackers.enchClaws = enchClaws;
    }

    public static void setClaws(int claws) {
        Trackers.claws = claws;
    }

    public static void setDeathsHunters(int deathsHunters) {
        Trackers.deathsHunters = deathsHunters;
    }

    public static void setDeathsLynxes(int deathsLynxes) {
        Trackers.deathsLynxes = deathsLynxes;
    }

    public static void setDeathsMinotaurs(int deathsMinotaurs) {
        Trackers.deathsMinotaurs = deathsMinotaurs;
    }

    public static void setDeathsGaias(int deathsGaias) {
        Trackers.deathsGaias = deathsGaias;
    }

    public static void setDeathsChamps(int deathsChamps) {
        Trackers.deathsChamps = deathsChamps;
    }

    public static void setDeathsInquis(int deathsInquis) {
        Trackers.deathsInquis = deathsInquis;
    }

}