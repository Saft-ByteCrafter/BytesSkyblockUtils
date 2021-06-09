package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.commands.ConfigGuiCommand;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.CompareInventories;
import io.github.saft_bytecrafter.bytesskyblockutils.itemtracking.Trackers;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BSUMain.MODID, version = BSUMain.VERSION, clientSideOnly = true)
public class BSUMain{

    public static final String MODID = "BytesSkyblockUtils";
    public static final String VERSION = "0.2.3.1";
    public static CompareInventories compareInventories;

    public BSUMain(){
        compareInventories = new CompareInventories();
    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event){


        MinecraftForge.EVENT_BUS.register(new CompareInventories());
        MinecraftForge.EVENT_BUS.register(new TimingHandler());
        MinecraftForge.EVENT_BUS.register(new Trackers());

        ClientCommandHandler.instance.registerCommand(new ConfigGuiCommand());

        ConfigHandler.reloadConfig();
    }
}
