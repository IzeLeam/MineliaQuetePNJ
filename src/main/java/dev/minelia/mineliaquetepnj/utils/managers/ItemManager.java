package dev.minelia.mineliaquetepnj.utils.managers;

import dev.minelia.mineliaquetepnj.utils.gui.BetterItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

  public BetterItem createItem(ItemStack itemStack) {
    return new BetterItem(itemStack);
  }

  public BetterItem createItem(Material material) {
    return new BetterItem(material);
  }

  public BetterItem createItem(Material material, int amount) {
    return new BetterItem(material, amount);
  }

}