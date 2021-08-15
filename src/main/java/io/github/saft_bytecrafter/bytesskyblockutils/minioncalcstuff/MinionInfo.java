package io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff;

import io.github.saft_bytecrafter.bytesskyblockutils.BSUMain;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.guis.MinionEfficiencyGui;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinionInfo {

    private int fuel;
    private int upgrade1;
    private int upgrade2;
    private boolean diaSpreading = false;
    private boolean potatoSpreading = false;
    private boolean krampusHelmet = false;
    private int soulflow; // 0 -> none, 1 -> lesser, 2 -> normal
    private double fuelBoost;
    private double ressourceMultiplier;

    private String minionType;
    private int minionLevel;
    private double actionsPerMinute;
    private double stonksPerAction;
    private double stonksPerDay;

    private List<Double> timingList;
    private Map<String, Double> bazaarPrices;

    public MinionInfo(String minionType, int minionLevel, List<Double> timingList, LinkedHashMap<String, Double> dropMap){

        fuel = MinionEfficiencyGui.getIntFuel();
        upgrade1 = MinionEfficiencyGui.getIntUpgrade1();
        upgrade2 = MinionEfficiencyGui.getIntUpgrade2();
        bazaarPrices = BSUMain.bazaarGetter.getInstaSellMap();

        switch(fuel){
            case 1:
                fuelBoost = 0.2;
                ressourceMultiplier = 1;
                break;
            case 2:
                fuelBoost = 0.25;
                ressourceMultiplier = 1;
                break;
            case 3:
                fuelBoost = 0.3;
                ressourceMultiplier = 1;
                break;
            case 4:
                fuelBoost = 0.35;
                ressourceMultiplier = 1;
                break;
            case 5:
                fuelBoost = 0.5;
                ressourceMultiplier = 1;
                break;
            case 6:
                fuelBoost = 0.9;
                ressourceMultiplier = 1;
                break;
            case 7:
                fuelBoost = 0.0;
                ressourceMultiplier = 2;
                break;
            case 8:
                fuelBoost = 0.0;
                ressourceMultiplier = 3;
                break;
            case 9:
                fuelBoost = 0.0;
                ressourceMultiplier = 4;
                break;
            default:
                fuelBoost = 0.0;
                ressourceMultiplier = 1;
                break;
        }

        switch(upgrade1){
            case 5:
                diaSpreading = true;
                break;
            case 6:
                potatoSpreading = true;
                break;
            case 7:
                krampusHelmet = true;
                break;
            case 8:
                fuelBoost += 0.05;
                break;
            case 9:
                fuelBoost += 0.2;
                break;
            case 10:
                ressourceMultiplier *= 0.5;
                soulflow = 1;
                break;
            case 11:
                ressourceMultiplier *= minionType.equals("VOIDLING") ? 0.5 - 0.03 * minionLevel : 0.5;
                soulflow = 2;
                break;
            default:
                break;
        }

        switch(upgrade2){
            case 5:
                diaSpreading = true;
                break;
            case 6:
                potatoSpreading = true;
                break;
            case 7:
                krampusHelmet = true;
                break;
            case 8:
                fuelBoost += 0.05;
                break;
            case 9:
                fuelBoost += 0.2;
                break;
            case 10:
                if(soulflow == 0) ressourceMultiplier *= 0.5;
                soulflow = 1;
                break;
            case 11:
                if(soulflow == 0) ressourceMultiplier *= minionType.equals("VOIDLING") ? 0.5 - 0.03 * minionLevel : 0.5;
                soulflow = 2;
                break;
            default:
                break;
        }

        this.minionType = minionType;
        this.minionLevel = minionLevel;
        this.timingList = timingList;
        this.actionsPerMinute = 60/(this.timingList.get(minionLevel)*(1-(fuelBoost/(1+fuelBoost))))/2;
        Set<String> keySet = dropMap.keySet();
        for(String drop : keySet){
            if(MinionEfficiencyGui.getIntBazaarNpc() == 0) {
                stonksPerAction = bazaarPrices.get(drop)/160*(dropMap.get(drop)*ressourceMultiplier);
                if(diaSpreading) stonksPerAction += bazaarPrices.get("ENCHANTED_DIAMOND")/160*((dropMap.get(drop)*ressourceMultiplier)/10.0);
                if(potatoSpreading) stonksPerAction += bazaarPrices.get("ENCHANTED_POTATO")/160*((dropMap.get(drop)*ressourceMultiplier)/22.0); //TODO change this to the actual potatoes per drop (-> 22 to wahtever, it should be about right tho)
                if(krampusHelmet) stonksPerAction += bazaarPrices.get("RED_GIFT")*dropMap.get(drop)*ressourceMultiplier/25000;
            } //TODO make an else for this with the npc-prices
        }
        double soulflowMoney = soulflow == 1 || soulflow == 2 ? bazaarPrices.get("RAW_SOULFLOW")*480 : 0;
        this.stonksPerDay = actionsPerMinute*60*24*stonksPerAction + soulflowMoney;
    }

    public MinionInfo(String minionType, int minionLevel){
        this.minionType = minionType;
        this.minionLevel = minionLevel;
    }

    public String toString(){
        return minionType + " " + minionLevel + " " + stonksPerDay;
    }

    public String getMinionType(){
        return minionType;
    }

    public int getMinionLevel(){
        return minionLevel;
    }

}
