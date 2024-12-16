package me.albedim.automatomc.utility.aichat;

import me.albedim.automatomc.AuTomatoMC;
import me.albedim.automatomc.utility.http.ai.AiAPI;
import me.albedim.automatomc.utility.http.ai.ai_schema.MessageSchema;
import org.bukkit.entity.Player;

public class AIChatHandler {

    public static AIChat createChat(Player player) {
        AIChat aiChat = new AIChat(player);
        AuTomatoMC.getInstance().AIChats.add(aiChat);
        return aiChat;
    }

    public static void removeChat(Player player) {
        for (AIChat aiChat : AuTomatoMC.getInstance().AIChats) {
            if (aiChat.getPlayer().equals(player)) {
                AuTomatoMC.getInstance().AIChats.remove(aiChat);
                break;
            }
        }
    }

    public static boolean hasOpenChat(Player player) {
        for (AIChat aiChat : AuTomatoMC.getInstance().AIChats) {
            if (aiChat.getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static AIChat getChat(Player player) {
        for (AIChat aiChat : AuTomatoMC.getInstance().AIChats) {
            if (aiChat.getPlayer().equals(player)) {
                return aiChat;
            }
        }
        return null;
    }

    public static String addMessage(AIChat chat, String message) {
        chat.getMessages().add(new MessageSchema("user", message));
        String response = AiAPI.getResponse(chat);
        chat.getMessages().add(new MessageSchema("assistant", response));
        return response;
    }

}