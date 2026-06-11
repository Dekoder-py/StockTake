package dev.codingcorner;

public class InventoryItem {

  private String name;
  public int quantity;

  public InventoryItem(String name, int quantity) {

    this.name = name;
    this.quantity = quantity;

  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

}
