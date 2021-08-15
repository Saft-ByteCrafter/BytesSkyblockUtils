package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.guis.ConfigGUI;
import io.github.saft_bytecrafter.bytesskyblockutils.guis.DropStatsGui;
import io.github.saft_bytecrafter.bytesskyblockutils.guis.MinionEfficiencyGui;
import io.github.saft_bytecrafter.bytesskyblockutils.guis.MythoDropsGui;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.DifferentItems;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.Trackers;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.collection.parallel.ParIterableLike;

import java.util.List;

public class TimingHandler {

    private static String guiToOpen;
    public static boolean canGetNewApi;

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event){

        if(guiToOpen != null){
            Minecraft mc = Minecraft.getMinecraft();
            switch (guiToOpen){
                case "configgui":
                    mc.displayGuiScreen(new ConfigGUI());
                    break;
                case "dropstats":
                    mc.displayGuiScreen(new DropStatsGui());
                    break;
                case "mythodrops":
                    mc.displayGuiScreen(new MythoDropsGui());
                    break;
                case "minionefficiency":
                    mc.displayGuiScreen(new MinionEfficiencyGui());
                    break;
            }
        }
        guiToOpen = null;
    }

    private static int tickCounter;
    private static List<DifferentItems> differentItems;

   @SubscribeEvent()
   public void onWorldJoin(EntityJoinWorldEvent event){
       if(event.entity != Minecraft.getMinecraft().thePlayer) return;
       tickCounter = 1;
       BSUMain.compareInventories.resetOldInventory();
       BSUMain.compareInventories.getNewItems(Minecraft.getMinecraft().thePlayer.inventory.mainInventory);
       Trackers.makeNewCompareInvInstance();
   }

    @SubscribeEvent()
    public void onTick(TickEvent.ClientTickEvent event){
        tickCounter++;

        if(tickCounter % 5 == 0){
            if(Minecraft.getMinecraft().thePlayer != null){
                if(Minecraft.getMinecraft().thePlayer.inventory.mainInventory != null) {
                    BSUMain.compareInventories.getNewItems(Minecraft.getMinecraft().thePlayer.inventory.mainInventory);
                }
            }
            differentItems = BSUMain.compareInventories.getDifferentItems();
        }

        if(tickCounter % 1200 == 0){ //-> every 60 seconds
            canGetNewApi = true;
        }
    }

    public static void setGuiToOpen(String guiToOpen) { //setter für die zu öffnende gui
        TimingHandler.guiToOpen = guiToOpen;
    }

    public static List<DifferentItems> getDifferentItems(){
       return differentItems;
    } //unused for now

}
