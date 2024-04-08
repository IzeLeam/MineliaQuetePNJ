package fr.izeleam.minelia.mineliaquetepnj.commands;

import fr.izeleam.minelia.mineliaquetepnj.QuestManager;
import fr.izeleam.minelia.mineliaquetepnj.QuestPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveQuestCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    if (args.length != 2) {
      commandSender.sendMessage("§cUsage: /questgive <player> <rarity>");
      return false;
    }

    Player player = Bukkit.getPlayer(args[0]);
    String questRarity = args[1];

    if (player == null) {
      commandSender.sendMessage("§cThe player is not online.");
      return true;
    }
    if (!QuestManager.getInstance().getQuests().containsKey(questRarity)) {
      commandSender.sendMessage("§cThe rarity does not exist.");
      return true;
    }

    QuestPlayer questPlayer = QuestPlayer.getPlayer(player);
    assert questPlayer != null;

    questPlayer.addQuest(QuestManager.getRandomQuest(questRarity));
    return true;
  }
}
