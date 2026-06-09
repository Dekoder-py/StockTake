package dev.codingcorner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Panel extends JPanel {

  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  public Panel() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(Color.WHITE);
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);

    JLabel title = new JLabel("StockTake");
    gbc.gridy = 0;
    this.add(title, gbc);

    JTextField addItemField = new JTextField(6);
    gbc.gridy = 1;

    addItemField.addActionListener(e -> {
      onAddItem();
      addItemField.setText(""); // clear entry field after item added
	});

    this.add(addItemField, gbc);

  }

  private void onAddItem() {
    System.out.println("item added ig");
  }

}
