package dev.minelia.mineliaquetepnj.commands;

import dev.minelia.mineliaquetepnj.quests.QuestManager;
import dev.minelia.mineliaquetepnj.quests.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveQuestCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    if (args.length != 2) {
      return false;
    }

    Player player = Bukkit.getPlayer(args[0]);
    Rarity questRarity = Rarity.getByName(args[1]);

    if (player == null) {
      commandSender.sendMessage("§cThe player is not online.");
      return true;
    }
    if (QuestManager.getInstance().getQuestRarity(questRarity) == null) {
      commandSender.sendMessage("§cThe rarity does not exist.");
      return true;
    }

    QuestManager.getInstance().getQuestRarity(questRarity).getRandomQuest().give(player);
    return true;
  }
}
