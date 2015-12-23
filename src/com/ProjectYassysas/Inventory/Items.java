package com.ProjectYassysas.Inventory;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;


public class Items{

	//AccessoryInv
	public static ItemStack border = Item(Material.STAINED_GLASS_PANE, 1, 8, ChatColor.BLACK + "", null);
	public static ItemStack ringSlot = Item(Material.BARRIER, 1, 15, ChatColor.GRAY + "No ring equipped", null);
	public static ItemStack amuletSlot = Item(Material.BARRIER, 1, 15, ChatColor.GRAY + "No amulet equipped", null);
	
	//Backpack
	public static ItemStack backpack = Item(Material.ENDER_CHEST, 1, 0, ChatColor.GOLD + "Backpack", new String[] {ChatColor.GRAY + "Right or left click to open"});
	public static ItemStack accessories = Item(Material.LEASH, 1, 0, ChatColor.GRAY + "Accessories", null);
	
	
	/**Ex. Item(Material.Grass, 5, 0, "ExampleItem", new String[] {"example lore line 1", "line two of example lore"})*/
	public static ItemStack Item(Material material, int amount, int data, String displayName, String[] lore){
		ItemStack item = new ItemStack(material, amount, (short) data);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(displayName);
		ArrayList<String> itemLore = new ArrayList<String>();
		if (lore != null){
			for (String l : lore){
				itemLore.add(l);
			}
			itemMeta.setLore(itemLore);
		}
		item.setItemMeta(itemMeta);
		return item;
	}

	/** Used to add the glow effect to Itemstacks*/
	public static ItemStack addGlow(ItemStack item){ 
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		  NBTTagCompound tag = null;
		  if (!nmsStack.hasTag()){
		      tag = new NBTTagCompound();
		      nmsStack.setTag(tag);
		}
		  if (tag == null) tag = nmsStack.getTag();
		  NBTTagList ench = new NBTTagList();
		  tag.set("ench", ench);
		  nmsStack.setTag(tag);
		  return CraftItemStack.asCraftMirror(nmsStack);
	}
}
