package fr.izeleam.minelia.mineliaquetepnj;

import fr.izeleam.minelia.mineliaquetepnj.commands.GuiCommand;
import fr.izeleam.minelia.mineliaquetepnj.listeners.BlockListener;
import fr.izeleam.minelia.mineliaquetepnj.listeners.PlayerConnectionListener;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineliaQuetePNJ extends JavaPlugin {

  private static MineliaQuetePNJ instance;

  public static MineliaQuetePNJ getInstance() {
    if (instance == null) {
      throw new IllegalStateException("The plugin has not been enabled yet!");
    }
    return instance;
  }

  /*
  quests:
  mystery:
    levels: 50
    rewards:
      - amount: 50000
        probability: 39.8
      - amount: 100000
        probability: 10
    quests:
      - type: KILL
        amount: 2000
        mobType: MONSTER
      - type: BREAK
        amount: 2000
        blockType: BLOCK
   */

  @Override
  public void onEnable() {
    instance = this;

    if (!getDataFolder().exists()) {
      System.out.println("The plugin folder does not exist, creating it...");
      if (getDataFolder().mkdir()) {
        System.out.println("The plugin folder has been created.");
      } else {
        System.out.println("The plugin folder could not be created.");
      }
    }
    saveDefaultConfig();

    System.out.println("Registering commands...");
    Bukkit.getPluginCommand("gui").setExecutor(new GuiCommand());

    System.out.println("Registering listeners...");
    Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
    Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(), this);

    QuestManager.getInstance().registerQuests(this);

    //System.out.println("Spawning NPC...");
    //EntityNPC.getNPC().spawn();
  }

  @Override
  public void onDisable() {
    saveConfig();
  }
}
