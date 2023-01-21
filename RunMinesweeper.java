package org.cis1200.minesweeper;

import java.awt.*;
import javax.swing.*;

public class RunMinesweeper implements Runnable {
    private final Color greenish = new Color(62, 86, 50);

    public void run() {
        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Minesweeper");
        frame.setLocation(450, 300);

        // instructions window
        final JFrame instr = new JFrame("");
        instr.setLocation(500, 420);

        final InfoScreen info = new InfoScreen();
        instr.add(info, BorderLayout.CENTER);
        info.setBackground(greenish);

        // Game board
        final GameGrid board = new GameGrid(false);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        control_panel.setBackground(greenish);

        final JButton reset = new JButton("RESET");
        reset.addActionListener(e -> board.reset());
        frame.add(reset, BorderLayout.SOUTH);

        final JButton save = new JButton("SAVE");
        save.addActionListener(e -> board.save());
        control_panel.add(save);

        final JButton load = new JButton("LOAD");
        load.addActionListener(e -> board.load());
        control_panel.add(load);

        final JToggleButton flagMode = new JToggleButton("FLAG MODE");
        flagMode.addActionListener(e -> board.flagToggle());
        control_panel.add(flagMode);

        final JButton instructions = new JButton("?");
        instructions.addActionListener(e -> instr.setVisible(true));
        control_panel.add(instructions);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        instr.pack();
        instr.setVisible(true);

        // Start the game
        board.reset();
    }
}