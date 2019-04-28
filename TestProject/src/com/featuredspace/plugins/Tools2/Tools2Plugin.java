package com.featuredspace.plugins.Tools2;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Tools2Plugin extends JavaPlugin implements Listener {
	
	private ArrayList<Player> gods = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		this.getLogger().info("We're loaded.");
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("fly")) {
			
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				player.setAllowFlight(!player.getAllowFlight());
				player.sendMessage(ChatColor.YELLOW + "Toggled FlyMode");
			}
			
		} else if(command.getName().equalsIgnoreCase("gmc")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.GREEN + "you are creative");
			
			}
		} else if(command.getName().equalsIgnoreCase("tntgun")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				ItemStack gun = new ItemStack(Material.IRON_HOE);
				ItemMeta meta = gun.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_RED + "TNTGun");
				gun.setItemMeta(meta);
				
				player.getInventory().addItem(gun);
				player.sendMessage(ChatColor.GREEN + "You have received a TNTGun");
			}
		} else if(command.getName().equalsIgnoreCase("zapgun")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				ItemStack gun = new ItemStack(Material.DIAMOND_HOE);
				ItemMeta meta = gun.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_AQUA + "ZapGun");
				gun.setItemMeta(meta);
				
				player.getInventory().addItem(gun);
				player.sendMessage(ChatColor.GREEN + "You received a ZapGun!");
			}
		} else if(command.getName().equalsIgnoreCase("god")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(gods.contains(player)) {
					gods.remove(player);
					
					player.sendMessage(ChatColor.BLUE + "youre naht holey");
				} else {
					gods.add(player);
					player.setHealth(player.getMaxHealth());
					player.setFoodLevel(20);
					
					player.sendMessage(ChatColor.BLUE + "youre prey godly");
				}
				
			}
		} else if(command.getName().equalsIgnoreCase("gms")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.DARK_AQUA + "YOURE SURVIVINGGGGGNN");
			}
		} else if(command.getName().equalsIgnoreCase("day")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				player.getWorld().setTime(6000);
				player.sendMessage(ChatColor.GREEN + "ITS NIGHT BOYS TIME TO GO TO A CLUBBBBB");
			}
		} else if(command.getName().equalsIgnoreCase("night")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				player.getWorld().setTime(12000);
				player.sendMessage(ChatColor.RED + "it is day you stupid ni**er");
			}
		} else if(command.getName().equalsIgnoreCase("spawn")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				// /spawn creeper
				EntityType type = EntityType.ZOMBIE;
				int num = 1;
				
				if(args.length >= 1) {
					
					if(args[0] != null || args[0] != "") {
						type = EntityType.fromName(args[0].toUpperCase());
					}
					
					if(args.length >= 2) {
						try {
							num = Integer.parseInt(args[1]);
						} catch(NumberFormatException e) {
							num = 1;
							player.sendMessage(ChatColor.RED + "That is not a proper number");
						}
					}
					
				} else {
					type = EntityType.ZOMBIE;
				}
				
				
				for(int i = 0; i < num; i++) {
					try {
						player.getWorld().spawnEntity(player.getLocation(), type);
					} catch(NullPointerException e) {
						player.sendMessage(ChatColor.DARK_RED + "That isn't a type: " + args[0]);
						return true;
					}
				}
				
				player.sendMessage(ChatColor.GREEN + "Succesfully spawned " + num + " " + type.toString() + "(s)");
			}
			
			
		} else if(command.getName().equalsIgnoreCase("killall")) {
			
			if(sender instanceof Player) {
				Player player = (Player) sender;
				int counter = 0;
				
				for(LivingEntity e : player.getWorld().getLivingEntities()) {
					if(e.getType() != EntityType.PLAYER) {
						if(e.getType() != EntityType.ENDER_DRAGON ) {
							e.damage(e.getMaxHealth());
						} else {
							e.remove();
						
						}
						counter++;
					}
				}
				
				player.sendMessage(ChatColor.GREEN + "Succesfully removed " + counter + " mobs!");
				
				
			}
			
		} else if(command.getName().equalsIgnoreCase("CHICKENBOMB")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				Location location = player.getLocation();
				location.setY(location.getY() + 5);
				
				for(int i = 0; i<846; i++) {
					player.getWorld().spawnEntity(location, EntityType.CHICKEN);
				}
				player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "YOU LOVEO CHICKEO");
				
			}
		}
			
		

		
		
		return true;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			final Player player = (Player) event.getPlayer();
			
			if(player == null) return;
			
			HashSet<Material> materials = new HashSet<Material>();
			materials.add(Material.AIR);
			
			event.setCancelled(true);
			if(player.getItemInHand() != null & player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "TNTGun")) {
				
				final Location location = player.getLocation();
				
				
				
				final Location bLoc = player.getTargetBlock(materials, 100).getLocation();
				final Vector vector = bLoc.toVector();
				
				vector.subtract(location.toVector());
				vector.normalize();
				
				vector.multiply(2);
				
				Entity entity = location.getWorld().spawnEntity(player.getEyeLocation(), EntityType.PRIMED_TNT);
				entity.setTicksLived(100);
				entity.setVelocity(vector);
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_AQUA + "ZapGun")) {
				final Location location = player.getLocation();
				final Location bLoc = player.getTargetBlock(materials, 100).getLocation();
				location.getWorld().strikeLightning(bLoc);
			} else {
				event.setCancelled(false);
			}
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			
			if(gods.contains(player)) {
				event.setCancelled(true);
				player.setHealth(player.getMaxHealth());
				player.setFoodLevel(20);
			}
		}
	}
	
}
