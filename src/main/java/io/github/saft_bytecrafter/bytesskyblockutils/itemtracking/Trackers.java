package io.github.saft_bytecrafter.bytesskyblockutils.itemtracking;

import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.OnOffConfigs;
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
    private static final String file =  ConfigHandler.getLootTrackingFile();

    private static CompareInventories compInvInstance;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChat(ClientChatReceivedEvent event){

        String chatMessage = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if(chatMessage.contains(":")) return;
        if(OnOffConfigs.getMythoTracker() == 1){
            if(chatMessage.startsWith("You dug out a Griffin Burrow! (1/4)")){
                startBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "Start Burrows", startBurrows);
            } else if(chatMessage.startsWith("You dug out a Griffin Burrow! (2/4)") || chatMessage.startsWith("You dug out a Griffin Burrow! (3/4)")){
                midBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "Mid Burrows", midBurrows);
                if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.inventory.mainInventory != null) compInvInstance.getNewItems(Minecraft.getMinecraft().thePlayer.inventory.mainInventory);
                differentItems = compInvInstance.getDifferentItems();
                for(DifferentItems item: differentItems){
                    if(item.getItemName().contains("Enchanted Gold") && item.getItemAmount() > 0){
                        gold += item.getItemAmount();
                        ConfigHandler.writeIntConfig(file, mytho, "Enchanted Gold", gold);
                    }
                    else if(item.getItemName().contains("Enchanted Iron") && item.getItemAmount() > 0){
                        iron += item.getItemAmount();
                        ConfigHandler.writeIntConfig(file, mytho, "Enchanted Iron", iron);
                    }
                    else if(item.getItemName().contains("Enchanted Ancient Claw") && item.getItemAmount() > 0){
                        enchClaws += item.getItemAmount();
                        ConfigHandler.writeIntConfig(file, mytho, "Enchanted Ancient Claw", enchClaws);
                    }
                    else if(item.getItemName().contains("Ancient Claw") && item.getItemAmount() > 0){
                        claws += item.getItemAmount();
                        ConfigHandler.writeIntConfig(file, mytho, "Ancient Claw", claws);
                    }
                    else if(item.getItemName().contains("Antique Remedies")){
                        remedis++;
                        ConfigHandler.writeIntConfig(file, mytho, "Antique Remedies", remedis);
                    }
                    else if(item.getItemName().contains("Crochet Tiger Plushie")){
                        crochets++;
                        ConfigHandler.writeIntConfig(file, mytho, "Crochet Tiger Plushies", crochets);
                    }
                    else if(item.getItemName().contains("Dwarf Turtle Shelmet")){
                        shelms++;
                        ConfigHandler.writeIntConfig(file, mytho, "Dwarf Turtle Shelmets", shelms);
                    }
                    else if(item.getItemName().contains("Daedalus Stick")){
                        sticks++;
                        ConfigHandler.writeIntConfig(file, mytho, "Daedalus Sticks", sticks);
                    }
                    else if(item.getItemName().contains("Minos Relic")){
                        relics++;
                        ConfigHandler.writeIntConfig(file, mytho, "Minos Relics", relics);
                    }
                    else if(item.getItemName().contains("Enchanted Book")){
                        chimeras++;
                        ConfigHandler.writeIntConfig(file, mytho, "Chimeras", chimeras);
                    }
                } //^the picked up items are added to the variables
            } else if(chatMessage.contains(/*"You finished the Griffin burrow chain! */"(4/4)")){// I have no hecking clue why the message before the 4/4 is not recognized, i literally copy-pasted it (maybe some spaces idfk)
                endBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "End Burrows" , endBurrows);
            } else if(chatMessage.contains("You dug out")) {
                if (chatMessage.contains("Minos Hunter")) {
                    hunters++;
                    ConfigHandler.writeIntConfig(file, mytho, "Minos Hunters", hunters);
                } else if (chatMessage.contains("Siamese Lynxes")) {
                    lynxes++;
                    ConfigHandler.writeIntConfig(file, mytho, "Siamese Lynxes", lynxes);
                } else if (chatMessage.contains("Minotaur")) {
                    minotaurs++;
                    ConfigHandler.writeIntConfig(file, mytho, "Minotaurs", minotaurs);
                } else if (chatMessage.contains("Gaia Construct")) {
                    gaias++;
                    ConfigHandler.writeIntConfig(file, mytho, "Gaia Constructs", gaias);
                } else if (chatMessage.contains("Minos Champion")) {
                    champs++;
                    ConfigHandler.writeIntConfig(file, mytho, "Minos Champions", champs);
                } else if (chatMessage.contains("Minos Inquisitor")) {
                    inquis++; //doesn't happen rn cause hypixel bad and they send the same msg for inquis as for champs, thats why the code under this exists
                    ConfigHandler.writeIntConfig(file, mytho, "Minos Inquisitors", inquis);
                } else if(chatMessage.contains("Griffin Feather")){
                    feathers++;
                    ConfigHandler.writeIntConfig(file, mytho, "Griffin Feathers", feathers);
                } else if(chatMessage.contains("Crown of Greed")){
                    cogs++;
                    ConfigHandler.writeIntConfig(file, mytho, "Crowns of Greed", cogs);
                } else if(chatMessage.contains("Washed-up")){
                    washedUps++;
                    ConfigHandler.writeIntConfig(file, mytho, "Washed up Souvenirs", washedUps);
                }else if(chatMessage.contains("You dug out ") && chatMessage.contains("coins!")){
                    String[] cutChatMessage = chatMessage.split(" ");
                    int amount = Integer.valueOf(cutChatMessage[4].replace(",", ""));
                    coins += amount;
                    ConfigHandler.writeIntConfig(file, mytho, "Coins", coins);
                }
            }
              else if(chatMessage.contains("You were killed by") && chatMessage.contains("Minos Hunter")){
                deathsHunters++;
                midBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "Deaths Minos Hunters", deathsHunters);
                ConfigHandler.writeIntConfig(file, mytho, "Mid Burrows", midBurrows);
            } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Siamese")){
                deathsLynxes++;
                midBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "Deaths Siames Lynxes", deathsLynxes);
                ConfigHandler.writeIntConfig(file, mytho, "Mid Burrows", midBurrows);
            } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Minotaur")){
                deathsMinotaurs++;
                midBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "Deaths Minotaurs", deathsMinotaurs);
                ConfigHandler.writeIntConfig(file, mytho, "Mid Burrows", midBurrows);
            } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Gaia Construct")){
                deathsGaias++;
                midBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "Deaths Gaia Constructs", deathsGaias);
                ConfigHandler.writeIntConfig(file, mytho, "Mid Burrows", midBurrows);
            } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Minos Champion")){
                deathsChamps++;
                midBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "Deaths Minos Champions", deathsChamps);
                ConfigHandler.writeIntConfig(file, mytho, "Mid Burrows", midBurrows);
            } else if(chatMessage.contains("You were killed by") && chatMessage.contains("Minos Inquisitor")){
                deathsInquis++;
                midBurrows++;
                ConfigHandler.writeIntConfig(file, mytho, "Deaths Minos Inquisitors", deathsInquis);
                ConfigHandler.writeIntConfig(file, mytho, "Mid Burrows", midBurrows);
            }
        }
    }

    public static void makeNewCompareInvInstance(){
        compInvInstance = null;
        compInvInstance = new CompareInventories();
        if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.inventory.mainInventory != null) compInvInstance.getNewInventory(Minecraft.getMinecraft().thePlayer.inventory.mainInventory);
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