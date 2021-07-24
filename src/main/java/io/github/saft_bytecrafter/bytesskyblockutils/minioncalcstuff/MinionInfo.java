package io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff;

import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;

import java.util.Map;

public class MinionInfo {

    private int fuel;
    private int upgrade1;
    private int upgrade2;
    private boolean diaSpreading = false;
    private boolean potatoSpreading = false;
    private boolean krampusHelmet = false;
    private int soulflow; // 0 -> none, 1 -> lesser, 2 -> normal
    private double fuelBoost;
    private int ressourceMultiplier;
    private double upgrade1Boost;
    private double upgrade2Boost;
    private String file;

    private String minionType;
    private int minionLevel;
    private double actionsPerMinute;
    private double stonksPerDay;

    private Map<Integer, Double> timingMap;

    public MinionInfo(String minionType, int minionLevel, Map<Integer, Double> timingMap){//TODO add more thingies here
        file = ConfigHandler.getMinionConfig();
        fuel = ConfigHandler.getInt(file, " ", "Fuel");
        upgrade1 = ConfigHandler.getInt(file, " ", "Upgrade 1");
        upgrade2 = ConfigHandler.getInt(file, " ", "Upgrade 2");

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
                fuelBoost = 0.5;
                ressourceMultiplier = 1;
                break;
            case 5:
                fuelBoost = 0.9;
                ressourceMultiplier = 1;
                break;
            case 6:
                ressourceMultiplier = 2;
                break;
            case 7:
                ressourceMultiplier = 3;
                break;
            case 8:
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
                ressourceMultiplier *= 0.5;
                soulflow = 2;
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
                ressourceMultiplier *= 0.5;
                soulflow = 1;
                break;
            case 11:
                ressourceMultiplier *= 0.5;
                soulflow = 2;
                break;
        }

        this.minionType = minionType;
        this.minionLevel = minionLevel;
        this.timingMap = timingMap;
        this.actionsPerMinute = 60/(this.timingMap.get(minionLevel)*(1-(fuelBoost/(1+fuelBoost))));

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
