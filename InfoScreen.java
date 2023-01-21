package org.cis1200.minesweeper;

import javax.swing.*;
import java.awt.*;

public class InfoScreen extends JPanel {

    public static final int INFO_WIDTH = 350;
    public static final int INFO_HEIGHT = 220;
    private final Color light = new Color(169, 208, 100);
    private final Color dark = new Color(141, 166, 80);

    public InfoScreen() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font f = new Font("Helvetica", Font.BOLD, 40);
        g.setFont(f);
        g.setColor(dark);
        g.drawString("INSTRUCTIONS", 25, 50);
        Font a = new Font("Helvetica", Font.BOLD, 15);
        g.setFont(a);
        g.setColor(light);
        g.drawString("1. Click any cell to begin.", 25, 70);
        g.drawString("2. The number indicates the amount of ", 25, 85);
        g.drawString("neighboring mines.", 25, 100);
        g.drawString("3. You lose when you click a mine.", 25, 115);
        g.drawString("4. To win, uncover all of the non-mine cells.", 25, 130);
        g.drawString("5. Click the FLAG MODE button to toggle", 25, 145);
        g.drawString("flagging.", 25, 160);
        g.drawString("6. To read the instructions again, click the", 25, 175);
        g.drawString("[?] button.", 25, 190);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(INFO_WIDTH, INFO_HEIGHT);
    }
}
