package com.ProjectYassysas.Inventory;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackUtil {
	
	public static String deserialize(ItemStack i){
	  String[] parts = new String[5];
	  parts[0] = i.getType().name();
	  parts [1] = Integer.toString(i.getAmount());
	  parts [2] = String.valueOf(i.getDurability());
	  
	  if(i.getItemMeta().hasDisplayName()){
		  parts [3] = i.getItemMeta().getDisplayName(); 
	  }else{parts [3] = "(StringIsNull)";}
	  
	  if(i.getItemMeta().hasLore()){
		  List<String> lore = i.getItemMeta().getLore();
		  String loreString = StringUtils.join(lore, "'");
		  parts [4] = loreString; 
	  }else{parts [4] = "(StringIsNull)";}
	  
	  return StringUtils.join(parts, ";");
	}
	
	public static ItemStack serialize(String str){
		String[] a = str.split(";");
		
		ItemStack i = new ItemStack(Material.getMaterial(a[0]), Integer.parseInt(a[1]));
		i.setDurability((short) Integer.parseInt(a[2]));
		ItemMeta meta = i.getItemMeta();
		
		if(!(a[3].contains("StringIsNull"))){
			meta.setDisplayName(a[3]);
		}
		
		if(!(a[4].contains("(StringIsNull)"))){
			String[] rawLore = a[4].split("'");
			ArrayList<String> lore = new ArrayList<String>();
			lore.clear();
			for(String l: rawLore){lore.add(l);}
			meta.setLore(lore);	
		}
		
		i.setItemMeta(meta);
		return i;
	}
}
