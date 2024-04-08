package fr.izeleam.minelia.mineliaquetepnj;

import java.util.List;
import net.minecraft.server.v1_8_R3.SpawnerCreature;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
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
    final double max = rewards.stream().mapToDouble(Reward::getProbability).sum();
    double sum = 0;
    final double rand = Math.random() * max;
    for (Reward reward : rewards) {
      sum += reward.getProbability();
      if (sum >= rand) {
        if (reward.getItem() == null) {
          MineliaQuetePNJ.getInstance().getServer().dispatchCommand(MineliaQuetePNJ.getInstance().getServer().getConsoleSender(), "eco give " + player.getName() + " " + reward.getAmount());
          player.sendMessage("§aYou have received " + reward.getAmount() + " coins!");
          return;
        }
        if (reward.getItem().equalsIgnoreCase("minelia_piece")) {
          MineliaQuetePNJ.getInstance().getServer().dispatchCommand(MineliaQuetePNJ.getInstance().getServer().getConsoleSender(), "mitems give " + player.getName() + " minelia-money " + reward.getAmount());
          player.sendMessage("§aYou have received " + reward.getAmount() + " Minelia pieces!");
          return;
        }
        if (reward.getItem().matches(".*_key")) {
          player.sendMessage("§aYou have received a + " + reward.getItem().substring(0, reward.getItem().length() - 4) + " key!");
          return;
        }
        if (reward.getItem().matches(".*_spawner")) {
          player.sendMessage("§aYou have received a " + reward.getItem().substring(0, reward.getItem().length() - 8) + " spawner!");
          return;
        }
      }
    }
  }
}