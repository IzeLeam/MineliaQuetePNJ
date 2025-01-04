package dev.minelia.mineliaquetepnj.quests;

import dev.minelia.mineliaquetepnj.MineliaQuetePNJ;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Reward {

  private final int amount;
  private final double probability;
  private final String item;
  private final String translation;

  public Reward(int amount, double probability, String item, String translation) {
    this.amount = amount;
    this.probability = probability;
    this.item = item;
    this.translation = translation;
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

  public String getTranslation() {
    if (translation == null) {
      return "$";
    }
    return translation;
  }

  public void give(Player player) {
    if (item == null) {
      player.sendMessage("§aVous avez reçu " + amount + " $");
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + amount);
    } else if (item.contains("key")) {
      String rarity = item.substring(0, item.indexOf("_"));
      player.sendMessage("§aVous avez reçu une clé " + rarity + " !");
      String keyCommand = MineliaQuetePNJ.getInstance().getConfig().getString("key_give_command");
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), keyCommand.replace("%player%", player.getName()).replace("%rarity%", rarity).replace("%amount%", String.valueOf(amount)));
    } else if (item.contains("spawner")) {
      String type = item.substring(0, item.indexOf("_"));
      player.sendMessage("§aVous avez reçu un spawner de " + type + " !");
      String spawnerCommand = MineliaQuetePNJ.getInstance().getConfig().getString("spawner_give_command");
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), spawnerCommand.replace("%player%", player.getName()).replace("%type%", type));
    } else if (item.contains("piece")) {
      player.sendMessage("§aVous avez reçu " + amount + " pièces Minelia !");
      String pieceCommand = MineliaQuetePNJ.getInstance().getConfig().getString("minelia_piece_give_command");
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
          pieceCommand.replace("%player%", player.getName()).replace("%amount%", String.valueOf(amount)));
    }
  }
}