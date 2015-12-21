package com.ProjectYassysas.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import com.ProjectYassysas.Main.PlayerFile;
import net.md_5.bungee.api.ChatColor;

public class Inventories{
	
	public static Inventory BackpackInv(){
		Inventory inv = Bukkit.getServer().createInventory(null, 27, ChatColor.GOLD + "Backpack");
		
		inv.setItem(0, Items.accessories);
		return inv;
	}
	
	public static Inventory AccessoryInv(Player p){
		Inventory inv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_GRAY + "Accessories");
		
		inv.setItem(0, Items.border);
		inv.setItem(1, Items.ringSlot);
		inv.setItem(2, Items.border);
		inv.setItem(3, Items.border);
		inv.setItem(4, Items.amuletSlot);
		inv.setItem(5, Items.border);
		inv.setItem(6, Items.border);
		inv.setItem(7, Items.ringSlot);
		inv.setItem(8, Items.border);
		
		PlayerFile.load(p);
		//if(PlayerFile.get().get("Accesorries.Amulet").getItemStack)
		
		
		
		
		
		return inv;
	}
}
