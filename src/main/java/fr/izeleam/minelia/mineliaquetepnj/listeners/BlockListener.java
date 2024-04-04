package fr.izeleam.minelia.mineliaquetepnj.listeners;

import fr.izeleam.minelia.mineliaquetepnj.Quest;
import fr.izeleam.minelia.mineliaquetepnj.QuestPlayer;
import fr.izeleam.minelia.mineliaquetepnj.QuestType;
import fr.izeleam.minelia.mineliaquetepnj.Reward;
import fr.izeleam.minelia.mineliaquetepnj.utils.Pair;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    QuestPlayer player = QuestPlayer.getPlayer(event.getPlayer());
    assert player != null;

    if (player.getActiveQuests().isEmpty()) {
      return;
    }

    List<Pair<Quest, Integer>> toRemove = new ArrayList<>();
    for (Pair<Quest, Integer> pair : player.getActiveQuests()) {
      Quest quest = pair.getKey();
      int progress = pair.getValue();

      if (!quest.getType().equals(QuestType.BREAK)) {
        continue;
      }

      if (event.getBlock().getType().name().contains(quest.getBlockType())) {
        pair.setValue(progress + 1);
        if (progress + 1 == quest.getAmount()) {
          player.getPlayer().sendMessage("§aYou have completed the quest: " + quest);
          toRemove.add(pair);
          Reward.giveReward(player.getPlayer(), quest.getRarity());
        }
      }
    }
    for (Pair<Quest, Integer> pair : toRemove) {
      player.getActiveQuests().remove(pair);
    }
  }

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    QuestPlayer player = QuestPlayer.getPlayer(event.getPlayer());
    assert player != null;

    if (player.getActiveQuests().isEmpty()) {
      return;
    }

    for (Pair<Quest, Integer> pair : player.getActiveQuests()) {
      Quest quest = pair.getKey();
      int progress = pair.getValue();

      if (!quest.getType().equals(QuestType.PLACE)) {
        continue;
      }

      if (quest.getBlockType().contains(event.getBlock().getType().name())) {
        pair.setValue(progress + 1);
        if (progress == quest.getAmount()) {
          player.getPlayer().sendMessage("§aYou have completed the quest: " + quest);
          player.getActiveQuests().remove(pair);
          Reward.giveReward(player.getPlayer(), quest.getRarity());
        }
      }
    }
  }
}