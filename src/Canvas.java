import rasterize.LineRasterizer;
import rasterize.LineRasterizerTrivial;
import rester.Raster;
import rester.RasterBufferdImage;

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

    private LineRasterizer lineRasterizer;
    private Raster raster;
    private JFrame frame;
    private JPanel panel;
    private CanvasMouse canvasMouse;
    //private BufferedImage img;

    public Canvas(int width, int height) {
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferdImage(800,600);
        lineRasterizer = new LineRasterizerTrivial(raster);
        //img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

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
        canvasMouse = new CanvasMouse(panel.getWidth(),panel.getHeight());
    }

    public void clear() {
        raster.clear();
    }

    public void present(Graphics graphics) {
        ((RasterBufferdImage)raster).present(graphics);
    }

    public void draw() {
        clear();
        lineRasterizer.rasterize(10,10,100,50);
        lineRasterizer.rasterize(10,10,100,250);
    }


    public void start() {
        draw();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Canvas(800, 600).start());
    }
}