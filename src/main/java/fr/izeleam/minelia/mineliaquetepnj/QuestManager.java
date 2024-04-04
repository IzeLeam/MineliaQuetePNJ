package fr.izeleam.minelia.mineliaquetepnj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String blockType = config.getString("quests." + questRarity + ".quests." + questIndex + ".blockType");
        String mobType = config.getString("quests." + questRarity + ".quests." + questIndex + ".mobType");

        quests.add(new Quest(QuestType.valueOf(type), amount, blockType, mobType));
      }

      for (String rewardIndex : config.getConfigurationSection("quests." + questRarity + ".rewards").getKeys(false)) {
        int amount = config.getInt("quests." + questRarity + ".rewards." + rewardIndex + ".amount");
        double probability = config.getDouble("quests." + questRarity + ".rewards." + rewardIndex + ".probability");
        String item = config.getString("quests." + questRarity + ".rewards." + rewardIndex + ".item");

        rewards.add(new Reward(amount, probability, item));
      }

      this.costs.put(questRarity, config.getInt("quests." + questRarity + ".levels"));
      this.quests.put(questRarity, quests);
      this.rewards.put(questRarity, rewards);
    }
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
}