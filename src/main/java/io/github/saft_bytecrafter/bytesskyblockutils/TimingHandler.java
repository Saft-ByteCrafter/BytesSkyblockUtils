package io.github.saft_bytecrafter.bytesskyblockutils;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class TimingHandler {

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
