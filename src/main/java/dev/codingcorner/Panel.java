package dev.codingcorner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class Panel extends JPanel {

  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  private JTextField addItemField;
  private JSpinner itemAmountSpinner;

  public Panel() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(Color.WHITE);
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints(); // used for grid positioning
    gbc.insets = new Insets(5, 5, 5, 5);

    JLabel title = new JLabel("StockTake");
    gbc.gridy = 0;
    this.add(title, gbc);

    addItemField = new JTextField(6);

    addItemField.addActionListener(e -> {
      onAddItem();
    });

    gbc.gridy = 1;
    this.add(addItemField, gbc);

    itemAmountSpinner = new JSpinner();
    itemAmountSpinner.setValue(1);

    gbc.gridy = 1;
    gbc.gridx = 1;
    this.add(itemAmountSpinner, gbc);

  }

  private void onAddItem() {

    String name = addItemField.getText();
    int quantity = (int) itemAmountSpinner.getValue();

    new InventoryItem(name, quantity);

    // clear entry fields after item added
    addItemField.setText("");
    itemAmountSpinner.setValue(1);
  }

}
