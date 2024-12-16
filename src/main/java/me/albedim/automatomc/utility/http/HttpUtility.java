package me.albedim.automatomc.utility.http;

import com.google.gson.Gson;

public class HttpUtility {

    public static String convertObjectToJson(Object obj) {
        try {
            Gson gson = new Gson();
            return gson.toJson(obj); // Convert object to JSON string
        } catch (Exception e) {
            return null;
        }
    }
}
