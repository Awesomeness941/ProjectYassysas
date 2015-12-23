package com.ProjectYassysas.Inventory;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.ProjectYassysas.Main.PlayerFile;

public class Listener implements org.bukkit.event.Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(!PlayerFile.exists(p)){
			PlayerFile.defaultPlayerFile(p);
		}else{
			PlayerFile.get(p).set("PlayerInfo.LastName", p.getPlayer().getDisplayName());
			PlayerFile.save();
		}
		
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
			p.openInventory(Inventories.BackpackInv(p));
		}
		
		if(e.getItem().getType().equals(Material.LEASH)&& e.getItem().getItemMeta().getDisplayName().contains("Amulet")){
			e.setCancelled(true);
		}
		if((e.getItem().getType().equals(Material.STONE_BUTTON) || e.getItem().getType().equals(Material.WOOD_BUTTON)) && e.getItem().getItemMeta().getDisplayName().contains("Ring")){
			e.setCancelled(true);
		}
		p.updateInventory();
	}
	
	@EventHandler
	public void onPlayerSpawn(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		p.getInventory().setItem(8, Items.backpack);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		Player p = e.getEntity();
		Location loc = p.getLocation();
		World world = loc.getWorld();
		FileConfiguration pf = PlayerFile.get(p);
		
		e.getDrops().remove(Items.backpack);
		
		if(p.getGameMode() != GameMode.CREATIVE){
		if(!pf.get("Inventory.Accessories.Amulet").equals("none")){
			world.dropItem(loc, ItemStackUtil.serialize(pf.get("Inventory.Accessories.Amulet") + ""));
			pf.set("Inventory.Accessories.Amulet", "none");
			PlayerFile.save();
		}
		if(!pf.get("Inventory.Accessories.Ring1").equals("none")){
			world.dropItem(loc, ItemStackUtil.serialize(pf.get("Inventory.Accessories.Ring1") + ""));
			pf.set("Inventory.Accessories.Ring1", "none");
			PlayerFile.save();
		}
		if(!pf.get("Inventory.Accessories.Ring2").equals("none")){
			world.dropItem(loc, ItemStackUtil.serialize(pf.get("Inventory.Accessories.Ring2") + ""));
			pf.set("Inventory.Accessories.Ring2", "none");
			PlayerFile.save();
			}
		}
	}
	
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e){
		if(e.getItemDrop().getItemStack().isSimilar(Items.backpack)){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		Location loc = p.getLocation();
		World world = loc.getWorld();
		FileConfiguration pf = PlayerFile.get(p);
		if(e.getCurrentItem() == null){return;}
		
		if(e.getCurrentItem().isSimilar(Items.backpack)){
			e.setCancelled(true);
			p.openInventory(Inventories.BackpackInv(p));
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

			if(e.getCurrentItem().getType().equals(Material.LEASH)&& e.getCurrentItem().getItemMeta().getDisplayName().contains("Amulet")){
				
				if(e.getClickedInventory().getType() != InventoryType.CHEST){
					if ((pf.get("Inventory.Accessories.Amulet").equals("none"))){
						
						ItemStack chestAmulet = new ItemStack(e.getCurrentItem());
						chestAmulet.setAmount(1);
						
						pf.set("Inventory.Accessories.Amulet", ItemStackUtil.deserialize(chestAmulet));
						PlayerFile.save();
						
						int amountAmulet = e.getCurrentItem().getAmount();
						if(amountAmulet != 1){
							
							ItemStack invAmulet = e.getCurrentItem();
							invAmulet.setAmount(amountAmulet - 1);
							e.setCurrentItem(invAmulet);
							
						}else{
							e.setCurrentItem(null);
						}
						p.updateInventory();
						p.openInventory(Inventories.AccessoryInv(p));
					}
				}else{
					if(p.getInventory().firstEmpty() == -1){
						world.dropItem(loc, ItemStackUtil.serialize(pf.get("Inventory.Accessories.Amulet") + ""));
					}else{
						p.getInventory().addItem(ItemStackUtil.serialize(pf.get("Inventory.Accessories.Amulet") + ""));
						p.updateInventory();
					}

					pf.set("Inventory.Accessories.Amulet", "none");
					PlayerFile.save();
					p.openInventory(Inventories.AccessoryInv(p));
				}
			}
			if((e.getCurrentItem().getType().equals(Material.STONE_BUTTON) || e.getCurrentItem().getType().equals(Material.WOOD_BUTTON)) && e.getCurrentItem().getItemMeta().getDisplayName().contains("Ring")){
				if(e.getClickedInventory().getType() != InventoryType.CHEST){
					
				if(pf.get("Inventory.Accessories.Ring1").equals("none")){
					ItemStack chestRing1 = new ItemStack(e.getCurrentItem());
					chestRing1.setAmount(1);
					
					pf.set("Inventory.Accessories.Ring1", ItemStackUtil.deserialize(chestRing1));
					PlayerFile.save();

					int amountRing1 = e.getCurrentItem().getAmount();
					if(amountRing1 != 1){
						
						ItemStack invRing1 = e.getCurrentItem();
						invRing1.setAmount(amountRing1);
						e.setCurrentItem(invRing1);
						
					}else{
						e.setCurrentItem(null);
					}
					p.updateInventory();

					p.openInventory(Inventories.AccessoryInv(p));
				}
				
					try{
						if(!(pf.get("Inventory.Accessories.Ring1").equals("none")) && (pf.get("Inventory.Accessories.Ring2").equals("none"))){
							ItemStack chestRing2 = new ItemStack(e.getCurrentItem());
							chestRing2.setAmount(1);
							
							pf.set("Inventory.Accessories.Ring2", ItemStackUtil.deserialize(chestRing2));
							PlayerFile.save();

							int amountRing2 = e.getCurrentItem().getAmount();
							if(amountRing2 != 1){
								
								ItemStack invRing2 = e.getCurrentItem();
								invRing2.setAmount(amountRing2 - 1);
								e.setCurrentItem(invRing2);
								
							}else{
								e.setCurrentItem(null);
							}
							p.updateInventory();

							p.openInventory(Inventories.AccessoryInv(p));
						}
					}catch(NullPointerException npe){} // Couldn't figure out what was causing the npe so I just did this doesn't seem to break anything anyways
				
				}else{
					
					if((!(pf.get("Inventory.Accessories.Ring1").equals("none"))) && e.getSlot() == 1){
						
						if(p.getInventory().firstEmpty() == -1){
							world.dropItem(loc, ItemStackUtil.serialize(pf.get("Inventory.Accessories.Ring1") + ""));
						}else{
							p.getInventory().addItem(ItemStackUtil.serialize(pf.get("Inventory.Accessories.Ring1") + ""));
							p.updateInventory();
						}

						pf.set("Inventory.Accessories.Ring1", "none");
						PlayerFile.save();
						p.openInventory(Inventories.AccessoryInv(p));

					}
					
					if((!(pf.get("Inventory.Accessories.Ring2").equals("none"))) && e.getSlot() == 7){
						
						if(p.getInventory().firstEmpty() == -1){
							world.dropItem(loc, ItemStackUtil.serialize(pf.get("Inventory.Accessories.Ring2") + ""));
						}else{
							p.getInventory().addItem(ItemStackUtil.serialize(pf.get("Inventory.Accessories.Ring2") + ""));
							p.updateInventory();
						}

						pf.set("Inventory.Accessories.Ring2", "none");
						PlayerFile.save();
						p.openInventory(Inventories.AccessoryInv(p));
					}
				}
			}
		}
	}
}
