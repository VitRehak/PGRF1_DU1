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

    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        BufferedImage img = ((RasterBufferdImage) raster).getImg();
        Graphics g = img.getGraphics();
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
