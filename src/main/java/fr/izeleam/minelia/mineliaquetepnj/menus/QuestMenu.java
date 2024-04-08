package fr.izeleam.minelia.mineliaquetepnj.menus;

import fr.izeleam.minelia.mineliaquetepnj.QuestManager;
import fr.izeleam.minelia.mineliaquetepnj.QuestPlayer;
import fr.izeleam.minelia.mineliaquetepnj.utils.gui.BetterMenu;
import fr.izeleam.minelia.mineliaquetepnj.utils.gui.ButtonAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class QuestMenu extends BetterMenu {

  private static final QuestMenu menu = new QuestMenu();

  public static QuestMenu getMenu() {
    return menu;
  }

  public QuestMenu() {
    super("Quest Shop");

    this.addLine("d", "", "", "", "", "", "", "", "d");
    this.addLine("d", "", "mystery", "", "minelia", "", "legendary", "", "d");
    this.addLine("d", "", "", "", "", "", "", "", "d");
    this.addLine("d", "", "", "", "close", "", "", "", "d");

    this.setButton("close", itemManager.createItem(Material.BARRIER)
        .setName("§cClose")
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            player.closeInventory();
          }
        }));

    this.setItemStack("d", itemManager.createItem(Material.STAINED_GLASS_PANE)
        .setName("§c")
        .setColor(DyeColor.LIGHT_BLUE));

    this.setButton("mystery", itemManager.createItem(Material.BOOK)
        .setName("§5Mystery Quest")
        .setLore(getLore("mystery"))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            QuestPlayer.getPlayer(player).addQuest(QuestManager.getRandomQuest("mystery"));
          }
        }));

    this.setButton("minelia", itemManager.createItem(Material.BOOK)
        .setName("§6Minelia Quest")
        .setLore(getLore("minelia"))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            QuestPlayer.getPlayer(player).addQuest(QuestManager.getRandomQuest("minelia"));
          }
        }));

    this.setButton("legendary", itemManager.createItem(Material.BOOK)
        .setName("§cLegendary Quest")
        .setLore(getLore("legendary"))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            QuestPlayer.getPlayer(player).addQuest(QuestManager.getRandomQuest("legendary"));
          }
        }));
  }

  private List<String> getLore(String type) {
    List<String> lore = new ArrayList<>();
    lore.add("");
    lore.add("§dCoût: §e" + QuestManager.getInstance().getCost(type) + " niveaux");
    lore.add("");
    lore.add("§7Récompenses:");
    lore.addAll(getRewards(type));
    lore.add("");
    lore.add("§6§l=> §r§8Cliquez pour voir les quêtes");
    return lore;
  }

  private List<String> getRewards(String type) {
    return QuestManager.getInstance().getRewards(type).stream().map(reward -> {
      return "§7- §e"
          + reward.getAmount()
          + " §3"
          + (reward.getItem() == null ? "$" : reward.getItem());
    }).collect(Collectors.toList());
  }
}