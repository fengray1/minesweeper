package org.cis1200.minesweeper;

import java.util.Random;

public class Minesweeper {

    private char[][] board;

    private int numFlags;
    private boolean gameOver;
    private boolean gameWon;
    private boolean isFlagMode;

    private static final int NUMBER_OF_MINES = 35;

    private static final char COVERED_MINE = 'm';
    private static final char COVERED_EMPTY = 'q';
    private static final char OPENED_EMPTY = 'O';
    private static final char OPENED_MINE = 'X';
    private static final char FLAGGED_MINE = 'M';
    private static final char FLAGGED_EMPTY = 'Q';

    private static final char COVERED_1 = 'a';
    private static final char COVERED_2 = 'b';
    private static final char COVERED_3 = 'c';
    private static final char COVERED_4 = 'd';
    private static final char COVERED_5 = 'e';
    private static final char COVERED_6 = 'f';
    private static final char COVERED_7 = 'g';
    private static final char COVERED_8 = 'h';
    private static final char FLAGGED_1 = 'A';
    private static final char FLAGGED_2 = 'B';
    private static final char FLAGGED_3 = 'C';
    private static final char FLAGGED_4 = 'D';
    private static final char FLAGGED_5 = 'E';
    private static final char FLAGGED_6 = 'F';
    private static final char FLAGGED_7 = 'G';
    private static final char FLAGGED_8 = 'H';
    private static final char OPEN_1 = '1';
    private static final char OPEN_2 = '2';
    private static final char OPEN_3 = '3';
    private static final char OPEN_4 = '4';
    private static final char OPEN_5 = '5';
    private static final char OPEN_6 = '6';
    private static final char OPEN_7 = '7';
    private static final char OPEN_8 = '8';

    public Minesweeper() {
        reset();
    }

    public int getNumMines() {
        return NUMBER_OF_MINES;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] b) {
        board = b;
    }

    public void setFlagMode(boolean b) {
        isFlagMode = b;
    }

    public boolean isBomb(int c, int r) {
        if (board[r][c] == COVERED_MINE) {
            return true;
        }
        return false;
    }

    public int getNumFlags() {
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char space = board[i][j];
                if (space == FLAGGED_1 || space == FLAGGED_2 || space == FLAGGED_3
                        || space == FLAGGED_4
                        || space == FLAGGED_5 || space == FLAGGED_6 || space == FLAGGED_7
                        || space == FLAGGED_8
                        || space == FLAGGED_EMPTY || space == FLAGGED_MINE
                        || space == OPENED_MINE) {
                    result++;
                }
            }
        }
        return result;
    }

    public int getNumberOfMines() {
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char space = board[i][j];
                if (space == OPENED_MINE || space == FLAGGED_MINE || space == COVERED_MINE) {
                    result++;
                }
            }
        }
        return result;
    }

    public boolean checkIfWon() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char space = board[i][j];
                if (space != OPENED_EMPTY && space != FLAGGED_MINE && space != COVERED_MINE
                        && space != OPEN_1
                        && space != OPEN_2 && space != OPEN_3 && space != OPEN_4 && space != OPEN_5
                        &&
                        space != OPEN_6 && space != OPEN_7 && space != OPEN_8) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasLost() {
        return gameOver;
    }

    public void setHasLost(boolean b) {
        gameOver = b;
    }

    public boolean hasWon() {
        return gameWon;
    }

    public void setHasWon(boolean b) {
        gameWon = b;
    }

    public int nearbyBombs(int c, int r) {
        final int h = board.length - 1;
        final int w = board[0].length - 1;
        int result = 0;
        if (c > 0) {
            if (isBomb(c - 1, r)) {
                result++;
            }
            if (r > 0 && isBomb(c - 1, r - 1)) {
                result++;
            }
            if (r < h && isBomb(c - 1, r + 1)) {
                result++;
            }
        }
        if (r > 0 && isBomb(c, r - 1)) {
            result++;
        }
        if (r < h && isBomb(c, r + 1)) {
            result++;
        }
        if (c < w) {
            if (isBomb(c + 1, r)) {
                result++;
            }
            if (r > 0 && isBomb(c + 1, r - 1)) {
                result++;
            }
            if (r < h && isBomb(c + 1, r + 1)) {
                result++;
            }
        }
        return result;
    }

    public void setup() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = COVERED_EMPTY;
            }
        }

        Random rand = new Random();
        int counter = 0;

        while (counter < NUMBER_OF_MINES) {
            int row = rand.nextInt(12);
            int col = rand.nextInt(15);
            if (board[row][col] == COVERED_EMPTY) {
                board[row][col] = COVERED_MINE;
                counter++;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == COVERED_MINE) {
                    continue;
                }
                int p = nearbyBombs(j, i);
                switch (p) {
                    case 1 -> board[i][j] = COVERED_1;
                    case 2 -> board[i][j] = COVERED_2;
                    case 3 -> board[i][j] = COVERED_3;
                    case 4 -> board[i][j] = COVERED_4;
                    case 5 -> board[i][j] = COVERED_5;
                    case 6 -> board[i][j] = COVERED_6;
                    case 7 -> board[i][j] = COVERED_7;
                    case 8 -> board[i][j] = COVERED_8;
                    default -> {
                    }
                }
            }
        }
    }

    /*
     * a- covered 1. A- flagged covered 1. 1- opened 1.
     */
    public boolean playTurn(int c, int r) {
        char currentChar = board[r][c];
        final int boardHeight = board.length - 1;
        final int boardWidth = board[0].length - 1;
        if (isFlagMode) {
            switch (currentChar) {
                case COVERED_MINE -> board[r][c] = FLAGGED_MINE;
                case COVERED_EMPTY -> board[r][c] = FLAGGED_EMPTY;
                case FLAGGED_MINE -> board[r][c] = COVERED_MINE;
                case FLAGGED_EMPTY -> board[r][c] = COVERED_EMPTY;
                case COVERED_1 -> board[r][c] = FLAGGED_1;
                case COVERED_2 -> board[r][c] = FLAGGED_2;
                case COVERED_3 -> board[r][c] = FLAGGED_3;
                case COVERED_4 -> board[r][c] = FLAGGED_4;
                case COVERED_5 -> board[r][c] = FLAGGED_5;
                case COVERED_6 -> board[r][c] = FLAGGED_6;
                case COVERED_7 -> board[r][c] = FLAGGED_7;
                case COVERED_8 -> board[r][c] = FLAGGED_8;
                case FLAGGED_1 -> board[r][c] = COVERED_1;
                case FLAGGED_2 -> board[r][c] = COVERED_2;
                case FLAGGED_3 -> board[r][c] = COVERED_3;
                case FLAGGED_4 -> board[r][c] = COVERED_4;
                case FLAGGED_5 -> board[r][c] = COVERED_5;
                case FLAGGED_6 -> board[r][c] = COVERED_6;
                case FLAGGED_7 -> board[r][c] = COVERED_7;
                case FLAGGED_8 -> board[r][c] = COVERED_8;
                default -> {
                }
            }
        } else {
            switch (currentChar) {
                case COVERED_MINE -> {
                    board[r][c] = OPENED_MINE;
                    gameOver = true;
                }
                case COVERED_EMPTY -> {
                    board[r][c] = OPENED_EMPTY;
                    playTurn(Integer.max(c - 1, 0), r);
                    playTurn(Integer.min(c + 1, boardWidth), r);
                    playTurn(Integer.max(c - 1, 0), Integer.min(r + 1, boardHeight));
                    playTurn(c, Integer.min(r + 1, boardHeight));
                    playTurn(Integer.min(c + 1, boardWidth), Integer.min(r + 1, boardHeight));
                    playTurn(Integer.max(c - 1, 0), Integer.max(r - 1, 0));
                    playTurn(c, Integer.max(r - 1, 0));
                    playTurn(Integer.min(c + 1, boardWidth), Integer.max(r - 1, 0));
                }
                case FLAGGED_MINE -> board[r][c] = COVERED_MINE;
                case FLAGGED_EMPTY -> board[r][c] = COVERED_EMPTY;
                case COVERED_1 -> board[r][c] = OPEN_1;
                case COVERED_2 -> board[r][c] = OPEN_2;
                case COVERED_3 -> board[r][c] = OPEN_3;
                case COVERED_4 -> board[r][c] = OPEN_4;
                case COVERED_5 -> board[r][c] = OPEN_5;
                case COVERED_6 -> board[r][c] = OPEN_6;
                case COVERED_7 -> board[r][c] = OPEN_7;
                case COVERED_8 -> board[r][c] = OPEN_8;
                default -> {
                }
            }
        }
        if (checkIfWon()) {
            gameWon = true;
        }
        return true;
    }

    public void printGameState() {
        System.out.println("\n\nTurn " + numFlags + ":\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            if (i < 2) {
                System.out.println("\n---------");
            }
        }
    }

    public void reset() {
        board = new char[12][15];
        numFlags = NUMBER_OF_MINES;
        isFlagMode = false;
        gameOver = false;
        setup();
    }

    public int getCell(int c, int r) {
        return board[r][c];
    }

    public static void main(String[] args) {
        Minesweeper t = new Minesweeper();

        t.playTurn(1, 1);
        t.printGameState();

        t.playTurn(0, 0);
        t.printGameState();

        t.playTurn(0, 2);
        t.printGameState();

        t.playTurn(2, 0);
        t.printGameState();

        t.playTurn(1, 0);
        t.printGameState();

        t.playTurn(1, 2);
        t.printGameState();

        t.playTurn(0, 1);
        t.printGameState();

        t.playTurn(2, 2);
        t.printGameState();

        t.playTurn(2, 1);
        t.printGameState();
        System.out.println();
        System.out.println();
    }

    public boolean isFlag() {
        return isFlagMode;
    }

}