package dev.minelia.mineliaquetepnj.listeners;

import dev.minelia.mineliaquetepnj.quests.Quest;
import dev.minelia.mineliaquetepnj.quests.QuestManager;
import dev.minelia.mineliaquetepnj.quests.QuestType;
import dev.minelia.mineliaquetepnj.utils.DataVariants.BlockCategory;
import dev.minelia.mineliaquetepnj.utils.DataVariants.VariantBlocks;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    handleEvent(event, QuestType.BREAK, event.getPlayer());
  }

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    handleEvent(event, QuestType.PLACE, event.getPlayer());
  }

  private void handleEvent(BlockEvent event, QuestType questType, Player player) {
    if (player == null) {
      return;
    }

    for (ItemStack item : player.getInventory().getContents()) {
      if (item == null || !QuestManager.getInstance().isQuestBook(item)) {
        continue;
      }
      final Quest quest = QuestManager.getInstance().getQuest(item);
      if (!quest.getType().equals(questType)) {
        continue;
      }

      Object object = quest.getObject();
      Block block = event.getBlock();
      if (object == null || block.getType().equals(object) || isBlockInCategory(object, block) || isBlockVariant(object, block)) {
        quest.setProgress(item, quest.getProgress(item) + 1);
      }
    }
  }

  private boolean isBlockInCategory(Object object, Block block) {
    return object instanceof BlockCategory && ((BlockCategory) object).getMaterials().contains(block.getType());
  }

  private boolean isBlockVariant(Object object, Block block) {
    return object instanceof VariantBlocks && VariantBlocks.contains(block.getType(), block.getType().getMaxDurability());
  }
}