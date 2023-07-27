package com.bshaven.manifestspawn.commands;

import com.bshaven.manifestspawn.ManifestSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Check if a custom spawn location is set in the configuration
            if (ManifestSpawn.getPlugin().getConfig().contains("spawn.location")) {
                // Retrieve the custom spawn location data from the configuration
                double x = ManifestSpawn.getPlugin().getConfig().getDouble("spawn.location.x");
                double y = ManifestSpawn.getPlugin().getConfig().getDouble("spawn.location.y");
                double z = ManifestSpawn.getPlugin().getConfig().getDouble("spawn.location.z");
                float yaw = (float) ManifestSpawn.getPlugin().getConfig().getDouble("spawn.location.yaw");
                float pitch = (float) ManifestSpawn.getPlugin().getConfig().getDouble("spawn.location.pitch");
                String worldName = ManifestSpawn.getPlugin().getConfig().getString("spawn.location.world");

                // Debug messages
                Bukkit.getLogger().info("Custom Spawn Location Data:");
                Bukkit.getLogger().info("X: " + x);
                Bukkit.getLogger().info("Y: " + y);
                Bukkit.getLogger().info("Z: " + z);
                Bukkit.getLogger().info("Yaw: " + yaw);
                Bukkit.getLogger().info("Pitch: " + pitch);
                Bukkit.getLogger().info("World Name: " + worldName);

                // Check if the world name and world with the specified name exist
                if (worldName != null && Bukkit.getWorld(worldName) != null) {
                    player.teleport(new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch));
                    return true;
                }
            }

            // If the custom spawn is not set or invalid, use the default spawn location
            player.sendMessage("Custom spawn location not set or invalid. Teleporting to default spawn.");
            player.teleport(player.getWorld().getSpawnLocation());
            return true;
        }

        // If the command is executed by the console, send a message indicating that it's for players only
        sender.sendMessage("This command can only be executed by players.");
        return false;
    }
}