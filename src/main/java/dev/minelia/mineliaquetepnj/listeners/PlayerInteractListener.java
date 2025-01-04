package dev.minelia.mineliaquetepnj.listeners;

import dev.minelia.mineliaquetepnj.quests.Quest;
import dev.minelia.mineliaquetepnj.quests.QuestManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (!event.hasItem()) {
      return;
    }
    if (!QuestManager.getInstance().isQuestBook(event.getItem())) {
      return;
    }
    event.setCancelled(true);
    final Quest quest = QuestManager.getInstance().getQuest(event.getItem());
    if (!quest.isCompleted(event.getItem())) {
      event.getPlayer().sendMessage("§cVous n'avez pas encore terminé cette quête.");
      return;
    }
    for (int i = 0; i < event.getItem().getAmount(); i++) {
      quest.getQuestRarity().getRandomReward().give(event.getPlayer());
    }
    event.getPlayer().getInventory().remove(event.getItem());
  }

}
