package dev.codingcorner;

public class InventoryItem {

  private String name;
  private int quantity;

  public InventoryItem(String name, int quantity) {

    this.name = name;
    this.quantity = quantity;

    System.out.println(name + ":" + quantity);

  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void updateQuantity(int amount) {
    this.quantity += amount;
  }

}
