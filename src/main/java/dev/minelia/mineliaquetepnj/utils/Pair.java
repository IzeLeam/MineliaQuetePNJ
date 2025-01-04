package dev.minelia.mineliaquetepnj.utils;

public class Pair<T, V> {

  private final T key;
  private V value;

  public Pair(T key, V value) {
    this.key = key;
    this.value = value;
  }

  public T getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }
}