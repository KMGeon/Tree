import javax.swing.*;
import java.io.*;

public class GameHandler {
    private final int SCREEN_WIDTH = 38;;
    private final int SCREEN_HEIGHT = 25;
    private final int LEFT_PADDING = 1;
    private final int UP_PADDING = 2;
    private final int FIELD_WIDTH = 13;
    private final int FIELD_HEIGHT = 6;
    private JTextArea textArea;
    private char Stone;
    private int moveStone;
    private char[][] buffer;
    private int field[];
    private boolean isGameOver;
    private int turn;
    private int BlackScore = 0;
    private int WhiteScore = 0;
    private int PBlackScore;
    private int PWhiteScore;
    public GameHandler(JTextArea ta) {
        textArea = ta;
        field = new int[FIELD_WIDTH * FIELD_HEIGHT];
        buffer = new
                char[SCREEN_WIDTH][SCREEN_HEIGHT];
        initData();
        try {
            BufferedReader in = new
                    BufferedReader(new FileReader("PBlackScore.txt"));
            PBlackScore =
                    Integer.parseInt(in.readLine());
            in.close();
        } catch (FileNotFoundException e) {
            PBlackScore = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new
                    BufferedReader(new FileReader("PWhiteScore.txt"));
            PWhiteScore =
                    Integer.parseInt(in.readLine());
            in.close();
        } catch (FileNotFoundException e) {
            PWhiteScore = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gameTiming() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    private void clearBuffer() {
        for (int y = 0; y < SCREEN_HEIGHT; y++) {
            for (int x = 0; x < SCREEN_WIDTH; x++) {
                buffer[x][y] = ' ';
            }
        }
    }
    public void initData() {
        for (int x = 0; x < FIELD_WIDTH; x++)
            for (int y = 0; y < FIELD_HEIGHT; y++) {
                if (x == 0 && y == 0)
                    field[y * FIELD_WIDTH + x] = 0;
                else if (x % 2 == 1)
                    field[y * FIELD_WIDTH + x] = 1;
                else if (y == 0 && x == FIELD_WIDTH
                        - 1)
                    field[y * FIELD_WIDTH + x] = 2;
                else if (y == 0)
                    field[y * FIELD_WIDTH + x] = 3;
                else if (0 < y && y < FIELD_HEIGHT
                        - 1 && x == 0)
                    field[y * FIELD_WIDTH + x] = 4;
                else if (x == FIELD_WIDTH - 1 && y
                        > 0 && y < FIELD_HEIGHT - 1)
                    field[y * FIELD_WIDTH + x] = 5;
                else if (y == FIELD_HEIGHT - 1 && x
                        == 0)
                    field[y * FIELD_WIDTH + x] = 6;
                else if (y == FIELD_HEIGHT - 1 && x
                        == FIELD_WIDTH - 1)
                    field[y * FIELD_WIDTH + x] = 7;
                else if (y == FIELD_HEIGHT - 1)
                    field[y * FIELD_WIDTH + x] = 8;
                else
                    field[y * FIELD_WIDTH + x] = 9;
            }
        isGameOver = false;
        clearBuffer();
    }
    private void drawToBuffer(int px, int py, String c) {
        for (int x = 0; x < c.length(); x++) {
            buffer[px + x + LEFT_PADDING][py] =
                    c.charAt(x);
        }
    }
    private void drawToBuffer(int px, int py, char c) {
        buffer[px][py] = c;
    }
    public void drawAll() {
        for (int x = 0; x < FIELD_WIDTH; x++) {
            for (int y = 0; y < FIELD_HEIGHT; y++) {
                drawToBuffer(x + LEFT_PADDING, y
                        + UP_PADDING, "┌─┐┬├┤└┘┴┼●○".charAt(field[y *
                        FIELD_WIDTH + x]));
            }
        }
        if (isGameOver) {
            Stone = ' ';
            drawToBuffer(moveStone + 1, 1, Stone);
        } else if (turn == 0) {
            Stone = '●';
            drawToBuffer(moveStone + 1, 1, Stone);
        } else {
            Stone = '○';
            drawToBuffer(moveStone + 1, 1, Stone);
        }
        drawScore();
        drawPScore();
        drawToBuffer(20, 10, " by M. G. Kim");
        render();
    }
    public void drawScore() {
        drawToBuffer(17, 2, "┌─── CURRENT───┐");
        drawToBuffer(17, 3, "│ │");
        drawToBuffer(17, 4, "└──────────────┘");
        drawToBuffer(20, 3, "● :" + BlackScore);
        drawToBuffer(25, 3, " ○ :" + WhiteScore);
    }
    public void drawPScore() {
        drawToBuffer(17, 7, "┌───PREVIOUS───┐");
        drawToBuffer(17, 8, "│ │");
        drawToBuffer(17, 9, "└──────────────┘");
        drawToBuffer(20, 8, "● :" + PBlackScore);
        drawToBuffer(25, 8, " ○ :" + PWhiteScore);
    }
    public void moveRightStone() {
        if (moveStone < 12) {
            drawToBuffer(moveStone + 1, 1, ' ');
            moveStone += 2;
        }
    }
    public void moveLeftStone() {
        if (moveStone > 1) {
            drawToBuffer(moveStone + 1, 1, ' ');
            moveStone -= 2;
        }
    }
    public void moveDownStone() {
        for (int y = FIELD_HEIGHT - 1; y >= 0; y--) {
            if (turn == 0 && (field[y * FIELD_WIDTH +
                    moveStone] != 10 && field[y * FIELD_WIDTH + moveStone]
                    != 11)) {
                field[y * FIELD_WIDTH + moveStone]
                        = 10;
                turn++;
                break;
            } else if (turn == 1
                    && (field[y * FIELD_WIDTH +
                    moveStone] != 10 && field[y * FIELD_WIDTH + moveStone]
                    != 11)) {
                field[y * FIELD_WIDTH + moveStone]
                        = 11;
                turn--;
                break;
            }
        }
    }
    /*
     * public void gameLogic(){
     *
     * }
     */
    public void drawGameOver() {
        drawAll();
        drawToBuffer(15, 7, "╔══════════════╗");
        drawToBuffer(15, 9, "║ ║");
        drawToBuffer(15, 10, "║ AGAIN? (Y/N) ║");
        drawToBuffer(15, 11, "╚══════════════╝");
        if (turn == 1) {
            BlackScore++;
            drawToBuffer(15, 8, "║ ● WINS! ║");
        } else if (turn == 0) {
            WhiteScore++;
            drawToBuffer(15, 8, "║ ○ WINS! ║");
        }
        render();
        BufferedWriter out;
        try {
            out = new BufferedWriter(new
                    FileWriter("PBlackScore.txt"));
            out.write(String.valueOf(BlackScore));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new BufferedWriter(new
                    FileWriter("PWhiteScore.txt"));
            out.write(String.valueOf(WhiteScore));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isGameOver() {
        for (int x = 0; x < FIELD_WIDTH; x++)
            for (int y = 0; y < FIELD_HEIGHT; y++) {
                if (x < 7 && field[y * FIELD_WIDTH +
                        x] == 10 && field[y * FIELD_WIDTH + x + 2] == 10
                        && field[y * FIELD_WIDTH
                        + x + 4] == 10 && field[y * FIELD_WIDTH + x + 6] == 10)
                    isGameOver = true;
                else if (y < 3 && field[y *
                        FIELD_WIDTH + x] == 10 && field[(y + 1) * FIELD_WIDTH +
                        x] == 10
                        && field[(y + 2) *
                        FIELD_WIDTH + x] == 10 && field[(y + 3) * FIELD_WIDTH +
                        x] == 10)
                    isGameOver = true;
                else if (x < 7 && y < 3 && field[y *
                        FIELD_WIDTH + x] == 10
                        && field[(y + 1) *
                        FIELD_WIDTH + x + 2] == 10 && field[(y + 2) *
                        FIELD_WIDTH + x + 4] == 10
                        && field[(y + 3) *
                        FIELD_WIDTH + x + 6] == 10)
                    isGameOver = true;
                else if (x < 7 && y < 3 && field[(y +
                        3) * FIELD_WIDTH + x] == 10
                        && field[(y + 2) *
                        FIELD_WIDTH + x + 2] == 10 && field[(y + 1) *
                        FIELD_WIDTH + x + 4] == 10
                        && field[y * FIELD_WIDTH
                        + x + 6] == 10)
                    isGameOver = true;
                else if (x < 7 && field[y *
                        FIELD_WIDTH + x] == 11 && field[y * FIELD_WIDTH + x +
                        2] == 11
                        && field[y * FIELD_WIDTH
                        + x + 4] == 11 && field[y * FIELD_WIDTH + x + 6] == 11)
                    isGameOver = true;
                else if (y < 3 && field[y *
                        FIELD_WIDTH + x] == 11 && field[(y + 1) * FIELD_WIDTH +
                        x] == 11
                        && field[(y + 2) *
                        FIELD_WIDTH + x] == 11 && field[(y + 3) * FIELD_WIDTH +
                        x] == 11)
                    isGameOver = true;
                else if (x < 7 && y < 3 && field[y *
                        FIELD_WIDTH + x] == 11
                        && field[(y + 1) *
                        FIELD_WIDTH + x + 2] == 11 && field[(y + 2) *
                        FIELD_WIDTH + x + 4] == 11
                        && field[(y + 3) *
                        FIELD_WIDTH + x + 6] == 11)
                    isGameOver = true;
                else if (x < 7 && y < 3 && field[(y +
                        3) * FIELD_WIDTH + x] == 11
                        && field[(y + 2) *
                        FIELD_WIDTH + x + 2] == 11 && field[(y + 1) *
                        FIELD_WIDTH + x + 4] == 11
                        && field[y * FIELD_WIDTH
                        + x + 6] == 11)
                    isGameOver = true;
            }
        return isGameOver;
    }
    private void render() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < SCREEN_HEIGHT; y++) {
            for (int x = 0; x < SCREEN_WIDTH; x++) {
                sb.append(buffer[x][y]);
            }
            sb.append("\n");
        }
        textArea.setText(sb.toString());
    }
}