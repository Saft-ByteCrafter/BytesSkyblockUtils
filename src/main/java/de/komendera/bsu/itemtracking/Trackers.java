package de.komendera.bsu.itemtracking;

import de.komendera.bsu.ConfigHandler;
import de.komendera.bsu.BSUMain;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
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
    private static int lynxes;
    private static int minotaurs;
    private static int gaias;
    private static int champs;
    private static int inquis;
    private static int coins;
    private static int feathers;
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
    private List<DifferentItems> differentItems;

//    private SBUMain main = SBUMain.getInstance();

    //§6§lRARE DROP! §5Antique Remedies
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChat(ClientChatReceivedEvent event){

        String chatMessage = StringUtils.stripControlCodes(event.message.getUnformattedText());
        //       System.out.println(chatMessage);
        if(chatMessage.contains(":")){
            return;
 /*           SBUMain.compareInventories.getNewItems(Minecraft.getMinecraft().thePlayer.inventory.mainInventory);
            differentItems = SBUMain.compareInventories.getDifferentItems();
            for(DifferentItems items: differentItems){
                if(!(items == null)) System.out.println(items.getItemName() + " + " + items.getItemAmount());
            } //testcode to test if it works
 */      } else if(chatMessage.startsWith("You dug out a Griffin Burrow! (1/4)")){
            startBurrows++;
            BSUMain.compareInventories.getNewItems(Minecraft.getMinecraft().thePlayer.inventory.mainInventory); //wird am anfang (jeder chain :/) gemacht, damit das default inv geladen wird TODO so machen, dass es nich am anfang jeder chain gemacht wird
            ConfigHandler.writeIntConfig("mythological trackers", "Start Burrows", startBurrows);
 //           Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("first burrow")); //test output TODO remove maybe
        } else if(chatMessage.startsWith("You dug out a Griffin Burrow! (2/4)") || chatMessage.startsWith("You dug out a Griffin Burrow! (3/4)")){
            midBurrows++;
            ConfigHandler.writeIntConfig("mythological trackers", "Mid Burrows", midBurrows);
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
            } //^the picke up items are added to the variables
            ConfigHandler.writeIntConfig("mythological trackers", "Enchanted Gold", gold);
            ConfigHandler.writeIntConfig("mythological trackers", "Enchanted Iron", iron);
            ConfigHandler.writeIntConfig("mythological trackers", "Enchanted Ancient Claw", enchClaws);
            ConfigHandler.writeIntConfig("mythological trackers", "Ancient Claw", claws); //the variables are passed to the cfg
 //            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("1:" + claws)); //test output TODO remove maybe
        } else if(chatMessage.startsWith("You dug out a Griffin Burrow! (4/4)")){
            endBurrows++;
            ConfigHandler.writeIntConfig("mythological trackers", "End Burrows" , endBurrows);
 //           Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("fourth burrow")); //test output TODO remove maybe
        } else if(chatMessage.contains("Minos Hunter")){
            hunters++;
            ConfigHandler.writeIntConfig("mythological trackers", "Minos Hunters", hunters);
        } else if(chatMessage.contains("Siamese Lynxes")){
            lynxes++;
            ConfigHandler.writeIntConfig("mythological trackers", "Siamese Lynxes", lynxes);
        } else if(chatMessage.contains("Minotaur")){
            minotaurs++;
            ConfigHandler.writeIntConfig("mythological trackers", "Minotaurs", minotaurs);
        } else if(chatMessage.contains("Gaia Construct")){
            gaias++;
            ConfigHandler.writeIntConfig("mythological trackers", "Gaia Constructs", gaias);
        } else if(chatMessage.contains("Minos Champion")){
            champs++;
            ConfigHandler.writeIntConfig("mythological trackers", "Minos Champions", champs);
        } else if(chatMessage.contains("Actually, you dug up a ")){//TODO this kinda does not work
            inquis++;
            ConfigHandler.writeIntConfig("mythological trackers", "Minos Inquisitors", inquis);
            champs--;
            ConfigHandler.writeIntConfig("mythological trackers", "Minos Champions", champs);
        } else if(chatMessage.contains("You dug out ") && chatMessage.contains("coins!")){
            String[] cutChatMessage = chatMessage.split(" ");
            int amount = Integer.valueOf(cutChatMessage[4].replace(",", ""));
            coins += amount;
            ConfigHandler.writeIntConfig("mythological trackers", "Coins", coins);
        } else if(chatMessage.contains("Griffin Feather")){
            feathers++;
            ConfigHandler.writeIntConfig("mythological trackers", "Griffin Feathers", feathers);
        } else if(chatMessage.contains("Antique Remedies")){
            remedis++;
            ConfigHandler.writeIntConfig("mythological trackers", "Antique Remedies", remedis);
        } else if(chatMessage.contains("Crochet Tiger Plushie")){
            crochets++;
            ConfigHandler.writeIntConfig("mythological trackers", "Crochet Tiger Plushies", crochets);
        } else if(chatMessage.contains("Dwarf Turtle Shelmet")){
            shelms++;
            ConfigHandler.writeIntConfig("mythological trackers", "Dwarf Turtle Shelmets", shelms);
        } else if(chatMessage.contains("Daedalus Stick")){
            sticks++;
            ConfigHandler.writeIntConfig("mythological trackers", "Daedalus Sticks", sticks);
        } else if(chatMessage.contains("Minos Relic")){
            relics++;
            ConfigHandler.writeIntConfig("mythological trackers", "Minos Relics", relics);
        } else if(chatMessage.contains("Chimera I")){
            chimeras++;
            ConfigHandler.writeIntConfig("mythological trackers", "Chimeras", chimeras);
        }
    }



    public int getStartBurrows() {
        return startBurrows;
    }

    public static void setStartBurrows(int startBurrows) {
        startBurrows = startBurrows;
    }

    public static int getMidBurrows() {
        return midBurrows;
    }

    public static void setMidBurrows(int midBurrows) {
        Trackers.midBurrows = midBurrows;
    }

    public static int getEndBurrows() {
        return endBurrows;
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

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        Trackers.coins = coins;
    }

    public static int getFeathers() {
        return feathers;
    }

    public static void setFeathers(int feathers) {
        Trackers.feathers = feathers;
    }

    public static int getRemedis() {
        return remedis;
    }

    public static void setRemedis(int remedis) {
        Trackers.remedis = remedis;
    }

    public static int getCrochets() {
        return crochets;
    }

    public static void setCrochets(int crochets) {
        Trackers.crochets = crochets;
    }

    public static int getShelms() {
        return shelms;
    }

    public static void setShelms(int shelms) {
        Trackers.shelms = shelms;
    }

    public static int getSticks() {
        return sticks;
    }

    public static void setSticks(int sticks) {
        Trackers.sticks = sticks;
    }

    public static int getRelics() {
        return relics;
    }

    public static void setRelics(int relics) {
        Trackers.relics = relics;
    }

    public static int getChimeras() {
        return chimeras;
    }

    public static void setChimeras(int chimeras) {
        Trackers.chimeras = chimeras;
    }

    public static int getGold() {
        return gold;
    }

    public static void setGold(int gold) {
        Trackers.gold = gold;
    }

    public static int getIron() {
        return iron;
    }

    public static void setIron(int iron) {
        Trackers.iron = iron;
    }

    public static int getEnchClaws() {
        return enchClaws;
    }

    public static void setEnchClaws(int enchClaws) {
        Trackers.enchClaws = enchClaws;
    }

    public static int getClaws() {
        return claws;
    }

    public static void setClaws(int claws) {
        Trackers.claws = claws;
    }

}