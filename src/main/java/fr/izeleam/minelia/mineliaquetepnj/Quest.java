package fr.izeleam.minelia.mineliaquetepnj;

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
}