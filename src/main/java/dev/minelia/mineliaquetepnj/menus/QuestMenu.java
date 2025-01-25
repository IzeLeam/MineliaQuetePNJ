package dev.minelia.mineliaquetepnj.menus;

import dev.minelia.mineliaquetepnj.MineliaQuetePNJ;
import dev.minelia.mineliaquetepnj.quests.Quest;
import dev.minelia.mineliaquetepnj.quests.QuestManager;
import dev.minelia.mineliaquetepnj.quests.Rarity;
import dev.minelia.mineliaquetepnj.utils.gui.BetterMenu;
import dev.minelia.mineliaquetepnj.utils.gui.ButtonAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class QuestMenu extends BetterMenu {

  private static final QuestMenu menu = new QuestMenu();

  public static QuestMenu getMenu() {
    return menu;
  }

  public QuestMenu() {
    super("Troqueur");

    final FileConfiguration config = MineliaQuetePNJ.getInstance().getConfig();

    this.addLine("d", "d", "", "", "info", "", "", "d", "d");
    this.addLine("d", "", "", "", "", "", "", "", "d");
    this.addLine("", "", "mystery", "", Rarity.MINELIA.getName(), "", Rarity.LEGENDARY.getName(), "", "");
    this.addLine("d", "", "", "", "", "", "", "", "d");
    this.addLine("d", "d", "", "", "close", "", "", "d", "d");

    this.setButton("close", itemManager.createItem(Material.BARRIER)
        .setName("§cFermer")
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            player.closeInventory();
          }
        }));

    this.setItemStack("d", itemManager.createItem(Material.STAINED_GLASS_PANE)
        .setName("§c")
        .setColor(DyeColor.MAGENTA));

    this.setItemStack("info", itemManager.createItem(Material.SIGN)
        .setName("§6Boutique des quêtes")
        .setLore(Arrays.asList("",
            "§7 Accomplissez des quêtes pour gagner des récompenses.",
            "",
            "§7Cliquez sur un livre pour obtenir une quête."
        ))
    );

    this.setButton(Rarity.MYSTERY.getName(), itemManager.createItem(Material.BOOK)
        .setName(ChatColor.translateAlternateColorCodes('&', translateCodes(config.getString("menu.books.name"), Rarity.MYSTERY)))
        .setLore(getLore(Rarity.MYSTERY))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            final int cost = QuestManager.getInstance().getQuestRarity(Rarity.MYSTERY).getCost();
            if (player.getExpToLevel() < cost) {
              player.sendMessage("§cVous n'avez pas assez d'expérience pour acheter cette quête.");
              return;
            }
            player.giveExpLevels(-cost);
            final Quest q = QuestManager.getInstance().getQuestRarity(Rarity.MYSTERY).getRandomQuest();
            q.give(player);
          }
        }));

    this.setButton(Rarity.MINELIA.getName(), itemManager.createItem(Material.BOOK)
        .setName(ChatColor.translateAlternateColorCodes('&', translateCodes(config.getString("menu.books.name"), Rarity.MINELIA)))
        .setLore(getLore(Rarity.MINELIA))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            final int cost = QuestManager.getInstance().getQuestRarity(Rarity.MINELIA).getCost();
            if (player.getExpToLevel() < cost) {
              player.sendMessage("§cVous n'avez pas assez d'expérience pour acheter cette quête.");
              return;
            }
            player.giveExpLevels(-cost);
            final Quest q = QuestManager.getInstance().getQuestRarity(Rarity.MINELIA).getRandomQuest();
            q.give(player);
          }
        }));

    this.setButton(Rarity.LEGENDARY.getName(), itemManager.createItem(Material.BOOK)
        .setName(ChatColor.translateAlternateColorCodes('&', translateCodes(config.getString("menu.books.name"), Rarity.LEGENDARY)))
        .setLore(getLore(Rarity.LEGENDARY))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            final int cost = QuestManager.getInstance().getQuestRarity(Rarity.LEGENDARY).getCost();
            if (player.getExpToLevel() < cost) {
              player.sendMessage("§cVous n'avez pas assez d'expérience pour acheter cette quête.");
              return;
            }
            player.giveExpLevels(-cost);
            final Quest q = QuestManager.getInstance().getQuestRarity(Rarity.LEGENDARY).getRandomQuest();
            q.give(player);
          }
        }));
  }

  private List<String> getLore(Rarity rarity) {
    final List<String> lore = new ArrayList<>();
    final FileConfiguration config = MineliaQuetePNJ.getInstance().getConfig();
    final List<String> lines = config.getStringList("menu.books.lore");
    for (String line : lines) {
      lore.add(ChatColor.translateAlternateColorCodes('&', translateCodes(line, rarity)));
    }
    int i;
    for (i = 0; i < lines.size(); i++) {
      if (lines.get(i).contains("%rewards%")) {
        break;
      }
    }
    if (i == lines.size()) {
      return lore;
    }
    lore.remove(i);
    lore.addAll(i, getRewards(rarity));
    return lore;
  }

  private String translateCodes(String line, Rarity rarity) {
    return ChatColor.translateAlternateColorCodes('&', line.replace("%rarity%", rarity.getDisplayName()))
        .replace("%color%", QuestManager.getInstance().getQuestRarity(rarity).getColor())
        .replace("%cost%", String.valueOf(QuestManager.getInstance().getQuestRarity(rarity).getCost()));
  }

  private List<String> getRewards(Rarity rarity) {
    return QuestManager.getInstance().getQuestRarity(rarity).getRewards().stream().map(reward -> {
      final FileConfiguration config = MineliaQuetePNJ.getInstance().getConfig();
      final String line = config.getString("menu.books.rewards");
      return ChatColor.translateAlternateColorCodes('&', line
          .replace("%amount%", long_number_formater(String.valueOf(reward.getAmount())))
          .replace("%probability%", String.valueOf(reward.getProbability()))
          .replace("%translation%", reward.getTranslation())
          .replace("%color%", QuestManager.getInstance().getQuestRarity(rarity).getColor()));
    }).collect(Collectors.toList());
  }

  private String long_number_formater(String number) {
    String result = "";
    int count = 0;
    for (int i = number.length() - 1; i >= 0; i--) {
      result = number.charAt(i) + result;
      count++;
      if (count % 3 == 0 && i != 0) {
        result = " " + result;
      }
    }
    return result;
  }
}