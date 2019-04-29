package com.featuredspace.plugins.Tools2.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExeuctor {
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command is intended for players only!");
            return true;
        }
        
        final Player player = (Player) sender;
        
        if(args.length < 1) {
            player.sendMessage("/test -> ");
            player.sendMessage("/test inv - Opens an inventory");
            return true;
        }
        
        final String subcommand = args[0];
        args = Arrays.copyOfRange(args, 1, args.length);
        
        switch(subcommand) {
            case "inv": {
                // Code to open inventory
            }
        }
        
        return true;
    }
    
}