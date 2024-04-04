package fr.izeleam.minelia.mineliaquetepnj.menus;

import fr.izeleam.minelia.mineliaquetepnj.QuestManager;
import fr.izeleam.minelia.mineliaquetepnj.utils.gui.BetterMenu;
import fr.izeleam.minelia.mineliaquetepnj.utils.gui.ButtonAction;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class QuestMenu extends BetterMenu {

  private static final QuestMenu menu = new QuestMenu();

  public static QuestMenu getMenu() {
    return menu;
  }

  public QuestMenu() {
    super("Quest Shop");

    this.addLine("", "", "", "", "", "", "", "", "");
    this.addLine("", "", "mystery", "", "minelia", "", "legendary", "", "");
    this.addLine("", "", "", "", "", "", "", "", "");

    this.setButton("mystery", itemManager.createItem(Material.BOOK)
        .setName("§5Mystery Quest")
        .setLore(getLore("mystery"))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            QuestTypeShop menu = new QuestTypeShop("mystery");
            menu.open(player);
          }
        }));

    this.setButton("minelia", itemManager.createItem(Material.BOOK)
        .setName("§6Minelia Quest")
        .setLore(getLore("minelia"))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            QuestTypeShop menu = new QuestTypeShop("minelia");
            menu.open(player);
          }
        }));

    this.setButton("legendary", itemManager.createItem(Material.BOOK)
        .setName("§cLegendary Quest")
        .setLore(getLore("legendary"))
        .asButton().setButtonAction(new ButtonAction() {
          @Override
          public void onClick(Player player) {
            QuestTypeShop menu = new QuestTypeShop("legendary");
            menu.open(player);
          }
        }));
  }

  private List<String> getLore(String type) {
    switch (type) {
      case "mystery":
        return Arrays.asList("", "§dCost: §b" + QuestManager.getInstance().getCost("mystery") + " levels");
      case "minelia":
        return Arrays.asList("", "§6Cost: §e" + QuestManager.getInstance().getCost("minelia") + " levels");
      case "legendary":
        return Arrays.asList("", "§cCost: §4" + QuestManager.getInstance().getCost("legendary") + " levels");
      default:
        return Arrays.asList("§7Unknown quest type");
    }
  }
}