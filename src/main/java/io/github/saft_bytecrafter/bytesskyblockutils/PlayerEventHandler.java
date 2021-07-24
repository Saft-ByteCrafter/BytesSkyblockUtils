package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.OnOffConfigs;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.Trackers;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChat(ClientChatReceivedEvent event) {

        String chatMessage = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (chatMessage.contains(":")) return;
        if (OnOffConfigs.getMythoTracker() == 1) {
            Trackers.seeIfDugBurrow(chatMessage);
        }
    }

}
