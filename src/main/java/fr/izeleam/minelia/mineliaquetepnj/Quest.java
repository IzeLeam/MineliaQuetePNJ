package fr.izeleam.minelia.mineliaquetepnj;

public class Quest {

  private final int id;
  private final QuestType type;
  private final int amount;
  private final Object object;

  public Quest(int id, QuestType type, int amount, Object object) {
    this.id = id;
    this.type = type;
    this.amount = amount;
    this.object = object;
  }

  public int getId() {
    return id;
  }

  public QuestType getType() {
    return type;
  }

  public int getAmount() {
    return amount;
  }

  public Object getObject() {
    return object;
  }

  public String getRarity() {
    return QuestManager.getInstance().getQuestRarity(this);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    switch (type) {
      case BREAK:
        builder.append("Cassez ");
        break;
      case KILL:
        builder.append("Tuez ");
        break;
      case PLACE:
        builder.append("Placez ");
        break;
      default:
        return "Unknown quest";
    }

    if (object == null) {
      builder.append(amount).append(" ").append("blocs");
      return builder.toString();
    }
    builder.append(amount).append(" ").append(object.toString().toLowerCase());
    return builder.toString();
  }
}