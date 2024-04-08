package fr.izeleam.minelia.mineliaquetepnj;

import fr.izeleam.minelia.mineliaquetepnj.utils.DataVariants.BlockCategory;
import fr.izeleam.minelia.mineliaquetepnj.utils.DataVariants.MobHostility;
import fr.izeleam.minelia.mineliaquetepnj.utils.DataVariants.MobType;
import fr.izeleam.minelia.mineliaquetepnj.utils.DataVariants.Mobs;
import fr.izeleam.minelia.mineliaquetepnj.utils.DataVariants.VariantBlocks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class QuestManager {

  private static final QuestManager instance = new QuestManager();

  private final Map<String, List<Quest>> quests;
  private final Map<String, List<Reward>> rewards;
  private final Map<String, Integer> costs;

  private QuestManager() {
    this.quests = new HashMap<>();
    this.rewards = new HashMap<>();
    this.costs = new HashMap<>();
  }

  public static QuestManager getInstance() {
    return instance;
  }

  public void registerQuests(final Plugin plugin) {
    FileConfiguration config = plugin.getConfig();

    for (String questRarity : config.getConfigurationSection("quests").getKeys(false)) {
      List<Quest> quests = new ArrayList<>();
      List<Reward> rewards = new ArrayList<>();

      for (String questIndex : config.getConfigurationSection("quests." + questRarity + ".quests").getKeys(false)) {
        String type = config.getString("quests." + questRarity + ".quests." + questIndex + ".type");
        int amount = config.getInt("quests." + questRarity + ".quests." + questIndex + ".amount");
        String obj = config.getString("quests." + questRarity + ".quests." + questIndex + ".object");

        Object object = getObject(type, obj);
        quests.add(new Quest(Integer.parseInt(questIndex), QuestType.valueOf(type), amount, object));
      }

      for (String rewardIndex : config.getConfigurationSection("quests." + questRarity + ".rewards").getKeys(false)) {
        int amount = config.getInt("quests." + questRarity + ".rewards." + rewardIndex + ".amount");
        double probability = config.getDouble("quests." + questRarity + ".rewards." + rewardIndex + ".probability");
        String item = config.getString("quests." + questRarity + ".rewards." + rewardIndex + ".item");

        rewards.add(new Reward(amount == 0 ? 1 : amount, probability, item));
      }

      this.costs.put(questRarity, config.getInt("quests." + questRarity + ".levels"));
      this.quests.put(questRarity, quests);
      this.rewards.put(questRarity, rewards);
    }
  }

  private static Object getObject(String type, String obj) {
    Object object = null;
    if (type.equals("BLOCK")) {
      return null;
    }

    switch (type) {
      case "BREAK":
      case "PLACE":
        for (Material material : Material.values()) {
          if (material.name().equalsIgnoreCase(obj)) {
            object = material;
            break;
          }
        }
        for (BlockCategory blockCategory : BlockCategory.values()) {
          if (blockCategory.name().equalsIgnoreCase(obj)) {
            object = blockCategory;
            break;
          }
        }
        for (VariantBlocks variantBlocks : VariantBlocks.values()) {
          if (variantBlocks.name().equalsIgnoreCase(obj)) {
            object = variantBlocks;
            break;
          }
        }
        break;
      case "KILL":
        for (Mobs mob : Mobs.values()) {
          if (mob.name().equalsIgnoreCase(obj)) {
            object = mob;
            break;
          }
        }
        for (MobType mobType : MobType.values()) {
          if (mobType.name().equalsIgnoreCase(obj)) {
            object = mobType;
            break;
          }
        }
        for (MobHostility mobHostility : MobHostility.values()) {
          if (mobHostility.name().equalsIgnoreCase(obj)) {
            object = mobHostility;
            break;
          }
        }
        break;
      default:
        throw new IllegalArgumentException("Unknown quest type " + type);
    }
    return object;
  }

  public List<Quest> getQuests(String type) {
    if (!this.quests.containsKey(type)) {
      throw new IllegalArgumentException("No quests found for type " + type);
    }
    return this.quests.get(type);
  }

  public Map<String, List<Quest>> getQuests() {
    return quests;
  }

  public Map<String, List<Reward>> getRewards() {
    return rewards;
  }

  public List<Reward> getRewards(String type) {
    if (!this.rewards.containsKey(type)) {
      throw new IllegalArgumentException("No rewards found for type " + type);
    }
    return this.rewards.get(type);
  }

  public int getCost(String type) {
    if (!this.costs.containsKey(type)) {
      throw new IllegalArgumentException("No cost found for type " + type);
    }
    return this.costs.get(type);
  }

  public String getQuestRarity(Quest quest) {
    for (Map.Entry<String, List<Quest>> entry : quests.entrySet()) {
      if (entry.getValue().contains(quest)) {
        return entry.getKey();
      }
    }
    return null;
  }

  public static Quest getRandomQuest(String rarity) {
    List<Quest> quests = instance.getQuests(rarity);
    return quests.get((int) (Math.random() * quests.size()));
  }
}