package dev.minelia.mineliaquetepnj;

import dev.minelia.mineliaquetepnj.commands.GiveQuestCommand;
import dev.minelia.mineliaquetepnj.listeners.BlockListener;
import dev.minelia.mineliaquetepnj.listeners.EntityListener;
import dev.minelia.mineliaquetepnj.listeners.PlayerConnectionListener;
import dev.minelia.mineliaquetepnj.listeners.PlayerInteractListener;
import dev.minelia.mineliaquetepnj.listeners.PlayerMoveListener;
import dev.minelia.mineliaquetepnj.npc.NPC;
import dev.minelia.mineliaquetepnj.npc.NPCManager;
import dev.minelia.mineliaquetepnj.quests.QuestManager;
import dev.minelia.mineliaquetepnj.quests.Rarity;

import java.io.File;
import java.io.InputStreamReader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineliaQuetePNJ extends JavaPlugin {

  private static MineliaQuetePNJ instance;

  public static MineliaQuetePNJ getInstance() {
    if (instance == null) {
      throw new IllegalStateException("The plugin has not been enabled yet!");
    }
    return instance;
  }

  @Override
  public void onEnable() {
    instance = this;

    saveResource("permissions.yml", false);
    saveDefaultConfig();
    
    FileConfiguration config = getConfig();
    File configFile = new File(getDataFolder(), "config.yml");

    if (configFile.exists()) {
      YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(getResource("config.yml")));
      for (String key : defaultConfig.getKeys(true)) {
        if (!config.contains(key)) {
          config.set(key, defaultConfig.get(key));
        }
      }
      saveConfig();
    }

    // Registering the commands
    Bukkit.getPluginCommand("questgive").setExecutor(new GiveQuestCommand());

    // Registering the listeners
    Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
    Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
    Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(), this);
    Bukkit.getPluginManager().registerEvents(NPC.getNPC(), this);
    Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
    Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);

    // Spawn the NPC
    NPC npc = NPC.getNPC();
    npc.spawn();
    for (Player player : Bukkit.getOnlinePlayers()) {
      npc.show(player);
    }

    // Initialize the managers
    NPCManager npcManager = new NPCManager(this);
    QuestManager.getInstance();
  }

  @Override
  public void onDisable() {
    saveConfig();
  }
}
