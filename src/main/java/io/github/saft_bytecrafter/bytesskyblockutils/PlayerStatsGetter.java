package io.github.saft_bytecrafter.bytesskyblockutils;

import io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff.MinionInfo;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatsGetter {

    private List<MinionInfo> playerMinions;

    public PlayerStatsGetter(){
        playerMinions = new ArrayList<>();
    }

    public void getPlayerStats(String uuid,  String apiKey){
        //TODO add this with the json and stuff (-> learn how to do it)
    }


    public List<MinionInfo> getPlayerMinions() {
        return playerMinions;
    }
}
