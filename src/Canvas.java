import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu
 *
 * @author PGRF FIM UHK
 * @version 2020
 */

public class Canvas {

    private JFrame frame;
    private JPanel panel;
    private BufferedImage img;

    public Canvas(int width, int height) {
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };

        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void clear() {
        Graphics gr = img.getGraphics();
        gr.setColor(new Color(0x2f2f2f));
        gr.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    public void present(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    public void draw() {
        clear();
        for (int i = 0; i <= 90; i++) {
            img.setRGB(10 + i, 10, 0xffff00);
        }
    }

    // 10,10 - 100,50
    public void drawLine(int x1, int y1, int x2, int y2) {
        float xgap = x2 - x1;
        float ygap = y2 - y1;
        //oboje kladny
        if (x2>=x1&&y2>=y1){
            if (xgap > ygap) {
                float ystep = ygap / xgap;
                float x = x1;
                float y = y1;
                while (x != x2) {
                    img.setRGB((int) x, (int) y, 0xffff00);
                    x++;
                    y += ystep;
                }
            } else {
                float xstep = xgap / ygap;
                float x = x1;
                float y = y1;
                while (y != y2) {
                    img.setRGB((int) x, (int) y, 0xffff00);
                    y++;
                    x += xstep;
                }
            }
        }
        //oboje zaporny
        else if(x2<=x1&&y2<=y1){
            if (xgap > ygap) {
                float ystep = ygap / xgap;
                float x = x1;
                float y = y1;
                while (x != x2) {
                    img.setRGB((int) x, (int) y, 0xffff00);
                    x--;
                    y -= ystep;
                }
            } else {
                float xstep = xgap / ygap;
                float x = x1;
                float y = y1;
                while (y != y2) {
                    img.setRGB((int) x, (int) y, 0xffff00);
                    y--;
                    x -= xstep;
                }
            }
        }
    }


    public void start() {
        //draw();
        drawLine(200, 200, 300, 100);
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Canvas(800, 600).start());
    }
}