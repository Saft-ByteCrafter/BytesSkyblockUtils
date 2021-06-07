package io.github.saft_bytecrafter.bytesskyblockutils.configstuff;

import io.github.saft_bytecrafter.bytesskyblockutils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ConfigGUI extends GuiScreen {

    //basic buttons
    private GuiButton next;
    private GuiButton back;
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

        next = new GuiButton(0, width/2 - 100, (int) (height * 0.8), "Next");
        back = new GuiButton(0, width/2 +20, (int) (height*0.8), 80, 20, "Back");
        gitHub = new GuiButton(0, width/2 - 60, (int) (height*0.5), 30, 30, "GitHub");

        mythologicalTracker = new GuiButton(0, 0, 0, "Toggle the Mythological-Tracker" + Utils.getColoredBool(ToggleCommand.);
    }

}
