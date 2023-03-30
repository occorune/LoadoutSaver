package me.data.loadoutsaver;

import me.data.loadoutsaver.commands.LoadoutCommand;
import me.data.loadoutsaver.listener.CustomLoadout;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LSMain extends JavaPlugin {
    private static LSMain instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        registerEvents();
        getServer().getConsoleSender().sendMessage(color(getConfig().getString("Messages.Prefix")) + " Events Registered.");
        registerCommands();
        getServer().getConsoleSender().sendMessage(color(getConfig().getString("Messages.Prefix")) + " Commands Registered.");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(color(getConfig().getString("Messages.Prefix")) + " No issues detected, safely shutting down.");
    }

    public void registerEvents() {
          getServer().getPluginManager().registerEvents(new CustomLoadout(), this);
    }

    public void registerCommands() {
        getCommand("Loadout").setExecutor(new LoadoutCommand());
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static LSMain getInstance() {
        return instance;
    }
}
