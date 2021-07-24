package io.github.saft_bytecrafter.bytesskyblockutils.commands;

import io.github.saft_bytecrafter.bytesskyblockutils.TimingHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

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
        TimingHandler.setGuiToOpen("minionefficiency");
    }
}
