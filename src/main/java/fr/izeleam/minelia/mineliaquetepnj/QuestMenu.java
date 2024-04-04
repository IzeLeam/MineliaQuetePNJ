package fr.izeleam.minelia.mineliaquetepnj;

import fr.izeleam.minelia.mineliaquetepnj.utils.gui.BetterMenu;
import fr.izeleam.minelia.mineliaquetepnj.utils.gui.ButtonAction;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class QuestMenu extends BetterMenu {

  private static final QuestMenu questMenu = new QuestMenu();

  public static QuestMenu getMenu() {
    return questMenu;
  }

  private QuestMenu() {
    super("Quests");

    this.addLine("", "", "", "", "", "", "", "", "");
    this.addLine("", "", "", "", "", "", "", "", "");
    this.addLine("", "", "", "", "test", "", "", "", "");
    this.addLine("", "", "", "", "", "", "", "", "");
    this.addLine("", "", "", "", "", "", "", "", "");
    this.addLine("", "", "", "", "", "", "", "", "");

    this.setButton("test", itemManager.createItem(Material.ACACIA_DOOR)
        .setName("§6§lClick me")
        .asButton()
        .setButtonAction(
            new ButtonAction() {
              @Override
              public void onClick(Player player) {
                player.sendMessage("§aYou clicked the button!");
              }
            }
        ));

  }
}