package com.ProjectYassysas.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.ProjectYassysas.Main.PlayerFile;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Inventories{
	
	public static Inventory BackpackInv(Player p){
		Inventory inv = Bukkit.getServer().createInventory(null, 27, ChatColor.GOLD + "Backpack");
		
		inv.setItem(0, Items.accessories);
		
		return inv;
	}
	
	public static Inventory AccessoryInv(Player p){
		Inventory inv = Bukkit.getServer().createInventory(null, 9, ChatColor.DARK_GRAY + "Accessories");
		FileConfiguration pf = PlayerFile.get(p);
		
		inv.setItem(0, Items.border);
		inv.setItem(2, Items.border);
		inv.setItem(3, Items.border);
		inv.setItem(5, Items.border);
		inv.setItem(6, Items.border);
		inv.setItem(8, Items.border);
		
		if(pf.get("Inventory.Accessories.Ring1").equals("none") && (!pf.get("Inventory.Accessories.Ring2").equals("none"))){
			String ring2Str = pf.get("Inventory.Accessories.Ring2") + "";

			pf.set("Inventory.Accessories.Ring1", ring2Str);
			PlayerFile.save();
			pf.set("Inventory.Accessories.Ring2", "none");
			PlayerFile.save();
		}
		
		if(!(pf.get("Inventory.Accessories.Ring1").equals("none"))){
			ItemStack ring1 = ItemStackUtil.serialize(pf.get("Inventory.Accessories.Ring1") + "");
			
			inv.setItem(1, ring1);
		}else{inv.setItem(1, Items.ringSlot);}
		
		if(!(pf.get("Inventory.Accessories.Amulet").equals("none"))){
			ItemStack amulet = ItemStackUtil.serialize(pf.get("Inventory.Accessories.Amulet") + "");
			
			inv.setItem(4, amulet);
		}else{inv.setItem(4, Items.amuletSlot);}
		
		if(!(pf.get("Inventory.Accessories.Ring2").equals("none"))){
			ItemStack ring2 = ItemStackUtil.serialize(pf.get("Inventory.Accessories.Ring2") + "");
			
			inv.setItem(7, ring2);
		}else{inv.setItem(7, Items.ringSlot);}
		
		return inv;
	}
}
