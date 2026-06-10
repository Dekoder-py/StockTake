package dev.codingcorner;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import com.catppuccin.Palette;

public class Panel extends JPanel {

  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

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

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    JButton checkInBtn = new JButton("Check In");
    JButton checkOutBtn = new JButton("Check Out");

    checkInBtn.setBackground(BLUE);
    checkOutBtn.setBackground(BLUE);
    checkInBtn.setOpaque(true);
    checkOutBtn.setOpaque(true);

    buttonPanel.add(checkInBtn, Component.CENTER_ALIGNMENT);
    buttonPanel.add(checkOutBtn, Component.CENTER_ALIGNMENT);

    gbc.gridx = 2;
    this.add(buttonPanel, gbc);

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
