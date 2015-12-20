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
  
    public static File getfolder(){return folder;}
  
    public static File getfile(){return file;}
  
    public static void load(Player p){
        file = new File(df, "player data" + File.separator + p.getUniqueId() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
    }
  
    public static FileConfiguration get(){return config;}
  
    public static void save(){
        try {
            config.save(file);
        }catch(Exception e){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + file.getName() + "!");
        }
    }
}
