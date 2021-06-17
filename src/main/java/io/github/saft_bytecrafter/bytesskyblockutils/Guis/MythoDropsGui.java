package io.github.saft_bytecrafter.bytesskyblockutils.Guis;

import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;

public class MythoDropsGui extends DropStatsGui {

    private static final String file = ConfigHandler.getLootTrackingFile();
    private static final String tabName = "mythological trackers";

    private int startBurrows;
    private int midBurrows;
    private int endBurrows;
    private float sumBurrows;

    private float hunters;
    private float lynxes;
    private float minotaurs;
    private float gaias;
    private float champs;
    private float inquis;
    private float totalMobs;

    private int deathsHunter;
    private int deathsLynx;
    private int deathsMinotaurs;
    private int deathsGaias;
    private int deathsChamp;
    private int deathsInquis;
    private int totalDeaths;

    private int coins;
    private int feathers;
    private int cog;
    private int washedUp;
    private float totalTreasures;

    private int gremdis;
    private int crochets;
    private int shelms;
    private int sticks;
    private int relics;
    private int chimeras;

    private int claws;
    private int enchClaws;
    private int gold;
    private int iron;

//TODO other stat for coins of mobs (scavenger or smth idk)
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        //get the values out of config
        startBurrows = ConfigHandler.getInt(file, tabName, "Start Burrows");
        midBurrows = ConfigHandler.getInt(file, tabName, "Mid Burrows");
        endBurrows = ConfigHandler.getInt(file, tabName, "End Burrows");
        sumBurrows = startBurrows + midBurrows + endBurrows;

        hunters = ConfigHandler.getInt(file, tabName, "Minos Hunters");
        lynxes = ConfigHandler.getInt(file, tabName, "Siamese Lynxes");
        minotaurs = ConfigHandler.getInt(file, tabName, "Minotaurs");
        gaias = ConfigHandler.getInt(file, tabName, "Gaia Constructs");
        champs = ConfigHandler.getInt(file, tabName, "Minos Champions");
        inquis = ConfigHandler.getInt(file, tabName, "Minos Inquisitors");
        totalMobs = hunters + lynxes + minotaurs + gaias + champs + inquis;

        deathsHunter = ConfigHandler.getInt(file, tabName, "Deaths Minos Hunters");
        deathsLynx = ConfigHandler.getInt(file, tabName, "Deaths Siames Lynxes");
        deathsMinotaurs = ConfigHandler.getInt(file, tabName, "Deaths Minotaurs");
        deathsGaias = ConfigHandler.getInt(file, tabName, "Deaths Gaia Constructs");
        deathsChamp = ConfigHandler.getInt(file, tabName, "Deaths Minos Champions");
        deathsInquis = ConfigHandler.getInt(file, tabName, "Deaths Minos Inquisitors");
        totalDeaths = deathsLynx + deathsMinotaurs + deathsGaias + deathsChamp + deathsInquis; //not including hunter cause not needed

        coins = ConfigHandler.getInt(file, tabName, "Coins");
        feathers = ConfigHandler.getInt(file, tabName, "Griffin Feathers");
        cog = ConfigHandler.getInt(file, tabName, "Crowns of Greed");
        washedUp = ConfigHandler.getInt(file, tabName, "Washed up Souvenirs");
        totalTreasures = endBurrows+(midBurrows-totalMobs);

        gremdis = ConfigHandler.getInt(file, tabName, "Antique Remedies");
        crochets = ConfigHandler.getInt(file, tabName, "Crochet Tiger Plushies");
        shelms = ConfigHandler.getInt(file, tabName, "Dwarf Turtle Shelmets");
        sticks = ConfigHandler.getInt(file, tabName, "Daedalus Sticks");
        relics = ConfigHandler.getInt(file, tabName, "Minos Relics");
        chimeras = ConfigHandler.getInt(file, tabName, "Chimeras");

        claws = ConfigHandler.getInt(file, tabName, "Ancient Claw");
        enchClaws = ConfigHandler.getInt(file, tabName, "Enchanted Ancient Claw");
        gold = ConfigHandler.getInt(file, tabName, "Enchanted Gold");
        iron = ConfigHandler.getInt(file, tabName, "Enchanted Iron");

        //create the strings to render
        String startingBurrows = sumBurrows != 0 ? midBurrows != 0 ? "Starting Burrows: " + startBurrows + " -> " + String.valueOf((startBurrows/sumBurrows)*100).substring(0, 4) + "%" : "not start burrows yet" : "no burrows yet";
        String middleBurrows = sumBurrows != 0 ? midBurrows != 0 ? "Middle Burrows: " + midBurrows + " -> " + String.valueOf((midBurrows/sumBurrows)*100).substring(0, 4) + "%" : "no middle burrows yet" : "no burrows yet";
        String middleMobBurrows = midBurrows != 0 ? totalMobs != 0 ? "Mob Burrows: " + (int) (totalMobs) + " -> " + String.valueOf((totalMobs/midBurrows)*100).substring(0, 4) + "%" : "no mobs killed yet" : "no middle burrows yet";
        String endingBurrows = sumBurrows != 0 ? endBurrows != 0 ? "Ending Burrows: " + endBurrows + " -> " + String.valueOf((endBurrows/sumBurrows)*100).substring(0, 4) + "%" : "no ending burrows yet" : "no burrows yet";
        String allBurrows = sumBurrows != 0 ? "All Burrows: " + (int) (sumBurrows) : "no burrows yet";

        String huntersString = totalMobs != 0 ? hunters != 0 ? "Minos Hunters: " + (int) (hunters) + " -> " + String.valueOf((hunters/totalMobs)*100).substring(0, 4) + "%" : "no Hunters killed yet" : "no mobs killed yet";
        String deathsHuntersString = hunters != 0 ? deathsHunter != 0 ? "Deaths Minos Hunter: " + deathsHunter + " -> " + String.valueOf((deathsHunter/hunters)*100).substring(0, 4) + "%" : "no deaths to Hunters yet" : "no Hunters killed yet";
        String lynxesString = totalMobs != 0 ? lynxes != 0 ? "Siamese Lynxes: " + (int) (lynxes) + " -> " + String.valueOf((lynxes/totalMobs)*100).substring(0, 4) + "%" : "no Lynxes killed yet" : "no mobs killed yet";
        String deathsLynxesString = lynxes != 0 ? deathsLynx != 0 ? "Deaths Siamese Lynxe: " + deathsLynx + " -> " + String.valueOf((deathsLynx/lynxes)*100).substring(0, 4) + "%" : "no deaths to Lynxes yet" : "no Lynxes killed yet";
        String minotaursString = totalMobs != 0 ? minotaurs != 0 ? "Minotaurs: " + (int) (minotaurs) + " -> " + String.valueOf((minotaurs/totalMobs)*100).substring(0, 4) + "%" : "no Minotaurs killed yet" : "no mobs killed yet";
        String deathsMinotaursString = minotaurs != 0 ? deathsMinotaurs != 0 ? "Deaths Minotaur: " + deathsMinotaurs + " -> " + String.valueOf((deathsMinotaurs/minotaurs)*100).substring(0, 4) + "%" : "no deaths to Minotaurs yet" : "no Minotaurs killed yet";
        String gaiasString = totalMobs != 0 ? gaias != 0 ? "Gaia Constructs: " + (int) (gaias) + " -> " + String.valueOf((gaias/totalMobs)*100).substring(0, 4) + "%" : "no Gaias killed yet" : "no mobs killed yet";
        String deathsGaiasString = gaias != 0 ? deathsGaias != 0 ? "Deaths Gaia Construct: " + deathsGaias + " -> " + String.valueOf((deathsGaias/gaias)*100).substring(0, 4) + "%" : "no deaths to Gaias yet" : "no Gaias killed yet";
        String champsString = totalMobs != 0 ? champs != 0 ? "Minos Champions: " + (int) (champs) + " -> " + String.valueOf((champs/totalMobs)*100).substring(0, 4) + "%" : "no Champions killed yet" : "no mobs killed yet";
        String deathsChampsString = champs != 0 ? deathsChamp != 0 ? "Deaths Minos Champion: " + deathsChamp + " -> " + String.valueOf((deathsChamp/champs)*100).substring(0, 4) + "%" : "no deaths to Champions yet" : "no Champions killed yet";
        String inquisString = totalMobs != 0 ? inquis != 0 ? "Minos Inquisitors: " + (int) (inquis) + " -> " + String.valueOf((inquis/totalMobs)*100).substring(0, 4) + "%" : "no Inquis killed yet" : "no mobs killed yet";
        String deathsInquisString = inquis != 0 ? deathsInquis != 0 ? "Deaths Minos Inquisitor: " + deathsInquis + " -> " + String.valueOf((deathsInquis/inquis)*100).substring(0, 4) + "%" : "no deaths to Inquis yet" : "no Inquis killed yet";

        String coinsString = totalTreasures != 0 ? coins != 0 ? "Coins: " + coins + " -> " + String.valueOf(((totalTreasures-(feathers+cog+washedUp))/totalTreasures)*100).substring(0, 4) + "%" : "no coins yet" : "no Treasures dug yet";
        String averageCoins = totalTreasures != 0 ? coins != 0 ? "Average Coins: " + coins/(totalTreasures-(feathers+cog+washedUp)) + " Coins/Coin-Burrow" : "no coins yet" : "no Treasures dug yet";
        String feathersString = totalTreasures != 0 ? feathers != 0 ? "Griffin Feathers: " + feathers + " -> " + String.valueOf((feathers/totalTreasures)*100).substring(0, 4) + "%" : "no Griffin Feathers yet" : "no Treasures dug yet";
        String cogString = totalTreasures != 0 ? cog != 0 ? "Crowns of Greed: " + cog + " -> " + String.valueOf((cog/totalTreasures)*100).substring(0, 4) + "%" : "no Crowns of Greed yet" : "no Treasures dug yet";
        String washedUpString = totalTreasures != 0 ? washedUp != 0 ? "Washed up Souvenirs: " + washedUp + " -> " + String.valueOf((washedUp/totalTreasures)*100).substring(0, 4) + "%" : "no Washed up Souvenirs yet" : "no Treasures dug yet";

        String gremediesString = totalMobs != 0 ? gremdis != 0 ? "Antique Remedies: " + gremdis + " -> " + String.valueOf(gremdis/(totalMobs-hunters-totalDeaths+lynxes-deathsLynx /* so that lynxes are counted twice*/ )*100).substring(0, 4) + "%" : "no Remedies dropped yet" : "no Mobs killed yet";
        String crochetsString = totalMobs != 0 ? crochets != 0 ? "Tiger Crochet Plushies: " + crochets + " -> " + String.valueOf(crochets/(totalMobs-hunters-totalDeaths+lynxes-deathsLynx /* so that lynxes are counted twice*/ )*100).substring(0, 4) + "%" : "no Crochets dropped yet" : "no Mobs killed yet";
        String shelmsString = totalMobs != 0 ? shelms != 0 ? "Dwarf Turtle Shelmets: " + shelms + " -> " + String.valueOf(shelms/(totalMobs-hunters-totalDeaths+lynxes-deathsLynx /* so that lynxes are counted twice*/ )*100).substring(0, 4) + "%" : "no Shelmets dropped yet" : "no Mobs killed yet";
        String sticksString = minotaurs != 0 ? sticks != 0 ? "Daedalus Sticks: " + sticks + " -> " + String.valueOf((sticks/(minotaurs-deathsMinotaurs))*100).substring(0, 4) + "%" : "no Sticks dropped yet" : "no Minotaurs killed yet";
        String relicsString = champs != 0 || inquis != 0 ? relics != 0 ? "Minos Relics: " + relics + " -> " + String.valueOf(relics/(champs+inquis-deathsChamp-deathsInquis)*100).substring(0, 4) + "%" : "no Relics dropped yet" : "no Champions or Inquis killed yet";
        String chimerasString = inquis != 0 ? chimeras != 0 ? "Chimeras: " + chimeras + " -> " + String.valueOf((chimeras/(inquis-deathsInquis))*100).substring(0, 4) + "%" : "no Chimeras dropped yet" : "no Inquis killed yet";

        String clawsString = totalMobs != 0 ? claws != 0 ? "Ancient Claws: " + claws + " -> " + (String.valueOf(claws/(totalMobs+lynxes-totalDeaths-deathsHunter-deathsLynx)).length() < 6 ? claws/(totalMobs-totalDeaths-deathsHunter) : String.valueOf(claws/(totalMobs-totalDeaths-deathsHunter)).substring(0, 5)) + " Claws/Mob" : "no Claws dropped yet" : "no Mobs killed yet";
        String enchClawsString = inquis != 0 ? enchClaws != 0 ? "Enchanted Ancient Claws: " + enchClaws + " -> " + (String.valueOf(enchClaws/(inquis-deathsInquis)).length() < 5 ? enchClaws/(inquis-deathsInquis) : String.valueOf(enchClaws/(inquis-deathsInquis)).substring(0, 4)) + " E. Claws/Inquis" : "no Enchanted Claws dropped yet" : "no Inquis killed yet";
        String goldString = totalMobs != 0 ? gold != 0 ? "Enchanted Gold: " + gold + " -> " + (String.valueOf(gold/(totalMobs+lynxes-totalDeaths-deathsHunter-deathsLynx)).length() < 5 ? gold/(totalMobs-totalDeaths-deathsHunter) : String.valueOf(gold/(totalMobs-totalDeaths-deathsHunter)).substring(0, 4)) + " Gold/Mob" : "no Enchanted Gold yet" : "no Mobs killed yet";
        String ironString = gaias != 0 ? iron != 0 ? "Enchanted Iron: " + iron + " -> " + (String.valueOf(iron/(gaias-deathsGaias)).length() < 5 ? iron/(gaias-deathsGaias) : String.valueOf(iron/(gaias-deathsGaias)).substring(0, 4)) + " Iron/Gaia" : "no Enchanted Iron yet" : "no Gaias killed yet";



        //render the strings
        drawString(fontRendererObj, startingBurrows, width/8*3, height/20*2,  0xFFFFFF);
        drawString(fontRendererObj, middleBurrows, width/8*3, height/20*3,  0xFFFFFF);
        drawString(fontRendererObj, middleMobBurrows, width/8*3+20, height/20*4,  0xFFFFFF);
        drawString(fontRendererObj, endingBurrows, width/8*3, height/20*5,  0xFFFFFF);
        drawString(fontRendererObj, allBurrows, width/8*3, height/20*6,  0xFFFFFF);

        drawString(fontRendererObj, huntersString, width/8*3, height/20*8,  0xFFFFFF);
        drawString(fontRendererObj, deathsHuntersString, width/8*3+20, height/20*9,  0xFFFFFF);
        drawString(fontRendererObj, lynxesString, width/8*3, height/20*10,  0xFFFFFF);
        drawString(fontRendererObj, deathsLynxesString, width/8*3+20, height/20*11,  0xFFFFFF);
        drawString(fontRendererObj, minotaursString, width/8*3, height/20*12,  0xFFFFFF);
        drawString(fontRendererObj, deathsMinotaursString, width/8*3+20, height/20*13,  0xFFFFFF);
        drawString(fontRendererObj, gaiasString, width/8*3, height/20*14,  0xFFFFFF);
        drawString(fontRendererObj, deathsGaiasString, width/8*3+20, height/20*15,  0xFFFFFF);
        drawString(fontRendererObj, champsString, width/8*3, height/20*16,  0xFFFFFF);
        drawString(fontRendererObj, deathsChampsString, width/8*3+20, height/20*17,  0xFFFFFF);
        drawString(fontRendererObj, inquisString, width/8*3, height/20*18,  0xFFFFFF);
        drawString(fontRendererObj, deathsInquisString, width/8*3+20, height/20*19,  0xFFFFFF);

        drawString(fontRendererObj, coinsString, (int) (width/8*5.4), height/20*2,  0xFFFFFF);
        drawString(fontRendererObj, averageCoins, (int) (width/8*5.4+20), height/20*3,  0xFFFFFF);
        drawString(fontRendererObj, feathersString, (int) (width/8*5.4), height/20*4,  0xFFFFFF);
        drawString(fontRendererObj, cogString, (int) (width/8*5.4), height/20*5,  0xFFFFFF);
        drawString(fontRendererObj, washedUpString, (int) (width/8*5.4), height/20*6,  0xFFFFFF);

        drawString(fontRendererObj, gremediesString, (int) (width/8*5.4), height/20*8,  0xFFFFFF);
        drawString(fontRendererObj, crochetsString, (int) (width/8*5.4), height/20*9,  0xFFFFFF);
        drawString(fontRendererObj, shelmsString, (int) (width/8*5.4), height/20*10,  0xFFFFFF);
        drawString(fontRendererObj, sticksString, (int) (width/8*5.4), height/20*11,  0xFFFFFF);
        drawString(fontRendererObj, relicsString, (int) (width/8*5.4), height/20*12,  0xFFFFFF);
        drawString(fontRendererObj, chimerasString, (int) (width/8*5.4), height/20*13,  0xFFFFFF);

        drawString(fontRendererObj, clawsString, (int) (width/8*5.4), height/20*15,  0xFFFFFF);
        drawString(fontRendererObj, enchClawsString, (int) (width/8*5.4), height/20*16,  0xFFFFFF);
        drawString(fontRendererObj, goldString, (int) (width/8*5.4), height/20*17,  0xFFFFFF);
        drawString(fontRendererObj, ironString, (int) (width/8*5.4), height/20*18,  0xFFFFFF);
    }
}
