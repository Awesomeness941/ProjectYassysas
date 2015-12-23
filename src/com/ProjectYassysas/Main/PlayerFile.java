package com.ProjectYassysas.Main;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerFile{

    static File file;
    static FileConfiguration config;
    static File folder = new File(Main.plugin.getDataFolder(), "player data" + File.separator);
    static File df = Main.plugin.getDataFolder();
  
    public static void create(Player p){
        file = new File(df, "player data" + File.separator + p.getUniqueId() + ".yml");
        if (!df.exists()) df.mkdir();
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch(Exception e){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + file.getName() + "!");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
  
    public static boolean exists(Player p){
    	load(p);
    	if (!df.exists()) df.mkdir();
        if (file.exists()){return true;}
		return false;
    }
    
    public static File getfolder(Player p){
    	load(p);
    	return folder;
    	}
  
    public static File getfile(Player p){
    	load(p);
    	return file;
    	}
  
    private static void load(Player p){
        file = new File(df, "player data" + File.separator + p.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
    }
  
    public static FileConfiguration get(Player p){
    	load (p);
    	return config;
    	}
  
    public static void save(){
        try {
            config.save(file);
        }catch(Exception e){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + file.getName() + "!");
        }
    }
    
    
    public static void defaultPlayerFile(Player p){
		PlayerFile.create(p);
		FileConfiguration pf = PlayerFile.get(p);
		
		//PlayerInfo Section
		pf.set("PlayerInfo.LastName", p.getPlayer().getDisplayName());
		PlayerFile.save();
		
		//Accessories Section
		pf.set("Inventory.Accessories.Ring1", "none");
		PlayerFile.save();
		pf.set("Inventory.Accessories.Amulet", "none");
		PlayerFile.save();
		pf.set("Inventory.Accessories.Ring2", "none");
		PlayerFile.save();
    }
}
