
import model.Line;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerTrivial;
import rasterize.PolygonRasterizer;
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
    private final PolygonRasterizer polygonRasterizer;

    private int x1 = -1;
    private int y1 = -1;
    private Polygon polygon;

    private final List<Line> lines = new ArrayList<>();
    private final List<Polygon> polygons = new ArrayList<>();

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
        polygonRasterizer = new PolygonRasterizer(raster, lineRasterizer);
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
                    clearValues();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    creatPolygon();
                }
            }
        });
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                clear();

                //line
                if (modeChanger.getMode() == 1) {
                    if (x1 == -1 && y1 == -1) {
                        x1 = e.getX();
                        y1 = e.getY();
                    } else {
                        lines.add(new Line(new Point(x1, y1), new Point(e.getX(), e.getY()), Color.YELLOW));
                        x1 = -1;
                        y1 = -1;
                    }
                }
                //polygon
                else if (modeChanger.getMode() == 2) {
                    if (polygon == null)
                        polygon = new Polygon(Color.YELLOW);
                    polygon.addPoint(new Point(e.getX(), e.getY()));
                    polygonAssistantLines(e);
                }

                draw();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                clear();

                //line
                if (modeChanger.getMode() == 1)
                    lineAssistantLines(e);
                    //polygon
                else if (modeChanger.getMode() == 2)
                    polygonAssistantLines(e);

                draw();
            }
        });
    }

    public void creatPolygon() {
        clear();
        polygons.add(polygon);
        polygon = null;
        polygonRasterizer.rasterize(polygons);
        draw();
    }

    public void clearValues() {
        lines.clear();
        x1 = -1;
        y1 = -1;
        polygon = null;
        polygons.clear();
        clear();
        panel.repaint();
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
        if (polygons.size() > 0)
            polygonRasterizer.rasterize(polygons);
        if (polygon != null)
            polygonRasterizer.rasterize(polygon);
        panel.repaint();
    }

    public void polygonAssistantLines(MouseEvent e) {
        if (polygon != null)
            if (polygon.getPoints().size() >= 2) {
                lineRasterizer.rasterize(
                        polygon.getPoints().get(0).x,
                        polygon.getPoints().get(0).y,
                        e.getX(),
                        e.getY(),
                        Color.RED);
                lineRasterizer.rasterize(
                        e.getX(),
                        e.getY(),
                        polygon.getPoints().get(polygon.getPoints().size() - 1).x,
                        polygon.getPoints().get(polygon.getPoints().size() - 1).y,
                        Color.RED);
            }
    }

    public void lineAssistantLines(MouseEvent e) {
        if (x1 >= 0 && y1 >= 0)
            lineRasterizer.rasterize(x1, y1, e.getX(), e.getY(), Color.RED);
    }
}