package dev.codingcorner;

public class InventoryItem {

  public String name;
  public int quantity;

  public InventoryItem() {}

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
