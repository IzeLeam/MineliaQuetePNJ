package fr.izeleam.minelia.mineliaquetepnj.menus;

import fr.izeleam.minelia.mineliaquetepnj.QuestManager;
import fr.izeleam.minelia.mineliaquetepnj.QuestPlayer;
import fr.izeleam.minelia.mineliaquetepnj.utils.gui.BetterMenu;
import fr.izeleam.minelia.mineliaquetepnj.utils.gui.ButtonAction;
import java.util.stream.Collectors;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class QuestTypeShop extends BetterMenu {

  public QuestTypeShop(String questRarity) {
    super(questRarity.substring(0, 1).toUpperCase() + questRarity.substring(1) + " quests");

    this.addLine("", "q", "q", "q", "q", "q", "q", "q", "");
    this.addLine("", "q", "q", "q", "q", "q", "q", "q", "");
    this.addLine("", "q", "q", "q", "q", "q", "q", "q", "");
    this.addLine("", "q", "q", "q", "q", "q", "q", "q", "");
    this.addLine("", "q", "q", "q", "q", "q", "q", "q", "");
    this.addLine("", "", "", "", "back", "", "", "", "");

    this.setButton("back", itemManager.createItem(Material.BARRIER)
        .setName("§cRetour")
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            QuestMenu.getMenu().open(player);
          }
        }));

    this.setButtonList("q", QuestManager.getInstance().getQuests(questRarity)
        .stream().map(quest -> {
          Material mat;
          switch (quest.getType()) {
            case BREAK:
              mat = Material.STONE_PICKAXE;
              break;
            case KILL:
              mat = Material.STONE_SWORD;
              break;
            default:
              mat = Material.WOOD;
              break;
          }
          return itemManager.createItem(mat)
              .setName(quest.toString())
              .asButton().setButtonAction(new ButtonAction() {
                @Override
                public void onClick(Player player) {
                  int cost = QuestManager.getInstance().getCost(questRarity);
                  if (player.getLevel() < cost) {
                    player.closeInventory();
                    player.sendMessage("§cVous n'avez pas assez de niveaux pour acheter cette quête");
                    return;
                  }
                  QuestPlayer questPlayer = QuestPlayer.getPlayer(player);
                  assert questPlayer != null;

                  questPlayer.addQuest(quest);
                  player.setLevel(player.getLevel() - cost);
                  player.sendMessage("§aVous avez acheté la quête " + quest);
                  player.closeInventory();
                }
              });
        }).collect(Collectors.toList()));
  }
}
