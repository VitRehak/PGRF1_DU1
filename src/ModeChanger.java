import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModeChanger {
    private final JPanel panel;
    private int mode;

    public ModeChanger(JPanel panel) {
        this.panel = panel;
        changeMode();
    }

    public int getMode() {
        return mode;
    }

    public void changeMode() {
        panel.requestFocus();
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_NUMPAD1:
                        mode = 1;
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        mode = 2;
                        break;
                    case KeyEvent.VK_NUMPAD3:
                        mode = 3;
                        break;
                    case KeyEvent.VK_NUMPAD4:
                        mode = 4;
                        break;
                    case KeyEvent.VK_NUMPAD5:
                        mode = 5;
                }
            }
        });
    }
}
