package dev.minelia.mineliaquetepnj.quests;

public enum Rarity {
  MYSTERY("mystery", "Mystère"),
  MINELIA("minelia", "Minelia"),
  LEGENDARY("legendary", "Légendaire");

  private final String name;
  private final String displayName;

  Rarity(String name, String displayName) {
    this.name = name;
    this.displayName = displayName;
  }

  public String getName() {
    return name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static Rarity getByName(String name) {
    for (Rarity rarity : values()) {
      if (rarity.getName().equalsIgnoreCase(name)) {
        return rarity;
      }
    }
    return null;
  }
}
