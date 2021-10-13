
import model.Line;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerBufferedImage;
import rester.RasterBufferdImage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu, ovladani mysi
 *
 * @author PGRF FIM UHK
 * @version 2020
 */
public class CanvasMouse {

    private final JPanel panel;
    private final RasterBufferdImage raster;
    private final LineRasterizer lineRasterizer;
    private int x1, y1;
    private final List<Line> lines = new ArrayList<>();

    public CanvasMouse(int width, int height) {
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferdImage(width, height);
        lineRasterizer = new LineRasterizerBufferedImage(raster);
        panel = new JPanel() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    x1 = e.getX();
                    y1 = e.getY();
                }
                if (e.getButton() == MouseEvent.BUTTON2)
                    raster.setPixel(e.getX(), e.getY(), 0xff00ff);
                if (e.getButton() == MouseEvent.BUTTON3)
                    raster.setPixel(e.getX(), e.getY(), 0xffffff);
                panel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    lines.add(new Line(new Point(x1, y1), new Point(e.getX(), e.getY())));
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                clear();
                lineRasterizer.rasterize(x1, y1, e.getX(), e.getY());
                panel.repaint();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                draw(e);
            }
        });
    }

    public void clear() {
        raster.clear();
    }

    public void present(Graphics graphics) {
        raster.present(graphics);
    }

    public void start() {
        clear();
        clear();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CanvasMouse(800, 600).start());
    }

    public void draw(MouseEvent e) {
        clear();
        lineRasterizer.rasterize(x1, y1, e.getX(), e.getY());
        lines.forEach(l -> lineRasterizer.rasterize(l.getSource().x, l.getSource().y, l.getDestination().x, l.getDestination().y));
        panel.repaint();
    }
}