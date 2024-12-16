package me.albedim.automatomc.utility.aichat;

import me.albedim.automatomc.AuTomatoMC;
import me.albedim.automatomc.utility.http.ai.ai_schema.MessageSchema;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AIChat {

    private Player player;
    private ArrayList<MessageSchema> messages;

    public AIChat(Player player) {
        this.player = player;
        this.messages = new ArrayList<>();
        messages.add(new MessageSchema("user", getConfigurationMessage()));
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<MessageSchema> getMessages() {
        return messages;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMessages(ArrayList<MessageSchema> messageSchema) {
        this.messages = messageSchema;
    }

    private String getConfigurationMessage() {
        String aiName = AuTomatoMC.getInstance().getConfigurationParam("ai_command");
        String response = String.format(
                "These chat has some things you must keep in mind:\n1.%s\n2.%s\n3.%s",
                String.format("The user who texts is playing minecraft and they're called: %s", this.player.getName()),
                String.format("You're name is: %s", aiName),
                "You must speak the language you see in the next message"
        );
        return response;
    }
}
