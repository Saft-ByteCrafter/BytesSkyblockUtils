package io.github.saft_bytecrafter.bytesskyblockutils.commands;

import io.github.saft_bytecrafter.bytesskyblockutils.TimingHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class MinionEfficiencyCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "minionefficiency";
    }

    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (ConfigHandler.getString("config/BytesSkyblockUtils/modconfig.cfg", "general", "API-Key").equals("")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You need to set your API-Key by doing \"/api new\" first"));
            return;
        }
        TimingHandler.setGuiToOpen("minionefficiency");
    }
}
