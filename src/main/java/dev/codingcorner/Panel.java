package dev.codingcorner;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Panel extends JPanel {

  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  public Panel() {

    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setBackground(Color.WHITE);
  }

}
