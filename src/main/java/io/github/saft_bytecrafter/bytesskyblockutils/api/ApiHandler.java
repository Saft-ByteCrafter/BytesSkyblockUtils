package io.github.saft_bytecrafter.bytesskyblockutils.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiHandler {

    public static JsonObject getApiInfo(String urlString) {

        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String input;
                StringBuilder response = new StringBuilder();

                while ((input = bufferedReader.readLine()) != null) {
                    response.append(input);
                }
                bufferedReader.close();

                Gson gson = new Gson();
                return gson.fromJson(response.toString(), JsonObject.class);

            } else {
                if (urlString.startsWith("https://api.hypixel.net/")) {
                    InputStream errorStream = connection.getErrorStream();
                    try (Scanner scanner = new Scanner(errorStream)) {
                        scanner.useDelimiter("\\Z");
                        String error = scanner.next();

                        Gson gson = new Gson();
                        gson.fromJson(error, JsonObject.class);
                    }
                } else {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "API-Request failed. HTTP Error Code: " + connection.getResponseCode()));
                }
            }
        } catch (IOException e) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "An error has occurred. See logs for more info. (Mod: BSU)"));
            e.printStackTrace();
        }

        return new JsonObject(); //just so that it returns one, in this case it is going to be empty
    }

}
