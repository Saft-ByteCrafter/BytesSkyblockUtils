package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.guis.ConfigGUI;
import io.github.saft_bytecrafter.bytesskyblockutils.guis.DropStatsGui;
import io.github.saft_bytecrafter.bytesskyblockutils.guis.MythoDropsGui;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.DifferentItems;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.Trackers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class TimingHandler {

    private static String guiToOpen;

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event){

        if(guiToOpen != null){
            Minecraft mc = Minecraft.getMinecraft();
            if(guiToOpen.startsWith("configgui")){
                mc.displayGuiScreen(new ConfigGUI());
            }
            else if(guiToOpen.startsWith("dropstats")){
                mc.displayGuiScreen(new DropStatsGui());
            }
            else if(guiToOpen.startsWith("mythodrops")){
                mc.displayGuiScreen(new MythoDropsGui());
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
    }

    public static void setGuiToOpen(String guiToOpen) { //setter für die zu öffnende gui
        TimingHandler.guiToOpen = guiToOpen;
    }

    public static List<DifferentItems> getDifferentItems(){
       return differentItems;
    } //unused for now

}
