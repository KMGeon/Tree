import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class project3 extends JFrame implements
        KeyListener {
    private GameHandler handler;
    private JTextArea textArea = new JTextArea();

    public project3() {
        setTitle("Let's play 4 In A Line");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 30));
        textArea.addKeyListener(this);
        add(textArea);
        textArea.setEditable(false);
        setVisible(true);
        handler = new GameHandler(textArea);
        new Thread(new GameThread()).start();
    }

    public static void main(String[] args) {
        new project3();
    }

    class GameThread implements Runnable {
        @Override
        public void run() {
            while (!handler.isGameOver()) {
                handler.drawAll();
                handler.gameTiming();
            }
            handler.drawGameOver();
        }
    }

    public void restart() {
        handler.initData();
        new Thread(new GameThread()).start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                handler.moveRightStone();
                break;
            case KeyEvent.VK_LEFT:
                handler.moveLeftStone();
                break;
            case KeyEvent.VK_DOWN:
                handler.moveDownStone();
                break;
            case KeyEvent.VK_Y:
                if (handler.isGameOver())
                    restart();
                break;
            case KeyEvent.VK_N:
                if (handler.isGameOver())
                    System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
