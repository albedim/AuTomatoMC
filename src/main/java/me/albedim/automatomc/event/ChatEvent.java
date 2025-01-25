package me.albedim.automatomc.event;

import me.albedim.automatomc.AuTomatoMC;
import me.albedim.automatomc.utility.aichat.AIChat;
import me.albedim.automatomc.utility.aichat.AIChatHandler;
import me.albedim.automatomc.utility.chat.ChatUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(org.bukkit.event.player.AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (AuTomatoMC.getInstance().AIDirectUsers.contains(player.getUniqueId())) {
            event.setCancelled(true);
            AIChat chat = AIChatHandler.getChat(player);
            if (chat == null) {
                chat = AIChatHandler.createChat(player);
            }
            ChatUtility.sendMessage(player, true, message);
            String response = AIChatHandler.addMessage(chat, message);
            ChatUtility.sendMessage(player, false, response);
        }
    }
}
