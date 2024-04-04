package fr.izeleam.minelia.mineliaquetepnj;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Reward {

  private final int amount;
  private final double probability;
  private final String item;

  public Reward(int amount, double probability, String item) {
    this.amount = amount;
    this.probability = probability;
    this.item = item;
  }

  public int getAmount() {
    return amount;
  }

  public double getProbability() {
    return probability;
  }

  public String getItem() {
    return item;
  }

  public static void giveReward(Player player, String questRarity) {
    List<Reward> rewards = QuestManager.getInstance().getRewards(questRarity);
    double[] probabilities = new double[rewards.size()];
    probabilities[0] = 0;
    for (int i = 1; i < rewards.size(); i++) {
      probabilities[i] = probabilities[i - 1] + rewards.get(i).getProbability();
    }
    double random = Math.random();
    for (int i = 0; i < probabilities.length; i++) {
      if ( i - 1 > 0 && random < probabilities[i] && random > probabilities[i - 1]) {
        Reward reward = rewards.get(i);
        if (reward.getItem() == null) {
          player.sendMessage("§aYou received " + reward.getAmount() + " $ !");
          player.giveExp(reward.getAmount());
          return;
        } else if (reward.getItem().equals("minelia_piece")) {
          player.sendMessage("§aYou received " + reward.getAmount() + " Minelia piece!");
          ItemStack mineliaPiece = new ItemStack(Material.GOLD_NUGGET, reward.getAmount());
          player.getInventory().addItem(mineliaPiece);
          return;
        }
      }
    }
  }
}