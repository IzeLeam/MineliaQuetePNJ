package dev.minelia.mineliaquetepnj.listeners;

import dev.minelia.mineliaquetepnj.quests.Quest;
import dev.minelia.mineliaquetepnj.quests.QuestManager;
import dev.minelia.mineliaquetepnj.utils.DataVariants.MobHostility;
import dev.minelia.mineliaquetepnj.utils.DataVariants.MobType;
import dev.minelia.mineliaquetepnj.utils.DataVariants.Mobs;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityListener implements Listener {

  @EventHandler
  public void onEntityDeath(EntityDeathEvent event) {
  if (event.getEntity().getKiller() == null) {
      return;
    }

    final Player player = event.getEntity().getKiller();

    for (ItemStack item : player.getInventory().getContents()) {
      if (item == null || !QuestManager.getInstance().isQuestBook(item)) {
        continue;
      }
      final Quest quest = QuestManager.getInstance().getQuest(item);

      Object object = quest.getObject();
      Entity entity = event.getEntity();
      if (object == null || entity.getType().equals(object) || isMob(object, entity) || isMobType(object, entity) || isMobHostility(object, entity)) {
        quest.setProgress(item, quest.getProgress(item) + 1);
      }
    }
  }

  private boolean isMob(Object object, Entity entity) {
    return object instanceof Mobs && ((Mobs) object).name().equals(entity.getType().toString());
  }

  private boolean isMobType(Object object, Entity entity) {
    return object instanceof MobType && ((MobType) object).getMobs().contains(Mobs.valueOf(entity.getType().toString()));
  }

  private boolean isMobHostility(Object object, Entity entity) {
    return object instanceof MobHostility && ((MobHostility) object).getMobs().contains(Mobs.valueOf(entity.getType().toString()));
  }
}
