package io.github.saft_bytecrafter.bytesskyblockutils.guis;

import io.github.saft_bytecrafter.bytesskyblockutils.BSUMain;
import io.github.saft_bytecrafter.bytesskyblockutils.api.PlayerStatsGetter;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff.MinionInfo;
import io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff.MinionStonksCalc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinionEfficiencyGui extends GuiScreen {

    private static int intBazaarNpc; //0 -> Bazaar, 1 -> Npc
    private static int intFuel;
    private static int intUpgrade1;
    private static int intUpgrade2;

    private static final String file = ConfigHandler.getMinionConfig();

    private static List<String> upgrades = new ArrayList<>();
    private static List<String> listFuel = new ArrayList<>();

    private GuiButton refresh;
    private GuiButton fuel;
    private GuiButton upgrade1;
    private GuiButton upgrade2;
    private GuiButton bazaarNpc;
    private GuiTextField minionAmount;
    private GuiTextField setAllMinionLvl;
    //TODO maybe add additional bonus

    private static int intMinionAmount;
    private static int intSetAllMinionLvl;

    private static MinionStonksCalc minionStonksCalc;

    private static List<MinionInfo> listMinionStonks;

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

    @Override
    public void initGui(){
        super.initGui();

        minionStonksCalc = new MinionStonksCalc();
        listMinionStonks = minionStonksCalc.getMinionStonksInfo();

        int buttonWidth = 150;
        int buttonHeight = 20;

        upgrades.add("Empty");
        upgrades.add("Auto Smelter");
        upgrades.add("Compactor");
        upgrades.add("Super Compactor");
        upgrades.add("Dwarven Compactor");
        upgrades.add("Diamond Spreading");
        upgrades.add("Potato Spreading");
        upgrades.add("Krampus Helmet");//TODO maybe do it so it only counts once or warns the user that dia spread is used
        upgrades.add("Minion Expander");
        upgrades.add("Fly Catcher");
        upgrades.add("Lesser Soulflow Engine");//TODO also maybe tell the user that for gravel and chick minions no engine is used
        upgrades.add("Soulflow Engine");

        listFuel.add("Nothing");
        //       listFuel.add("Coal");
        //       listFuel.add("Block of Coal");
        //       listFuel.add("Ench. Bread");
        //       listFuel.add("Ench. Coal"); TODO maybe change it do a dropdown and then add these
        listFuel.add("Ench. Charcoal");
        //        listFuel.add("Solar Panel");
        listFuel.add("Ench. Lava Bucket");
        listFuel.add("Magma Bucket");
        listFuel.add("Plasma Bucket");
        listFuel.add("Hamster Wheel");
        listFuel.add("Foul Flesh");
        listFuel.add("Tasty Cheese");
        listFuel.add("Catalyst");
        listFuel.add("Hyper Catalyst");


        intBazaarNpc = ConfigHandler.getInt(file, " ", "Bazaar or NPC");
        intFuel = ConfigHandler.getInt(file, " ", "Fuel");
        intUpgrade1 = ConfigHandler.getInt(file, " ", "Upgrade 1");
        intUpgrade2 = ConfigHandler.getInt(file, " ", "Upgrade 2");

        bazaarNpc = new GuiButton(0, width/7*1-buttonWidth/2, height/7*2-buttonHeight/2, buttonWidth, buttonHeight, intBazaarNpc == 0 ? "Use Bazaar Prices" : "Use NPC Prices");
        refresh = new GuiButton(0, width/7*1-buttonWidth/2, height/7*5-buttonHeight/2, buttonWidth, buttonHeight, "Refresh");
        fuel = new GuiButton(0, width/7*3-buttonWidth/2, height/7*2-buttonHeight/2, buttonWidth, buttonHeight, "Fuel: " + listFuel.get(intFuel));
        upgrade1 = new GuiButton(0, width/7*3-buttonWidth/2, height/7*4-buttonHeight/2, buttonWidth, buttonHeight, "Upgrade 1: " + upgrades.get(intUpgrade1));
        upgrade2 = new GuiButton(0, width/7*3-buttonWidth/2, height/7*5-buttonHeight/2, buttonWidth, buttonHeight, "Upgrade 2: " + upgrades.get(intUpgrade2));

        minionAmount = new GuiTextField(0, fontRendererObj, width/7*1-buttonWidth/2, height/7*3-buttonHeight/2, buttonWidth, buttonHeight);
        setAllMinionLvl = new GuiTextField(0, fontRendererObj, width/7*1-buttonWidth/2, height/7*4-buttonHeight/2, buttonWidth, buttonHeight);


        buttonList.add(bazaarNpc);
        buttonList.add(refresh);
        buttonList.add(fuel);
        buttonList.add(upgrade1);
        buttonList.add(upgrade2);

        minionAmount.setText(String.valueOf(intMinionAmount));
        setAllMinionLvl.setText(String.valueOf(intSetAllMinionLvl));
        minionAmount.setEnabled(true);
        setAllMinionLvl.setEnabled(true);
        minionAmount.setVisible(true);
        setAllMinionLvl.setVisible(true);

        BSUMain.playerStatsGetter.getPlayerMinionsFromApi(Minecraft.getMinecraft().thePlayer.getUniqueID().toString().replaceAll("-", ""));
        BSUMain.bazaarGetter.getBazaarData();
        minionStonksCalc.calcMinions();
        listMinionStonks = minionStonksCalc.getMinionStonksInfo();

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        int modifier = 2;
        for(MinionInfo minion : listMinionStonks){
            drawString(fontRendererObj, minion.toString(), width/10*6, height/20*modifier, 0xFFFFFF);
            modifier++;
        }

        minionAmount.drawTextBox();
        setAllMinionLvl.drawTextBox();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.equals(bazaarNpc)){
            intBazaarNpc = intBazaarNpc == 0 ? 1 : 0;
            ConfigHandler.writeIntConfig(file, " ", "Bazaar or NPC", intBazaarNpc);
            bazaarNpc.displayString = intBazaarNpc == 0 ? "Use Bazaar Prices" : "Use NPC Prices";
        }
        else if(button.equals(fuel)){
            intFuel = (intFuel + 1 == listFuel.size()) ? 0 : intFuel + 1;
            ConfigHandler.writeIntConfig(file, " ", "Fuel", intFuel); //TODO this has some serious issues
            fuel.displayString = "Fuel: " + listFuel.get(intFuel);
            minionStonksCalc.calcMinions();
            listMinionStonks = minionStonksCalc.getMinionStonksInfo();
        }
        else if(button.equals(upgrade1)){
            intUpgrade1 = (intUpgrade1 + 1 == upgrades.size()) ? 0 : intUpgrade1 + 1;
            ConfigHandler.writeIntConfig(file, " ", "Upgrade 1", intUpgrade1);
            upgrade1.displayString = "Upgrade 1: " + upgrades.get(intUpgrade1);
            minionStonksCalc.calcMinions();
            listMinionStonks = minionStonksCalc.getMinionStonksInfo();
        }
        else if(button.equals(upgrade2)){
            intUpgrade2 = (intUpgrade2 + 1 == upgrades.size()) ? 0 : intUpgrade2 + 1;
            ConfigHandler.writeIntConfig(file,  " ", "Upgrade 2", intUpgrade2);
            upgrade2.displayString = "Upgrade 2: " + upgrades.get(intUpgrade2);
            minionStonksCalc.calcMinions();
            listMinionStonks = minionStonksCalc.getMinionStonksInfo();
        }
        else if(button.equals(refresh)){
            minionStonksCalc.calcMinions();
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        minionAmount.mouseClicked(mouseX, mouseY, mouseButton);
        setAllMinionLvl.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if(47 < typedChar && typedChar < 58 || GuiScreen.isKeyComboCtrlA(keyCode) || GuiScreen.isKeyComboCtrlC(keyCode) || GuiScreen.isKeyComboCtrlV(keyCode) || GuiScreen.isKeyComboCtrlX(keyCode) || keyCode == 14){
            minionAmount.textboxKeyTyped(typedChar, keyCode);
            setAllMinionLvl.textboxKeyTyped(typedChar, keyCode);
            intMinionAmount = Integer.parseInt(minionAmount.getText());
            intSetAllMinionLvl = Integer.parseInt(setAllMinionLvl.getText());
        }
    }

    public static int getIntBazaarNpc(){
        return intBazaarNpc;
    }

    public static void setIntBazaarNpc(int intBazaarNpc){
        MinionEfficiencyGui.intBazaarNpc = intBazaarNpc;
    }

    public static int getIntFuel(){
        return intFuel;
    }

    public static void setIntFuel(int intFuel){
        MinionEfficiencyGui.intFuel = intFuel;
    }

    public static int getIntUpgrade1(){
        return intUpgrade1;
    }

    public static void setIntUpgrade1(int intUpgrade1) {
        MinionEfficiencyGui.intUpgrade1 = intUpgrade1;
    }

    public static int getIntUpgrade2(){
        return intUpgrade2;
    }

    public static void setIntUpgrade2(int intUpgrade2) {
        MinionEfficiencyGui.intUpgrade2 = intUpgrade2;
    }

}
