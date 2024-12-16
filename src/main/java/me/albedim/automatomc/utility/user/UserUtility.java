package me.albedim.automatomc.utility.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UserUtility {

    @SuppressWarnings("deprecation")
    public static boolean isUsernamePremium(String username) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+username);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = in.readLine())!=null){
                result.append(line);
            }
            return !result.toString().isEmpty();
        } catch (IOException e) {
            return false;
        }
    }
}
