package io.github.saft_bytecrafter.bytesskyblockutils.api;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BazaarGetter {

    private Map<String, Double> instaBuyMap;
    private Map<String, Double> instaSellMap;

    public BazaarGetter(){
        instaBuyMap = new HashMap<>();
        instaSellMap = new HashMap<>();
    }

    public void getBazaarData(){
        JsonObject bazaarData = ApiHandler.getApiInfo("https://api.hypixel.net/skyblock/bazaar");

        if(!bazaarData.get("success").getAsBoolean()){
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Getting Your Stats failed due to" + bazaarData.get("cause").getAsString()));
            return;
        }
        instaBuyMap.clear();
        instaSellMap.clear();
        bazaarData = bazaarData.get("products").getAsJsonObject();
        Set<String> keys = bazaarData.keySet();
        for(String key : keys){
            instaBuyMap.put(bazaarData.get(key).getAsJsonObject().get("product_id").getAsString(), bazaarData.get(key).getAsJsonObject().get("quick_status").getAsJsonObject().get("buyPrice").getAsDouble());
            instaSellMap.put(bazaarData.get(key).getAsJsonObject().get("product_id").getAsString(), bazaarData.get(key).getAsJsonObject().get("quick_status").getAsJsonObject().get("sellPrice").getAsDouble());
        }
    }

    public Map<String, Double> getInstaBuyMap(){
        return instaBuyMap;
    }

    public Map<String, Double> getInstaSellMap(){
        return instaSellMap;
    }

}
