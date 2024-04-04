package fr.izeleam.minelia.mineliaquetepnj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class QuestManager {

  private static final QuestManager instance = new QuestManager();

  private final Map<String, List<Quest>> quests;
  private final Map<String, List<Reward>> rewards;

  private QuestManager() {
    this.quests = new HashMap<>();
    this.rewards = new HashMap<>();
  }

  public static QuestManager getInstance() {
    return instance;
  }

  public void registerQuests(final Plugin plugin) {
    FileConfiguration config = plugin.getConfig();

    for (String questRarity : config.getConfigurationSection("quests").getKeys(false)) {
      List<Quest> quests = new ArrayList<>();
      List<Reward> rewards = new ArrayList<>();
      for (String reward : config.getConfigurationSection("quests." + questRarity + ".rewards").getKeys(false)) {
        rewards.add(new Reward(config.getInt("quests." + questRarity + ".rewards." + reward + ".amount"),
            config.getInt("quests." + questRarity + ".rewards." + reward + ".probability"),
            config.getString("quests." + questRarity + ".rewards." + reward + ".item")));
      }
      for (String quest : config.getConfigurationSection("quests." + questRarity + ".quests.").getKeys(false)) {
        quests.add(new Quest(QuestType.valueOf(config.getString("quests." + questRarity + ".quests." + quest + ".type")),
            config.getInt("quests." + questRarity + ".quests." + quest + ".amount"),
            config.getString("quests." + questRarity + ".quests." + quest + ".mobType"),
            config.getString("quests." + questRarity + ".quests." + quest + ".blockType")));
      }
      this.quests.put(questRarity, quests);
      this.rewards.put(questRarity, rewards);
    }
  }
}