package com.bshaven.manifestspawn;

import com.bshaven.manifestspawn.commands.MsReload;
import com.bshaven.manifestspawn.commands.SetSpawn;
import com.bshaven.manifestspawn.commands.Spawn;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public final class ManifestSpawn extends JavaPlugin {

    // Add a static method to access the plugin instance from other classes
    private static ManifestSpawn plugin;

    public static ManifestSpawn getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        plugin = this;

        // Register the /spawn command with its executor
        registerCommand("spawn", new Spawn());

        // Register the /setspawn command with its executor
        registerCommand("setspawn", new SetSpawn());

        // Register the /msreload command with its executor
        registerCommand("msreload", new MsReload());

        // Load the config file
        saveDefaultConfig();
    }

    // Helper method to register commands
    private void registerCommand(String commandName, CommandExecutor executor) {
        PluginCommand command = getCommand(commandName);
        if (command != null) {
            command.setExecutor(executor);
        } else {
            // Use ChatColor to change the color of the warning message
            getLogger().warning(ChatColor.DARK_PURPLE + "Failed to register command: " + commandName);
        }
    }

    @Override
    public void onDisable() {
        System.out.println("ManifestSpawn has been unloaded successfully!");
    }
}
