package io.github.saft_bytecrafter.bytesskyblockutils.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.saft_bytecrafter.bytesskyblockutils.configstuff.ConfigHandler;
import io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff.MinionInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.*;

public class PlayerStatsGetter {

    private String apiKey;
    private List<MinionInfo> minionList;

    public PlayerStatsGetter(){
        minionList = new ArrayList<>();
        apiKey = ConfigHandler.getString("config/BytesSkyblockUtils/modconfig.cfg", "general", "API-Key");
    }

    public String getPlayersLatestProfile(String uuid){
        JsonObject playerProfiles = ApiHandler.getApiInfo("https://api.hypixel.net/skyblock/profiles?uuid=" + uuid + "&key=" + apiKey);
        if(!playerProfiles.get("success").getAsBoolean()){
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Getting Your Stats failed due to" + playerProfiles.get("cause").getAsString()));
            return null;
        }
        if(playerProfiles.get("profiles").isJsonNull()){
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This player seems to not have joined SkyBlock yet."));
            return null;
        }
        String latestProfile = "";
        long latestSave = 0;
        JsonArray profiles = playerProfiles.get("profiles").getAsJsonArray();

        for(JsonElement profile : profiles){
            long latestProfileSave = 0;
            if(profile.getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().has("last_save")){
                latestProfileSave = profile.getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("last_save").getAsLong();
            }

            if(latestProfileSave > latestSave){
                latestProfile = profile.getAsJsonObject().get("profile_id").getAsString();
                latestSave = latestProfileSave;
            }

        }
        return latestProfile;
    }


    public void getPlayerMinionsFromApi(String uuid) {
        //TODO also make it so that TimingHandler.canGetNewApi = false; and that it only gets the new stats if it is true

        Map<String, Integer> minionMap;

        JsonArray jsonMinionArray;

        JsonObject playerStats = ApiHandler.getApiInfo("https://api.hypixel.net/skyblock/profile?profile=" + getPlayersLatestProfile(uuid) + "&key=" + apiKey);

        if(!playerStats.get("success").getAsBoolean()){
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Getting Your Stats failed due to" + playerStats.get("cause").getAsString()));
            return;
        }
        minionList.clear();
        minionMap = new HashMap<>();
        jsonMinionArray = playerStats.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("crafted_generators").getAsJsonArray();
        for(JsonElement element : jsonMinionArray){
            String[] splitMinion = element.getAsString().split("_");
            if(splitMinion.length > 2) {
                StringBuilder minionName = new StringBuilder(splitMinion[0]);
                for (int i = 1; i < splitMinion.length - 1; i++) {
                    minionName.append("_").append(splitMinion[i]);
                }
                splitMinion[0] = minionName.toString();
                splitMinion[1] = splitMinion[splitMinion.length - 1];
            }
            if(!minionMap.containsKey(splitMinion[0]) || !(minionMap.get(splitMinion[0]) > Integer.parseInt(splitMinion[1]))){
                minionMap.put(splitMinion[0], Integer.valueOf(splitMinion[1]));
            }
        }
        for(Map.Entry<String, Integer> entry : minionMap.entrySet()){
            minionList.add(new MinionInfo(entry.getKey(), entry.getValue()));
        }
    }

    public List<MinionInfo> getPlayerMinions(){
        return minionList;
    }
}
