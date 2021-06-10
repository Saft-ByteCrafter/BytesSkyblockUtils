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

        //create the strings to render
        String startingBurrows = sumBurrows != 0 ? midBurrows != 0 ? "Starting Burrows: " + startBurrows + " " + String.valueOf((startBurrows/sumBurrows)*100).substring(0, 4) + "%" : "not start burrows yet" : "no burrows yet";
        String middleBurrows = sumBurrows != 0 ? midBurrows != 0 ? "Middle Burrows: " + midBurrows + " " + String.valueOf((midBurrows/sumBurrows)*100).substring(0, 4) + "%" : "no middle burrows yet" : "no burrows yet";
        String middleMobBurrows = midBurrows != 0 ? totalMobs != 0 ? "Mob Burrows: " + totalMobs + " " + String.valueOf((totalMobs/midBurrows)*100).substring(0, 4) + "%" : "no mobs killed yet" : "no middle burrows yet";
        String endingBurrows = sumBurrows != 0 ? endBurrows != 0 ? "Ending Burrows: " + endBurrows + " " + String.valueOf((endBurrows/sumBurrows)*100).substring(0, 4) + "%" : "no ending burrows yet" : "no burrows yet";
        String allBurrows = sumBurrows != 0 ? "All Burrows: " + sumBurrows + " " + String.valueOf((sumBurrows/sumBurrows)*100).substring(0, 4) + "%" : "no burrows yet";
        String huntersString = totalMobs != 0 ? hunters != 0 ? "Minos Hunters: " + hunters + " " + String.valueOf((hunters/totalMobs)*100).substring(0, 4) + "%" : "no Hunters killed yet" : "no mobs killed yet";
        String deathsHuntersString = hunters != 0 ? deathsHunter != 0 ? "Deaths Minos Hunter: " + deathsHunter + " " + String.valueOf((deathsHunter/hunters)*100).substring(0, 4) + "%" : "no deaths to Hunters yet" : "no Hunters killed yet";
        String lynxesString = totalMobs != 0 ? lynxes != 0 ? "Siamese Lynxes: " + lynxes + " " + String.valueOf((lynxes/totalMobs)*100).substring(0, 4) + "%" : "no Lynxes killed yet" : "no mobs killed yet";
        String deathsLynxesString = lynxes != 0 ? deathsLynx != 0 ? "Deaths Siamese Lynxe: " + deathsLynx + " " + String.valueOf((deathsLynx/lynxes)*100).substring(0, 4) + "%" : "no deaths to Lynxes yet" : "no Lynxes killed yet";
        String minotaursString = totalMobs != 0 ? minotaurs != 0 ? "Minotaurs: " + minotaurs + " " + String.valueOf((minotaurs/totalMobs)*100).substring(0, 4) + "%" : "no Minotaurs killed yet" : "no mobs killed yet";
        String deathsMinotaursString = minotaurs != 0 ? deathsMinotaurs != 0 ? "Deaths Minotaur: " + deathsMinotaurs + " " + String.valueOf((deathsMinotaurs/minotaurs)*100).substring(0, 4) + "%" : "no deaths to Minotaurs yet" : "no Minotaurs killed yet";
        String gaiasString = totalMobs != 0 ? gaias != 0 ? "Gaia Constructs: " + gaias + " " + String.valueOf((gaias/totalMobs)*100).substring(0, 4) + "%" : "no Gaias killed yet" : "no mobs killed yet";
        String deathsGaiasString = gaias != 0 ? deathsGaias != 0 ? "Deaths Gaia Construct: " + deathsGaias + " " + String.valueOf((deathsGaias/gaias)*100).substring(0, 4) + "%" : "no deaths to Gaias yet" : "no Gaias killed yet";
        String champsString = totalMobs != 0 ? champs != 0 ? "Minos Champions: " + champs + " " + String.valueOf((champs/totalMobs)*100).substring(0, 4) + "%" : "no Champions killed yet" : "no mobs killed yet";
        String deathsChampsString = champs != 0 ? deathsChamp != 0 ? "Deaths Minos Champion: " + deathsChamp + " " + String.valueOf((deathsChamp/champs)*100).substring(0, 4) + "%" : "no deaths to Champions yet" : "no Champions killed yet";
        String inquisString = totalMobs != 0 ? inquis != 0 ? "Minos Inquisitors: " + inquis + " " + String.valueOf((inquis/totalMobs)*100).substring(0, 4) + "%" : "no Inquis killed yet" : "no mobs killed yet";
        String deathsInquisString = inquis != 0 ? deathsInquis != 0 ? "Deaths Minos Inquisitor: " + deathsInquis + " " + String.valueOf((deathsInquis/inquis)*100).substring(0, 4) + "%" : "no deaths to Inquis yet" : "no Inquis killed yet";

        //render the strings
        //       drawCenteredString(fontRendererObj, middleBurrows, width/8*6, height/20*3,  0xFFFFFF);
        //       drawCenteredString(fontRendererObj, middleBurrows, width/8*6, height/20*2,  0xFFFFFF);
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
    }
}
