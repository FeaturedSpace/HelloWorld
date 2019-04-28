package com.featuredspace.plugins.Tools2;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Tools2Plugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getLogger().info("We're loaded.");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("fly")) {
			
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				player.setAllowFlight(!player.getAllowFlight());
				player.sendMessage(ChatColor.YELLOW + "Toggled FlyMode");
			}
			
		}
		
		return true;
	}
	
}
