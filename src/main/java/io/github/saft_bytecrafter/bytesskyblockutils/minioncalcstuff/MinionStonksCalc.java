package io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff;

import io.github.saft_bytecrafter.bytesskyblockutils.BSUMain;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class MinionStonksCalc {

    private List<MinionInfo> minionStonksInfo = new ArrayList<>();

    public void calcMinions(){
        minionStonksInfo.clear();
        for(MinionInfo minion : BSUMain.playerStatsGetter.getPlayerMinions()){
            if(minion.getMinionType().equals("COBBLESTONE")) minionStonksInfo.add(new MinionInfo(minion.getMinionType(), minion.getMinionLevel(), MinionMaps.getMinionTimingMaps().get(minion.getMinionType()), MinionMaps.getMinionOutputMap().get(minion.getMinionType()))); //TODO remove the if when all minions are there obviously (and maybe add smth so it doesn't crash when a minion gets added)
        }
    }

    public  List<MinionInfo> getMinionStonksInfo(){
        return minionStonksInfo;
    }

}
