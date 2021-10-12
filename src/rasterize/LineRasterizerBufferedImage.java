package rasterize;

import rester.Raster;
import rester.RasterBufferdImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LineRasterizerBufferedImage extends LineRasterizer{

    public LineRasterizerBufferedImage(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2) {
        BufferedImage img = ((RasterBufferdImage)raster).getImg();
        img.getGraphics().drawLine(x1, y1, x2, y2);
    }
}
