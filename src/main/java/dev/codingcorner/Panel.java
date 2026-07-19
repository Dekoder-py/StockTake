package dev.codingcorner;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.catppuccin.Palette;

public class Panel extends JPanel {

  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  private Vector<InventoryItem> items = new Vector<InventoryItem>();

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

  String[] columns = { "Name", "Quantity" };
  private DefaultTableModel model = new DefaultTableModel(columns, 0);

  public Panel() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    this.setBackground(BASE);
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints(); // used for grid positioning
    gbc.insets = new Insets(5, 5, 5, 5);

    JLabel title = new JLabel("StockTake");
    title.setFont(new Font("Sans-Serif", Font.TRUETYPE_FONT, 24));
    title.setForeground(TEXT);

    gbc.gridy = 0;
    this.add(title, gbc);

    JLabel fieldLabel = new JLabel("Item:");

    gbc.gridy = 1;
    gbc.gridx = 0;
    this.add(fieldLabel, gbc);

    addItemField = new JTextField(5);
    addItemField.setColumns(15);

    gbc.gridx = 1;
    this.add(addItemField, gbc);

    JLabel spinnerLabel = new JLabel("Quantity:");

    gbc.gridy = 2;
    gbc.gridx = 0;
    this.add(spinnerLabel, gbc);

    itemAmountSpinner = new JSpinner();
    itemAmountSpinner.setValue(1);

    // set size of spinner (source:
    // https://stackoverflow.com/questions/25188926/change-jspinner-size-width)
    Component spinnerEditor = itemAmountSpinner.getEditor();
    JFormattedTextField jftf = ((JSpinner.DefaultEditor) spinnerEditor).getTextField();
    jftf.setColumns(5);

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

      for (InventoryItem item : items) {
        System.out.println("Item: " + item.getName() + ", Quantity: " + item.quantity);
      }
    });

    checkOutBtn.addActionListener(e -> {
      removeItem();

      System.out.println("\n\nNew inventory: ");
      for (InventoryItem item : items) {
        System.out.println("Item: " + item.getName() + ", Quantity: " + item.quantity);
      }
    });

    buttonPanel.add(checkInBtn, Component.CENTER_ALIGNMENT);
    buttonPanel.add(checkOutBtn, Component.CENTER_ALIGNMENT);

    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.gridheight = 2;
    this.add(buttonPanel, gbc);
    gbc.gridheight = 1;

    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 3;
    this.add(scrollPane, gbc);
    gbc.gridwidth = 1;

  }

  private void addItem() {

    String name = addItemField.getText().toLowerCase().trim();
    int quantity = (int) itemAmountSpinner.getValue();

    if (name.isBlank() || quantity <= 0) {
      if (quantity <= 0) {
        String text = "You can't check in less than one!";
        JOptionPane optionPane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE);
        JDialog alert = optionPane.createDialog("Check In Error!");
        alert.setAlwaysOnTop(true);
        alert.setVisible(true);
      }
      return;
    }

    // search through items for item
    boolean found = false;
    for (int row = 0; row < items.size(); row++) {
      InventoryItem item = items.get(row);

      if (item.getName().equals(name)) {
        item.quantity += quantity;
        model.setValueAt(item.getQuantity(), row, 1);

        found = true;
        break;
      }
    }

    if (!found) {
      items.add(new InventoryItem(name, quantity));
      model.addRow(new Object[] {
          name,
          quantity
      });
    }

    // clear entry fields after item added
    addItemField.setText("");
    itemAmountSpinner.setValue(1);
  }

  private void removeItem() {

    String name = addItemField.getText().toLowerCase().trim();
    int quantity = (int) itemAmountSpinner.getValue();

    if (name.isBlank() || quantity <= 0) {
      if (quantity <= 0) {
        String text = "You can't check out less than one!";
        JOptionPane optionPane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE);
        JDialog alert = optionPane.createDialog("Check Out Error!");
        alert.setAlwaysOnTop(true);
        alert.setVisible(true);
      }

      return;
    }

    // search for item to remove
    boolean found = false;
    for (int row = 0; row < items.size(); row++) {
      InventoryItem item = items.get(row);
      if (item.getName().equals(name)) {
        found = true;
        if (item.quantity - quantity < 0) {
          String text = "You can't check out that many! There are currently " + item.quantity
              + " of that item checked in!";
          JOptionPane optionPane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE);
          JDialog alert = optionPane.createDialog("Check Out Error!");
          alert.setAlwaysOnTop(true);
          alert.setVisible(true);

          return;
        }

        item.quantity -= quantity;
        model.setValueAt(item.getQuantity(), row, 1);
        break;
      }
    }

    if (!found) {
      String text = "Item '" + name + "' not found!";
      JOptionPane optionPane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE);
      JDialog alert = optionPane.createDialog("Check Out Error!");
      alert.setAlwaysOnTop(true);
      alert.setVisible(true);

      return;
    }

    // clear entry fields after item added
    addItemField.setText("");
    itemAmountSpinner.setValue(1);
  }

}
