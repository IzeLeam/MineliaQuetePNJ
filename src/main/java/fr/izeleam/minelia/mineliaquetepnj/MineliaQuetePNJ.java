package fr.izeleam.minelia.mineliaquetepnj;

import fr.izeleam.minelia.mineliaquetepnj.commands.GuiCommand;
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

    System.out.println("Registering commands...");
    Bukkit.getPluginCommand("gui").setExecutor(new GuiCommand());

    saveDefaultConfig();

    //System.out.println("Spawning NPC...");
    //EntityNPC.getNPC().spawn();
  }

  @Override
  public void onDisable() {
    saveConfig();
  }
}
