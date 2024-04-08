package fr.izeleam.minelia.mineliaquetepnj.listeners;

import fr.izeleam.minelia.mineliaquetepnj.Quest;
import fr.izeleam.minelia.mineliaquetepnj.QuestPlayer;
import fr.izeleam.minelia.mineliaquetepnj.QuestType;
import fr.izeleam.minelia.mineliaquetepnj.utils.DataVariants.BlockCategory;
import fr.izeleam.minelia.mineliaquetepnj.utils.DataVariants.VariantBlocks;
import fr.izeleam.minelia.mineliaquetepnj.utils.Pair;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockListener implements Listener {

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    handleEvent(event, QuestType.BREAK, QuestPlayer.getPlayer(event.getPlayer()));
  }

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    handleEvent(event, QuestType.PLACE, QuestPlayer.getPlayer(event.getPlayer()));
  }

  private void handleEvent(BlockEvent event, QuestType questType, QuestPlayer player) {
    if (player == null || player.getActiveQuests().isEmpty()) {
      return;
    }

    List<Pair<Quest, Integer>> toRemove = new ArrayList<>();
    for (Pair<Quest, Integer> pair : player.getActiveQuests()) {
      Quest quest = pair.getKey();
      if (!quest.getType().equals(questType)) {
        continue;
      }

      Object object = quest.getObject();
      Block block = event.getBlock();
      if (object == null || block.getType().equals(object) || isBlockInCategory(object, block) || isBlockVariant(object, block)) {
        toRemove.addAll(player.progressQuest(quest, 1));
      }
    }
    player.getActiveQuests().removeAll(toRemove);
  }

  private boolean isBlockInCategory(Object object, Block block) {
    return object instanceof BlockCategory && ((BlockCategory) object).getMaterials().contains(block.getType());
  }

  private boolean isBlockVariant(Object object, Block block) {
    return object instanceof VariantBlocks && VariantBlocks.contains(block.getType(), block.getType().getMaxDurability());
  }
}