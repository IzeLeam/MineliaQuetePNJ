package dev.minelia.mineliaquetepnj.quests;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class QuestRarity {

  private final Rarity rarity;
  private final String color;
  private final int cost;
  private final Set<Quest> quests = new HashSet<>();
  private final Set<Reward> rewards = new HashSet<>();

  public QuestRarity(Rarity rarity, String color, int cost) {
    this.rarity = rarity;
    this.color = color;
    this.cost = cost;
  }

  public Rarity getRarity() {
    return rarity;
  }

  public String getColor() {
    return color;
  }

  public int getCost() {
    return cost;
  }

  public Set<Quest> getQuests() {
    return quests;
  }

  public void addQuest(Quest quest) {
    quests.add(quest);
  }

  public Set<Reward> getRewards() {
    return rewards;
  }

  public void addReward(Reward reward) {
    rewards.add(reward);
  }

  public Quest getQuest(short id) {
    for (Quest quest : quests) {
      if (quest.getId() == id) {
        return quest;
      }
    }
    return null;
  }

  public Quest getRandomQuest() {
    int size = quests.size();
    int item = new Random().nextInt(size);
    int i = 0;
    for (Quest quest : quests) {
      if (i == item) {
        return quest;
      }
      i++;
    }
    return null;
  }

  public Reward getRandomReward() {
    double[] probaCumul = new double[rewards.size()];
    double sum = 0;
    int i = 0;
    for (Reward reward : rewards) {
      sum += reward.getProbability();
      probaCumul[i] = sum;
      i++;
    }
    double random = new Random().nextDouble() * sum;
    i = 0;
    for (Reward reward : rewards) {
      if (random < probaCumul[i]) {
        return reward;
      }
      i++;
    }
    return null;
  }
}
