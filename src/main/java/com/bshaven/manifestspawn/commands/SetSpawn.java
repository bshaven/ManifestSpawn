package com.bshaven.manifestspawn.commands;

import com.bshaven.manifestspawn.ManifestSpawn;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player and has the necessary permission to use /setspawn
        if (sender instanceof Player && sender.hasPermission("manifestspawn.setspawn")) {
            Player player = (Player) sender;
            Location location = player.getLocation();

            // Save the custom spawn location to the plugin config
            ManifestSpawn.getPlugin().getConfig().set("spawn.location.x", location.getX());
            ManifestSpawn.getPlugin().getConfig().set("spawn.location.y", location.getY());
            ManifestSpawn.getPlugin().getConfig().set("spawn.location.z", location.getZ());
            ManifestSpawn.getPlugin().getConfig().set("spawn.location.yaw", location.getYaw());
            ManifestSpawn.getPlugin().getConfig().set("spawn.location.pitch", location.getPitch());
            ManifestSpawn.getPlugin().getConfig().set("spawn.location.world", location.getWorld().getName());
            ManifestSpawn.getPlugin().saveConfig();

            player.sendMessage("Spawn location set to your current location.");
            return true;
        }

        // If the command is executed by the console or the player lacks permission, send an error message
        sender.sendMessage("You don't have permission to use this command.");
        return false;
    }
}