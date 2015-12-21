package com.ProjectYassysas.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class ItemStackUtil {
	private static String getEnchants(ItemStack i){
		List<String> e = new ArrayList<String>();
		Map<Enchantment, Integer> en = new HashMap<Enchantment, Integer>();
		for (Enchantment t : en.keySet()){
			e.add(t.getName() + ":" + en.get(t));
		}
		return StringUtils.join(e, ",");
	}
	
	@SuppressWarnings("deprecation")
	public static String deserialize(ItemStack i){
	  String[] parts = new String[7];
	  parts[0] = i.getType().name();
	  parts [1] = Integer.toString(i.getAmount());
	  parts [2] = String.valueOf(i.getDurability());
	  parts [3] = i.getItemMeta().getDisplayName();
	  List<String> lore = i.getItemMeta().getLore();
	  String loreString = StringUtils.join(lore, "|"); // shows up as "lore|lore|lore|lore"     still need to mess with serializion
	  parts [4] = loreString;
	  parts [5] = String.valueOf (i.getData().getData());
	  parts [6] = getEnchants(i);
	  return StringUtils.join(parts, ";");
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack serialize(String str){

		String[] a = str.split(";");
		ItemStack i = new ItemStack(Material.getMaterial(a[0]), Integer.parseInt(a[1]));
		i.setDurability((short) Integer.parseInt(a[2]));
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(a[3]);
		i.setItemMeta(meta);
		MaterialData data = i.getData();
		data.setData((byte) Integer.parseInt(a[5]));
		i.setData(data);
		String[] parts = a[6].split(",");
		for (String s : parts){
			String label = s.split(":")[0];
			String amplifier = s.split(":")[1];
			Enchantment type = Enchantment.getByName(label);
			if (type == null){
				continue;
			}
			int f;
			try{
				f = Integer.parseInt(amplifier);
			} catch (Exception ex) {
				continue;
			}
			i.addEnchantment(type, f);
		}
		return i;
	}
}
