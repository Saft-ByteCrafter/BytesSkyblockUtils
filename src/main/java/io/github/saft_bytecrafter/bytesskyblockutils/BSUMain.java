package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.commands.ConfigGuiCommand;
import io.github.saft_bytecrafter.bytesskyblockutils.commands.DropStatsCommand;
import io.github.saft_bytecrafter.bytesskyblockutils.commands.MinionEfficiencyCommand;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.CompareInventories;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.Trackers;
import io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff.MinionTimingMaps;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BSUMain.MODID, version = BSUMain.VERSION, clientSideOnly = true)
public class BSUMain{

    public static final String MODID = "BytesSkyblockUtils";
    public static final String VERSION = "0.5";
    public static CompareInventories compareInventories;
    public static MinionTimingMaps minionTimingMaps;
    public static PlayerStatsGetter playerStatsGetter;

    public BSUMain(){
        compareInventories = new CompareInventories();
        minionTimingMaps = new MinionTimingMaps();
        playerStatsGetter = new PlayerStatsGetter();
    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event){

        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        MinecraftForge.EVENT_BUS.register(new TimingHandler());
        MinecraftForge.EVENT_BUS.register(new CompareInventories());
        MinecraftForge.EVENT_BUS.register(new Trackers());

        ClientCommandHandler.instance.registerCommand(new ConfigGuiCommand());
        ClientCommandHandler.instance.registerCommand(new DropStatsCommand());
        ClientCommandHandler.instance.registerCommand(new MinionEfficiencyCommand());

        ConfigHandler.reloadConfig();
    }
}
