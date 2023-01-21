package org.cis1200.minesweeper;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class GameGrid extends JPanel {

    private Minesweeper ms;
    private boolean isFlagMode;
    private int minesLeft;

    private boolean gameOver = false;
    private boolean gameWon = false;

    public static final int BOARD_WIDTH = 450;
    public static final int BOARD_HEIGHT = 415;

    private static final Color ONE = new Color(129, 50, 50);
    private static final Color TWO = new Color(50, 50, 129);
    private static final Color THREE = new Color(96, 113, 45);
    private static final Color FOUR = new Color(100, 65, 116);
    private static final Color FIVE = new Color(137, 122, 36);

    private static final Color EVEN_GRID = new Color(169, 208, 100);
    private static final Color ODD_GRID = new Color(141, 166, 80);
    private static final Color EVEN_OPEN = new Color(247, 236, 209);
    private static final Color ODD_OPEN = new Color(242, 224, 179);
    private static final Color GREENISH = new Color(62, 86, 50);

    public Minesweeper getMinesweeper() {
        return ms;
    }

    public GameGrid(boolean flag) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setFocusable(true);

        ms = new Minesweeper();
        isFlagMode = flag;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON3) {
                    ms.setFlagMode(true);
                    System.out.println("this works");
                }

                Point p = e.getPoint();

                if (p.y < 360) {
                    if (!gameOver && !gameWon) {
                        ms.playTurn(p.x / 30, p.y / 30);
                    }
                }

                minesLeft = ms.getNumMines() - ms.getNumFlags();

                gameOver = ms.hasLost();
                gameWon = ms.hasWon();

                updateStatus();
                repaint();
            }
        });

    }

    public void reset() {
        ms.reset();
        isFlagMode = false;
        gameOver = false;
        gameWon = false;
        ms.setFlagMode(false);
        minesLeft = ms.getNumMines();
        repaint();

        requestFocusInWindow();
    }

    private void updateStatus() {
        isFlagMode = ms.isFlag();
    }

    public boolean isOddGrid(int c, int r) {
        if ((c % 2 == 0 && r % 2 == 0) || (c % 2 != 0 && r % 2 != 0)) {
            return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font f = new Font("Helvetica", Font.BOLD, 20);
        Font gameOverFont = new Font("Helvetica", Font.BOLD, 50);
        g.setFont(f);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 15; j++) {
                int state = ms.getCell(j, i);

                if (state == 'm' || state == 'q' || state == 'a' || state == 'b'
                        || state == 'c' || state == 'd' || state == 'e' || state == 'f'
                        || state == 'g' || state == 'h') {
                    g.setColor(EVEN_GRID);
                    if (isOddGrid(j, i)) {
                        g.setColor(ODD_GRID);
                    }
                    g.fillRect(30 * j, 30 * i, 30, 30);
                } else if (state == 'M' || state == 'Q' || state == 'A' || state == 'B'
                        || state == 'C' || state == 'D' || state == 'E' || state == 'F'
                        || state == 'G' || state == 'H') {
                    g.setColor(EVEN_GRID);
                    if (isOddGrid(j, i)) {
                        g.setColor(ODD_GRID);
                    }
                    g.fillRect(30 * j, 30 * i, 30, 30);
                    g.setColor(Color.red);
                    int[] x = { 30 * j + 12, 30 * j + 12, 30 * j + 25 };
                    int[] y = { 30 * i + 5, 30 * i + 20, 30 * i + 12 };
                    g.fillPolygon(x, y, 3);
                    g.fillRect(30 * j + 10, 30 * i + 5, 2, 20);
                } else if (state == 'X') {
                    g.setColor(EVEN_OPEN);
                    if (isOddGrid(j, i)) {
                        g.setColor(ODD_OPEN);
                    }
                    g.fillRect(30 * j, 30 * i, 30, 30);
                    g.setColor(Color.black);
                    g.fillOval(30 * j + 7, 30 * i + 7, 16, 16);
                    g.fillRect(30 * j + 13, 30 * i + 4, 5, 22);
                    g.fillRect(30 * j + 4, 30 * i + 13, 22, 5);
                } else {
                    g.setColor(EVEN_OPEN);
                    if (isOddGrid(j, i)) {
                        g.setColor(ODD_OPEN);
                    }
                    g.fillRect(30 * j, 30 * i, 30, 30);
                    if (state == '1') {
                        g.setColor(ONE);
                        g.drawString("1", 30 * j + 10, 30 * i + 22);
                    } else if (state == '2') {
                        g.setColor(TWO);
                        g.drawString("2", 30 * j + 10, 30 * i + 22);
                    } else if (state == '3') {
                        g.setColor(THREE);
                        g.drawString("3", 30 * j + 10, 30 * i + 22);
                    } else if (state == '4') {
                        g.setColor(FOUR);
                        g.drawString("4", 30 * j + 10, 30 * i + 22);
                    } else if (state == '5') {
                        g.setColor(FIVE);
                        g.drawString("5", 30 * j + 10, 30 * i + 22);
                    } else if (state == '6') {
                        g.setColor(Color.black);
                        g.drawString("6", 30 * j + 10, 30 * i + 22);
                    } else if (state == '7') {
                        g.setColor(Color.black);
                        g.drawString("7", 30 * j + 10, 30 * i + 22);
                    } else if (state == '8') {
                        g.setColor(Color.black);
                        g.drawString("8", 30 * j + 10, 30 * i + 22);
                    }
                }

            }
        }

        if (gameOver) {
            g.setFont(gameOverFont);
            g.setColor(Color.red);
            g.drawString("GAME OVER", 80, 170);
            g.setFont(f);
            g.setColor(Color.black);
            g.drawString("Click RESET to play again", 110, 195);
        }
        if (gameWon) {
            g.setFont(gameOverFont);
            g.setColor(TWO);
            ms.setHasWon(false);
            g.drawString("YOU WON!", 90, 170);
            g.setFont(f);
            g.setColor(Color.black);
            g.drawString("Click RESET to play again", 110, 195);
        }
        g.setColor(GREENISH);
        g.fillRect(0, 360, 450, 60);
        g.setColor(ODD_GRID);
        g.drawString("REMAINING MINES", 10, 385);
        g.setColor(EVEN_GRID);
        g.drawString(String.valueOf(minesLeft), 10, 405);
        g.setColor(Color.black);
        g.drawLine(0, 360, 450, 360);
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

    public void flagToggle() {
        boolean placeholder = isFlagMode;
        isFlagMode = !placeholder;
        ms.setFlagMode(isFlagMode);
        requestFocusInWindow();
    }

    public void save() {
        char[][] b = ms.getBoard();
        File file = new File("files/save_state.txt");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < b.length; i++) {
                for (char c : b[i]) {
                    bw.write(c + " ");
                }
                bw.write("\n");
            }

            if (gameOver) {
                ms.setHasLost(true);
                bw.write("Y" + "\n");
            } else {
                ms.setHasLost(false);
                bw.write("N" + "\n");
            }

            if (gameWon) {
                ms.setHasWon(true);
                bw.write("Y" + "\n");
            } else {
                ms.setHasWon(false);
                bw.write("N" + "\n");
            }

            bw.write(String.valueOf(ms.getNumFlags()));
        } catch (IOException e) {
        } finally {
            try {
                assert bw != null;
                bw.close();
            } catch (IOException e) {
            }
        }
    }

    public void load() {
        char[][] newBoard = new char[12][15];
        File file = new File("files/save_state.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            for (int i = 0; i < 12; i++) {
                String[] placeholder = br.readLine().split(" ");
                for (int j = 0; j < 15; j++) {
                    newBoard[i][j] = placeholder[j].charAt(0);
                }
            }
            char isGameOver = br.readLine().charAt(0);
            char isGameWon = br.readLine().charAt(0);
            int flags = Integer.parseInt(br.readLine());

            if (isGameOver == 'N') {
                gameOver = false;
                ms.setHasLost(false);
            } else {
                gameOver = true;
                ms.setHasLost(true);
            }

            if (isGameWon == 'N') {
                gameWon = false;
                ms.setHasWon(false);
            } else {
                gameWon = true;
                ms.setHasWon(true);
            }

            minesLeft = 35 - flags;

            ms.setBoard(newBoard);
        } catch (Exception e) {
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
            }
        }

        repaint();
    }
}
