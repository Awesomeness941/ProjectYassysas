package com.ProjectYassysas.Inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.ProjectYassysas.Main.PlayerFile;

public class Listener implements org.bukkit.event.Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
			PlayerFile.create(p);
			PlayerFile.save();
		
		if(p.getInventory().getItem(8) == null || p.getInventory().getItem(8) != Items.backpack){
			p.getInventory().setItem(8, Items.backpack);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(e.getItem() == null){return;}
		if(e.getItem().isSimilar(Items.backpack)){
			if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){return;}
			e.setCancelled(true);
			p.openInventory(Inventories.BackpackInv());
		}
	}
	
	@EventHandler
	public void onPlayerSpawn(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		p.getInventory().setItem(8, Items.backpack);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		e.getDrops().remove(Items.backpack);
	}
	
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e){
		if(e.getItemDrop().getItemStack().isSimilar(Items.backpack)){
			e.setCancelled(true);
			Player p = e.getPlayer();
			
			  p.sendMessage(ItemStackUtil.deserialize(e.getItemDrop().getItemStack()));
		}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem() == null){return;}
		
		if(e.getCurrentItem().isSimilar(Items.backpack)){
			e.setCancelled(true);
			p.openInventory(Inventories.BackpackInv());
		}
		
		if(e.getCurrentItem().isSimilar(Items.accessories)){
			e.setCancelled(true);
			p.openInventory(Inventories.AccessoryInv(p));
		}
		
		if(p.getOpenInventory().getTitle().equals(ChatColor.GOLD + "Backpack")){
			e.setCancelled(true);
		}
		
		if(p.getOpenInventory().getTitle().equals(ChatColor.DARK_GRAY + "Accessories")){
			e.setCancelled(true);
			if(!e.getCurrentItem().hasItemMeta()){return;}
			if(e.getCurrentItem().getType().equals(Material.LEASH) && e.getCurrentItem().getItemMeta().getDisplayName().contains("Amulet")){
				/*
				if(e.getClickedInventory().getType() != InventoryType.CHEST){
					PlayerFile.load(p);
					PlayerFile.get().set("Accesorries.Amulet", e.getCurrentItem());
					PlayerFile.save();
					
					e.getCurrentItem().setType(null);
					p.updateInventory();
					
					p.openInventory(Inventories.AccessoryInv(p));
				}
				*/
			}
			if((e.getCurrentItem().getType().equals(Material.STONE_BUTTON) || e.getCurrentItem().getType().equals(Material.WOOD_BUTTON)) && e.getCurrentItem().getItemMeta().getDisplayName().contains("Ring")){
				
				/*
				PlayerFile.load(p);
				PlayerFile.get().set("Accesorries.Ring1", e.getCurrentItem());
				PlayerFile.save();
				
				e.getCurrentItem().setType(null);
				p.updateInventory();
				
				p.openInventory(Inventories.AccessoryInv(p));
				
				
				
				
				PlayerFile.load(p);
				PlayerFile.get().set("Accesorries.Ring2", e.getCurrentItem());
				PlayerFile.save();
				
				e.getCurrentItem().setType(null);
				p.updateInventory();
				
				p.openInventory(Inventories.AccessoryInv(p));
				*/
				
				
				
			}
		}
	}
}
