package com.bshaven.manifestspawn.commands;

import com.bshaven.manifestspawn.ManifestSpawn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MsReload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("manifestspawn.msreload")) {
            // Reload the plugin's configuration
            ManifestSpawn.getPlugin().reloadConfig();
            sender.sendMessage("Configuration reloaded successfully.");
            return true;
        }

        // If the command is executed by a player without the permission, send an error message
        sender.sendMessage("You don't have permission to use this command.");
        return false;
    }

}
