package me.albedim.automatomc.event;

import me.albedim.automatomc.AuTomatoMC;
import me.albedim.automatomc.utility.aichat.AIChat;
import me.albedim.automatomc.utility.aichat.AIChatHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        AIChatHandler.removeChat(player);
        AuTomatoMC.getInstance().AIDirectUsers.remove(player.getUniqueId());
    }
}
