package fr.izeleam.minelia.mineliaquetepnj;

import net.minecraft.server.v1_8_R3.BlockPressurePlateBinary.EnumMobType;
import org.bukkit.Material;

public class Quest {

  private final QuestType type;
  private final int amount;
  private final String blockType;
  private final String mobType;

  public Quest(QuestType type, int amount, String blockType, String mobType) {
    this.type = type;
    this.amount = amount;
    this.blockType = blockType;
    this.mobType = mobType;
  }

  public QuestType getType() {
    return type;
  }

  public int getAmount() {
    return amount;
  }

  public String getBlockType() {
    return blockType;
  }

  public String getMobType() {
    return mobType;
  }

  public String getRarity() {
    return QuestManager.getInstance().getQuestRarity(this);
  }

  @Override
  public String toString() {
    switch (type) {
      case BREAK:
        return "Break " + amount + " " + blockType;
      case KILL:
        return "Kill " + amount + " " + mobType;
      case PLACE:
        return "Place " + amount + " " + blockType;
      default:
        return "Unknown quest";
    }
  }
}