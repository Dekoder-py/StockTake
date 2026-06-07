package dev.codingcorner;

import javax.swing.*;

public class App {
  static JFrame frame;

  public static void main(String[] args) {
    frame = new JFrame("StockTake");

    Panel panel = new Panel();

    frame.add(panel);
    frame.pack();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
