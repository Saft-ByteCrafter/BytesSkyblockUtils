package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.Guis.ConfigGUI;
import io.github.saft_bytecrafter.bytesskyblockutils.Guis.DropStatsGui;
import io.github.saft_bytecrafter.bytesskyblockutils.Guis.MythoDropsGui;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.DifferentItems;
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
    System.out.println("you joined");
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
            for(DifferentItems item: differentItems){
                System.out.println(item.getItemName() + " + " + item.getItemAmount());
            }
        }
    }

    public static void setGuiToOpen(String guiToOpen) { //setter für die zu öffnende gui
        TimingHandler.guiToOpen = guiToOpen;
    }

}
