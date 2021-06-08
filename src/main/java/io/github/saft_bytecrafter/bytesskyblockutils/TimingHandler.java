package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigGUI;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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

    public static void setGuiToOpen(String guiToOpen) {
        TimingHandler.guiToOpen = guiToOpen;
    }
/*    private int tickCounter;
    private List<ItemStack> differentItems;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    private void onWorldJoin(EntityJoinWorldEvent event){
        tickCounter = 1;
        SBUMain.compareInventories.resetOldInventory();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    private void onTick(TickEvent.ClientTickEvent event){
        tickCounter++;

        if(tickCounter % 5 == 0){
            differentItems = SBUMain.compareInventories.getNewItems(Minecraft.getMinecraft().thePlayer.inventory.mainInventory);
            for(ItemStack stack: differentItems){
                System.out.println(stack.getUnlocalizedName() + " + " + stack.stackSize);
            }
        }
    }
*/
}
