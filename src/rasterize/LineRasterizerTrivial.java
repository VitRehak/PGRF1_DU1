package rasterize;

import rester.Raster;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, Color color) {

        float k, q;
        k = ((float) (y2 - y1) / (x2 - x1));
        q = y1 - (k * x1);


        if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
            if (x2 < x1) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            for (int x = x1; x <= x2; x++) {
                float y = (k * x) + q;
                raster.setPixel(x, (int) y, color);
            }
        } else {
            if (y2 < y1) {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }
            if (x1 == x2) {
                for (int y = y1; y <= y2; y++)
                    raster.setPixel(x1, y, color);
            } else {
                for (int y = y1; y <= y2; y++) {
                    float x = (y - q) / k;
                    raster.setPixel((int) x, y, color);
                }
            }
        }
    }
}
