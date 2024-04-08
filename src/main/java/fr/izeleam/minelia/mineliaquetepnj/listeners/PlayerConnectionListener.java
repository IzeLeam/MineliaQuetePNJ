package fr.izeleam.minelia.mineliaquetepnj.listeners;

import fr.izeleam.minelia.mineliaquetepnj.NPC;
import fr.izeleam.minelia.mineliaquetepnj.QuestPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    QuestPlayer.addPlayer(new QuestPlayer(event.getPlayer()));

    NPC.getNPC().show(event.getPlayer());
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    QuestPlayer.removePlayer(QuestPlayer.getPlayer(event.getPlayer()));
  }
}
