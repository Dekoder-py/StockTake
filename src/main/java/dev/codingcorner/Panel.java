package dev.codingcorner;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.*;

import com.catppuccin.Palette;

public class Panel extends JPanel {

  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  private HashMap<String, InventoryItem> items = new HashMap<>();

  private JTextField addItemField;
  private JSpinner itemAmountSpinner;

  private Color BASE = new Color(
      Palette.MOCHA.base().r(),
      Palette.MOCHA.base().g(),
      Palette.MOCHA.base().b());

  private Color BLUE = new Color(
      Palette.MOCHA.blue().r(),
      Palette.MOCHA.blue().g(),
      Palette.MOCHA.blue().b());

  private Color TEXT = new Color(
      Palette.MOCHA.text().r(),
      Palette.MOCHA.text().g(),
      Palette.MOCHA.text().b());

  public Panel() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    this.setBackground(BASE);
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints(); // used for grid positioning
    gbc.insets = new Insets(5, 5, 5, 5);

    JLabel title = new JLabel("StockTake");
    title.setForeground(TEXT);

    gbc.gridy = 0;
    this.add(title, gbc);

    addItemField = new JTextField(6);

    gbc.gridy = 1;
    this.add(addItemField, gbc);

    itemAmountSpinner = new JSpinner();
    itemAmountSpinner.setValue(1);

    gbc.gridy = 1;
    gbc.gridx = 1;
    this.add(itemAmountSpinner, gbc);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    JButton checkInBtn = new JButton("Check In");
    JButton checkOutBtn = new JButton("Check Out");

    checkInBtn.setBackground(BLUE);
    checkOutBtn.setBackground(BLUE);
    checkInBtn.setOpaque(true);
    checkOutBtn.setOpaque(true);
    checkInBtn.setBorderPainted(false);
    checkOutBtn.setBorderPainted(false);
    checkInBtn.setFocusPainted(false);
    checkOutBtn.setFocusPainted(false);

    checkInBtn.addActionListener(e -> {
      addItem();

      System.out.println("\n\nNew inventory: ");
      for (InventoryItem item : items.values()) {
        System.out.println("Item: " + item.getName() + ", Quantity: " + item.quantity);
      }
    });

    checkOutBtn.addActionListener(e -> {
      removeItem();

      System.out.println("\n\nNew inventory: ");
      for (InventoryItem item : items.values()) {
        System.out.println("Item: " + item.getName() + ", Quantity: " + item.quantity);
      }
    });

    buttonPanel.add(checkInBtn, Component.CENTER_ALIGNMENT);
    buttonPanel.add(checkOutBtn, Component.CENTER_ALIGNMENT);

    gbc.gridx = 2;
    this.add(buttonPanel, gbc);

  }

  private void addItem() {

    String name = addItemField.getText().toLowerCase().trim();
    int quantity = (int) itemAmountSpinner.getValue();

    if (name.isBlank() || quantity == 0) {
      return;
    }

    items.compute(name,
        (k, v) -> v == null ? new InventoryItem(name, quantity) : new InventoryItem(name, v.quantity += quantity));

    // clear entry fields after item added
    addItemField.setText("");
    itemAmountSpinner.setValue(1);
  }

  private void removeItem() {

    String name = addItemField.getText().toLowerCase().trim();
    int quantity = (int) itemAmountSpinner.getValue();

    if (name.isBlank() || quantity == 0) {
      return;
    }

    if (!items.containsKey(name)) {
      String text = "Item '" + name + "' not found!";
      JOptionPane optionPane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE);
      JDialog alert = optionPane.createDialog("Check Out Error!");
      alert.setAlwaysOnTop(true);
      alert.setVisible(true);

      return;
    }

    if (items.get(name).quantity - quantity < 0) {
      String text = "You can't check out that many! There are currently " + items.get(name).quantity
          + " of that item checked in!";
      JOptionPane optionPane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE);
      JDialog alert = optionPane.createDialog("Check Out Error!");
      alert.setAlwaysOnTop(true);
      alert.setVisible(true);

      return;
    }

    items.compute(name,
        (k, v) -> v == null ? new InventoryItem(name, quantity) : new InventoryItem(name, v.quantity -= quantity));

    // clear entry fields after item added
    addItemField.setText("");
    itemAmountSpinner.setValue(1);
  }

}
