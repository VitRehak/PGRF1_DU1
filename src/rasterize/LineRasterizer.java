package rasterize;

import model.Line;
import rester.Raster;
import rester.RasterBufferdImage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public abstract class LineRasterizer {
    protected Raster raster;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    public void rasterize(int x1, int y1, int x2, int y2) {
        BufferedImage img = ((RasterBufferdImage) raster).getImg();
        img.getGraphics().drawLine(x1, y1, x2, y2);
    }

    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        BufferedImage img = ((RasterBufferdImage) raster).getImg();
        Graphics g = img.getGraphics();
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    public void rasterize(Line line, Color color) {
        rasterize(line.getSource().x, line.getSource().y, line.getDestination().x, line.getDestination().y, color);
    }

    public void lineAssistant(Point source, MouseEvent e) {
        rasterize(source.x, source.y, e.getX(), e.getY(), Color.RED);
    }
}
