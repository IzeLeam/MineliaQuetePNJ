package fr.izeleam.minelia.mineliaquetepnj;

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

  public void addQuest(Quest quest) {
    activeQuests.add(new Pair<>(quest, 0));
  }

  public static void addPlayer(QuestPlayer player) {
    players.add(player);
  }

  public static void removePlayer(QuestPlayer player) {
    players.remove(player);
  }

  public static QuestPlayer getPlayer(Player player) {
    for (QuestPlayer questPlayer : players) {
      if (questPlayer.getPlayer().equals(player)) {
        return questPlayer;
      }
    }
    return null;
  }
}
