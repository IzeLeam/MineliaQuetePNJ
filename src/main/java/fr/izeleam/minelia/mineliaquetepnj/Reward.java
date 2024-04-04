package fr.izeleam.minelia.mineliaquetepnj;

public class Reward {

  private final int amount;
  private final int probability;
  private final String item;

  public Reward(int amount, int probability, String item) {
    this.amount = amount;
    this.probability = probability;
    this.item = item;
  }

  public int getAmount() {
    return amount;
  }

  public int getProbability() {
    return probability;
  }

  public String getItem() {
    return item;
  }
}