package dev.minelia.mineliaquetepnj.listeners;

import dev.minelia.mineliaquetepnj.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    NPC.getNPC().show(event.getPlayer());
  }
}
