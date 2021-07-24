package io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff;

import io.github.saft_bytecrafter.bytesskyblockutils.BSUMain;

import java.util.ArrayList;
import java.util.List;

public class MinionStonksCalc {

    private List<MinionInfo> minionStonksInfo = new ArrayList<>();

    public void calcMinions(){
        for(MinionInfo minion : BSUMain.playerStatsGetter.getPlayerMinions()){
            minionStonksInfo.add(new MinionInfo(minion.getMinionType(), minion.getMinionLevel(), BSUMain.minionTimingMaps.getTimingMap(minion.getMinionType())));
        }
    }

    public  List<MinionInfo> getMinionStonksInfo(){
        return minionStonksInfo;
    }

}
