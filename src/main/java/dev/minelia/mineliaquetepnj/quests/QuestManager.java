package dev.minelia.mineliaquetepnj.quests;

import dev.minelia.mineliaquetepnj.MineliaQuetePNJ;
import dev.minelia.mineliaquetepnj.utils.DataVariants;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class QuestManager {

  private static final QuestManager INSTANCE = new QuestManager();

  public static QuestManager getInstance() {
    return INSTANCE;
  }

  private final List<QuestRarity> questRarities = new ArrayList<>();

  private QuestManager() {
    FileConfiguration config = MineliaQuetePNJ.getInstance().getConfig();
    for (String rarity : config.getConfigurationSection("quests").getKeys(false)) {
      String color = config.getString("quests." + rarity + ".color");
      int cost = config.getInt("quests." + rarity + ".cost");
      QuestRarity qr = new QuestRarity(Rarity.getByName(rarity), color, cost);
      for (String reward : config.getConfigurationSection("quests." + rarity + ".rewards").getKeys(false)) {
        int amount = config.getInt("quests." + rarity + ".rewards." + reward + ".amount");
        double probability = config.getDouble("quests." + rarity + ".rewards." + reward + ".probability");
        String item = config.getString("quests." + rarity + ".rewards." + reward + ".item");
        String translation = config.getString("quests." + rarity + ".rewards." + reward + ".translation");
        Reward r = new Reward(amount, probability, item, translation);
        qr.addReward(r);
      }
      for (String quest : config.getConfigurationSection("quests." + rarity + ".quests").getKeys(false)) {
        short id = Short.parseShort(quest);
        QuestType type = QuestType.valueOf(config.getString("quests." + rarity + ".quests." + quest + ".type"));
        int objective = config.getInt("quests." + rarity + ".quests." + quest + ".amount");
        Object object = getObject(config.getString("quests." + rarity + ".quests." + quest + ".object"), type);
        String description = config.getString("quests." + rarity + ".quests." + quest + ".description");
        Quest q = new Quest(id, type, objective, object, qr, description);
        qr.addQuest(q);
      }
      questRarities.add(qr);
    }
  }

  private Object getObject(String o, QuestType type) {
    switch (type) {
      case KILL:
        if (DataVariants.Mobs.contains(o)) {
          return DataVariants.Mobs.valueOf(o);
        }
        if (DataVariants.MobType.contains(o)) {
          return DataVariants.MobType.valueOf(o);
        }
        DataVariants.MobHostility.valueOf(o);
        return DataVariants.MobHostility.valueOf(o);
      case BREAK:
      case PLACE:
        if (o.equals("BLOCK")) {
          return null;
        }
        if (Material.getMaterial(o) != null) {
          return Material.getMaterial(o);
        }
        if (DataVariants.VariantBlocks.contains(o)) {
          return DataVariants.VariantBlocks.valueOf(o);
        }
        return DataVariants.BlockCategory.valueOf(o);
    }
    System.out.println("Error: getObject() returned null for " + o);
    return null;
  }

  public List<QuestRarity> getQuestRarities() {
    return questRarities;
    }

  public QuestRarity getQuestRarity(Rarity rarity) {
    for (QuestRarity qr : questRarities) {
      if (qr.getRarity().equals(rarity)) {
      return qr;
      }
    }
    return null;
  }

  public boolean isQuestBook(ItemStack item) {
    final net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
    return nmsItem.hasTag() && nmsItem.getTag().hasKey("questId");
  }

  public Quest getQuest(ItemStack item) {
    final net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
    return getQuestRarity(Rarity.getByName(nmsItem.getTag().getString("questRarity"))).getQuest(nmsItem.getTag().getShort("questId"));
  }
}


