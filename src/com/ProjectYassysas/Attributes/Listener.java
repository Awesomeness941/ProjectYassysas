package com.ProjectYassysas.Attributes;

import org.bukkit.Bukkit;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EntityType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;

@SuppressWarnings({ "deprecation", "unused" })
public class Listener implements org.bukkit.event.Listener{

	@EventHandler
	public void Damage(EntityDamageEvent e){
		//if(e.getEntityType()!=EntityType.PLAYER){
		e.setDamage(0);
		
		//}
	}
	
	
	
	
	
	
}
