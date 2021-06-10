package io.github.saft_bytecrafter.bytesskyblockutils.Guis;

import io.github.saft_bytecrafter.bytesskyblockutils.TimingHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class DropStatsGui extends GuiScreen {

    private GuiButton mythoTab;

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

    @Override
    public void initGui(){
        super.initGui();

        int tabButtonWidth = 100;
        int buttonHeight = 20;

        mythoTab = new GuiButton(0, width/4-tabButtonWidth/2, height/2-buttonHeight/2, tabButtonWidth, buttonHeight, "Mythological");

        buttonList.add(mythoTab);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button == mythoTab){
            TimingHandler.setGuiToOpen("mythodrops");
        }
    }
}
