package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigGUI;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.DifferentItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.lang3.builder.Diff;

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
        }
        guiToOpen = null;
    }

    private static int tickCounter;

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
            List<DifferentItems> differentItems = BSUMain.compareInventories.getDifferentItems();
            for(DifferentItems item: differentItems){
                System.out.println(item.getItemName() + " + " + item.getItemAmount());
            }
        }
    }

    public static void setGuiToOpen(String guiToOpen) { //setter für die zu öffnende gui
        TimingHandler.guiToOpen = guiToOpen;
    }

}
