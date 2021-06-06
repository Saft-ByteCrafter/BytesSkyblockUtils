package de.komendera.bsu;

import de.komendera.bsu.itemtracking.CompareInventories;
import de.komendera.bsu.itemtracking.Trackers;
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
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new TimingHandler());
        MinecraftForge.EVENT_BUS.register(new Trackers());

        ConfigHandler.reloadConfig();
    }
}
