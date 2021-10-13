
import model.Line;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerTrivial;
import rester.RasterBufferdImage;

import java.awt.*;
import java.awt.event.*;
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
    private final ModeChanger modeChanger;

    private int x1, y1;
    private final List<Line> lines = new ArrayList<>();

    public CanvasMouse(int width, int height) {
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        raster = new RasterBufferdImage(width, height);
        lineRasterizer = new LineRasterizerTrivial(raster);
        modeChanger = new ModeChanger(panel);

        /*panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                BufferedImage newImg = new BufferedImage(panel.getWidth(), panel.getHeight(),
                        BufferedImage.TYPE_INT_RGB);
                img = newImg;
                draw();
            }
        });*/
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    lines.clear();
                    x1 = 0;
                    y1 = 0;
                    draw();
                    clear();
                }
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    x1 = e.getX();
                    y1 = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    lines.add(new Line(new Point(x1, y1), new Point(e.getX(), e.getY()), Color.YELLOW));
                }
                draw();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                clear();
                lineRasterizer.rasterize(x1, y1, e.getX(), e.getY(), Color.RED);
                draw();
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
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CanvasMouse(800, 600).start());
    }

    public void draw() {
        lines.forEach(l -> lineRasterizer.rasterize(l.getSource().x, l.getSource().y, l.getDestination().x, l.getDestination().y, l.getColor()));
        panel.repaint();
    }
}