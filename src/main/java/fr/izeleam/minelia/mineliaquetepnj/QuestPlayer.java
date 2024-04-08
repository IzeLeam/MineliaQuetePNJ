package fr.izeleam.minelia.mineliaquetepnj;

import fr.izeleam.minelia.mineliaquetepnj.utils.Database;
import fr.izeleam.minelia.mineliaquetepnj.utils.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.entity.Player;

public class QuestPlayer{

  private static final Set<QuestPlayer> players = new HashSet<>();
  private final Player player;
  private final List<Pair<Quest, Integer>> activeQuests = new ArrayList<>();

  public QuestPlayer(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

  public List<Pair<Quest, Integer>> getActiveQuests() {
    return activeQuests;
  }

  public List<Pair<Quest, Integer>> progressQuest(Quest quest, int progress) {
    List<Pair<Quest, Integer>> toRemove = new ArrayList<>();
    for (Pair<Quest, Integer> pair : activeQuests) {
      if (pair.getKey().equals(quest)) {
        pair.setValue(pair.getValue() + progress);
        if (pair.getValue() >= quest.getAmount()) {
          player.sendMessage("§aYou have completed the quest: " + quest);
          toRemove.add(pair);
          Reward.giveReward(player, quest.getRarity());
        }
      }
    }
    return toRemove;
  }

  public void addQuest(Quest quest) {
    activeQuests.add(new Pair<>(quest, 0));
  }

  public void addQuest(Quest quest, int progress) {
    activeQuests.add(new Pair<>(quest, progress));
  }

  public static void addPlayer(QuestPlayer player) {
    players.add(player);
    player.load();
  }

  public static void removePlayer(QuestPlayer player) {
    players.remove(player);
    player.save();
  }

  public static QuestPlayer getPlayer(Player player) {
    for (QuestPlayer questPlayer : players) {
      if (questPlayer.getPlayer().equals(player)) {
        return questPlayer;
      }
    }
    return null;
  }

  private void load() {
    Database database = Database.getInstance();
    database.loadPlayer(this);
  }

  private void save() {
    Database database = Database.getInstance();
    database.savePlayer(this);
  }
}