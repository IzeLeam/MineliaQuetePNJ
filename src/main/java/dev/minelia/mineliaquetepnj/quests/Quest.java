package dev.minelia.mineliaquetepnj.quests;

import dev.minelia.mineliaquetepnj.MineliaQuetePNJ;
import dev.minelia.mineliaquetepnj.utils.managers.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.meta.ItemMeta;

public class Quest {

  private final short id;
  private final QuestType type;
  private final int objective;
  private final Object object;
  private final QuestRarity rarity;
  private final String description;

  public Quest(short id, QuestType type, int objective, Object object, QuestRarity rarity, String description) {
    this.id = id;
    this.type = type;
    this.objective = objective;
    this.object = object;
    this.rarity = rarity;
    this.description = description;
  }

  public short getId() {
    return id;
  }

  public QuestType getType() {
    return type;
  }

  public int getObjective() {
    return objective;
  }

  public Object getObject() {
    return object;
  }

  public QuestRarity getQuestRarity() {
    return rarity;
  }

  public String getDescription() {
    return description;
  }

  public boolean isCompleted(ItemStack item) {
    return getProgress(item) >= objective;
  }

  public void give(Player player) {
    player.getInventory().addItem(createBookItem());
  }

  public ItemStack createBookItem() {
    ItemManager itemManager = new ItemManager();

    ItemStack item = itemManager.createItem(Material.BOOK)
        .setName(ChatColor.translateAlternateColorCodes('&',
          MineliaQuetePNJ.getInstance().getConfig().getString("quest_books.name")
            .replace("%color%", getQuestRarity().getColor())
            .replace("%rarity%", getQuestRarity().getRarity().getDisplayName())))
        .setLore(getLore(0, false));

    final net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
    nmsItem.getTag().setShort("questId", id);
    nmsItem.getTag().setString("questRarity", getQuestRarity().getRarity().getName());
    nmsItem.getTag().setInt("progress", 0);
    return CraftItemStack.asBukkitCopy(nmsItem);
  }

  public List<String> getLore(int progress, boolean completed) {
    final List<String> lore = new ArrayList<>();
    for (String line : MineliaQuetePNJ.getInstance().getConfig().getStringList("quest_books.lore")) {
      lore.add(ChatColor.translateAlternateColorCodes('&', line
          .replace("%description%", getDescription())
          .replace("%progress%", String.valueOf(progress))
          .replace("%objective%", String.valueOf(objective))
          .replace("%color%", getQuestRarity().getColor())));
    }
    if (completed) {
      for (String line : MineliaQuetePNJ.getInstance().getConfig().getStringList("quest_books.completed")) {
        lore.add(ChatColor.translateAlternateColorCodes('&', line
          .replace("%color%", getQuestRarity().getColor())));
      }
    }
    return lore;
  }

  public int getProgress(ItemStack item) {
    if (item == null || !QuestManager.getInstance().isQuestBook(item)) {
      return 0;
    }

    final net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
    return nmsItem.getTag().getInt("progress");
  }

  public void setProgress(ItemStack item, int progress) {
    if (item == null || !QuestManager.getInstance().isQuestBook(item)) {
      return;
    }
    if (progress >= objective) {
      progress = objective;
    }

    final net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
    nmsItem.getTag().setInt("progress", progress);
    item.setItemMeta(CraftItemStack.asBukkitCopy(nmsItem).getItemMeta());
    final ItemMeta meta = item.getItemMeta();
    assert meta != null;
    meta.setLore(getLore(getProgress(item), isCompleted(item)));
    item.setItemMeta(meta);
  }
}