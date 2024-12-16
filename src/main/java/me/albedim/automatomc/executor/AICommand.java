package me.albedim.automatomc.executor;

import me.albedim.automatomc.AuTomatoMC;
import me.albedim.automatomc.utility.aichat.AIChat;
import me.albedim.automatomc.utility.aichat.AIChatHandler;
import me.albedim.automatomc.utility.chat.ChatUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AICommand implements CommandExecutor {

    private static final String AI_COMMAND = AuTomatoMC.getInstance().getConfigurationParam("ai_command");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equals(AI_COMMAND)) {
            return false;
        }

        Player player = Bukkit.getPlayer(sender.getName());
        if (player == null) {
            return false;
        }

        if (args.length < 1) {
            boolean isDirectAIUser = AuTomatoMC.getInstance().AIDirectUsers.contains(player.getUniqueId());
            if (isDirectAIUser) {
                AuTomatoMC.getInstance().AIDirectUsers.remove(player.getUniqueId());
                sendAIOFFMessage(player);
            } else {
                AIChat userChat = AIChatHandler.getChat(player);
                if (userChat == null) {
                    AIChatHandler.createChat(player);
                }
                AuTomatoMC.getInstance().AIDirectUsers.add(player.getUniqueId());
                sendAIONMessage(player);
            }
            return false;
        }

        AIChat userChat = AIChatHandler.getChat(player);
        if (userChat == null) {
            userChat = AIChatHandler.createChat(player);
        }
        String message = String.join(" ", args);
        String response = AIChatHandler.addMessage(userChat, message);
        ChatUtility.sendMessage(player, false, response);
        return false;
    }

    private void sendAIONMessage(Player player) {
        player.sendMessage("§8----------------------\n");
        player.sendMessage(
                AuTomatoMC.getInstance().getConfigurationParam("ai_on")
                        .replace("%ai_name%", AI_COMMAND.toUpperCase())
        );
        player.sendMessage("§8----------------------\n");
    }

    private void sendAIOFFMessage(Player player) {
        player.sendMessage("§8----------------------\n");
        player.sendMessage(
                AuTomatoMC.getInstance().getConfigurationParam("ai_off")
                        .replace("%ai_name%", AI_COMMAND.toUpperCase())
        );
        player.sendMessage("§8----------------------\n");
    }

}
