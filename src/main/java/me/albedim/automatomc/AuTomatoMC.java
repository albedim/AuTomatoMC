package me.albedim.automatomc;

import me.albedim.automatomc.event.ChatEvent;
import me.albedim.automatomc.event.UserEvent;
import me.albedim.automatomc.executor.AICommand;
import me.albedim.automatomc.utility.aichat.AIChat;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public final class AuTomatoMC extends JavaPlugin {

    private static AuTomatoMC INSTANCE;
    public ArrayList<AIChat> AIChats;
    public ArrayList<UUID> AIDirectUsers;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        saveDefaultConfig();
        AIChats = new ArrayList<>();
        AIDirectUsers = new ArrayList<>();
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new UserEvent(), this);
        Objects.requireNonNull(getCommand(getConfigurationParam("ai_command"))).setExecutor(new AICommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AuTomatoMC getInstance() {
        return INSTANCE;
    }

    public String getConfigurationParam(String param) {
        return getConfig().getString(param);
    }

}