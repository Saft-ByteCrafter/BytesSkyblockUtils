package io.github.saft_bytecrafter.bytesskyblockutils.Guis;

import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;

public class MythoDropsGui extends DropStatsGui {

    private static final String file = ConfigHandler.getLootTrackingFile();
    private static final String tabName = "mythological trackers";

    private int startingBurrows;
    private int midBurrows;
    private int endBurrows;
    private float sumBurrows;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        startingBurrows = ConfigHandler.getInt(file, tabName, "Start Burrows");
        midBurrows = ConfigHandler.getInt(file, tabName, "Mid Burrows");
        endBurrows = ConfigHandler.getInt(file, tabName, "End Burrows");
        sumBurrows = startingBurrows + midBurrows + endBurrows;
        String startBurrows = sumBurrows != 0 ? "Starting Burrows: " + startingBurrows +" " + String.valueOf((startingBurrows/sumBurrows)*100).substring(0, 4) + "%" : "no burrows yet";
        String middleBurrows = sumBurrows != 0 ? midBurrows != 0 ?"Middle Burrows: " + midBurrows + " " + String.valueOf((midBurrows/sumBurrows)*100).substring(0, 4) + "%" : "no middle burrows yet" : "no burrows yet";

        drawCenteredString(fontRendererObj, startBurrows, width/4*3, height/4,  0xFFFFFF);
        drawCenteredString(fontRendererObj, middleBurrows, width/4*3, height/4,  0xFFFFFF);
    }
}
