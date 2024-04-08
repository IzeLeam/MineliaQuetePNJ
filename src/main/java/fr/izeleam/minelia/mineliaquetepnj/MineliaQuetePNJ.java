package fr.izeleam.minelia.mineliaquetepnj;

import fr.izeleam.minelia.mineliaquetepnj.commands.GiveQuestCommand;
import fr.izeleam.minelia.mineliaquetepnj.listeners.BlockListener;
import fr.izeleam.minelia.mineliaquetepnj.listeners.PlayerConnectionListener;
import fr.izeleam.minelia.mineliaquetepnj.utils.Database;
import org.bukkit.Bukkit;
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

    // Initialize the configuration
    if (!getDataFolder().exists()) {
      getDataFolder().mkdir();
    }
    saveDefaultConfig();

    // Registering the commands
    Bukkit.getPluginCommand("questgive").setExecutor(new GiveQuestCommand());

    // Registering the listeners
    Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
    Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(), this);
    Bukkit.getPluginManager().registerEvents(NPC.getNPC(), this);

    // Registering the quests
    QuestManager.getInstance().registerQuests(this);

    // Spawn the NPC
    NPC npc = NPC.getNPC();
    npc.spawn();
    for (Player player : Bukkit.getOnlinePlayers()) {
      npc.show(player);
    }
  }

  @Override
  public void onDisable() {
    saveConfig();

    // Save the players advancement
    for (Player player : Bukkit.getOnlinePlayers()) {
      QuestPlayer.removePlayer(QuestPlayer.getPlayer(player));
    }
  }
}
