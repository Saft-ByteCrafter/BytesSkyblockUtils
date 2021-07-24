package io.github.saft_bytecrafter.bytesskyblockutils.guis;

import io.github.saft_bytecrafter.bytesskyblockutils.Utils;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.OnOffConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ConfigGUI extends GuiScreen {

    private static final String file = ConfigHandler.getModConfigFile();

    //basic buttons
//    private GuiButton next;
//    private GuiButton back;
    private GuiButton gitHub;

    //trackers
    private GuiButton mythologicalTracker;

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();

        ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());
        int height = scaledRes.getScaledHeight();
        int width = scaledRes.getScaledWidth();
        int normalButtonWidth = 210;
        int buttonHeight = 20;

//        next = new GuiButton(0, width/2 + 20, (int) (height * 0.8), (normalButtonWidth-40)/2, buttonHeight, "Next ->");
//        back = new GuiButton(0, width/2 - 100, (int) (height*0.8), (normalButtonWidth-40)/2, buttonHeight, "<- Back");
        gitHub = new GuiButton(0, 5, height - buttonHeight - 5, (normalButtonWidth - 40) / 2, buttonHeight, "GitHub");

        mythologicalTracker = new GuiButton(0, width / 2 - normalButtonWidth / 2, height / 2 - buttonHeight / 2, normalButtonWidth, buttonHeight, "Toggle the Mythological-Tracker: " + Utils.getColoredBool(OnOffConfigs.getMythoTracker() == 1));

//        this.buttonList.add(next);
//        this.buttonList.add(back);
        this.buttonList.add(gitHub);

        this.buttonList.add(mythologicalTracker);

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        String title = "Bytecrafter's Skyblock Utilities";
//        String pages = "Page " + 1 + "/" + 1; //TODO change this if there are more things
        drawCenteredString(fontRendererObj, title, width/2, 10, 0xFFFFFF);
//        drawCenteredString(fontRendererObj,  pages, width/2, (int) (height*0.89), 0xFFFFFF);
    }

    @Override
    public void actionPerformed(GuiButton button) {
 /*       if(button == next){
            System.out.println("next");
        }
        else if(button == back){
            System.out.println("back");
        }
        else */
        if (button == gitHub) {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/Saft-ByteCrafter/BytesSkyblockUtils"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else if (button == mythologicalTracker) {
            OnOffConfigs.setMythoTracker(OnOffConfigs.getMythoTracker() == 0 ? 1 : 0);
            ConfigHandler.writeIntConfig(file, "trackers", "Mythological-Tracker", OnOffConfigs.getMythoTracker());
            mythologicalTracker.displayString = "Toggle the Mythological-Tracker: " + Utils.getColoredBool(OnOffConfigs.getMythoTracker() == 1);
        }
    }
}
