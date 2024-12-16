package me.albedim.automatomc.utility.chat;

import me.albedim.automatomc.AuTomatoMC;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ChatUtility {

    @SuppressWarnings("deprecation")
    public static TextComponent createInteractiveMessage(String hoverText, ClickEvent.Action action, String message, String command)
    {
        TextComponent button = new TextComponent(message);
        if(hoverText != null){
            BaseComponent[] hoverTextComponents = TextComponent.fromLegacyText(hoverText);
            button.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverTextComponents));
        }

        button.setClickEvent(new ClickEvent(action, command));
        return button;
    }

    public static void sendMessage(Player player, boolean isSender, String message) {
        String senderName = (
                isSender ?
                player.getName().toUpperCase() :
                AuTomatoMC.getInstance().getConfigurationParam("ai_command").toUpperCase()
        );
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 300, 300);
        player.sendMessage(String.format("§7(§e§l%s§7) §8➜§7 %s", senderName, message));
        player.sendMessage("§8----------------------\n\n");
    }

    public static TextComponent createEmptyInteractiveMessage(String message)
    {
        return new TextComponent(message);
    }
}
