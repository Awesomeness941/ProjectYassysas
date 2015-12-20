package com.ProjectYassysas.Main;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static Main plugin;
	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new com.ProjectYassysas.Inventory.Listener(), this);
		getServer().getPluginManager().registerEvents(new com.ProjectYassysas.Attributes.Listener(), this);
		plugin = this;
	}
	@Override
	public void onDisable(){}
}
