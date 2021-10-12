package rasterize;

import rester.Raster;

public class LineRasterizerTrivial extends LineRasterizer{

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2) {
        float k,q;

        k=((float)(y2-y1)/(x2-x1));
        q=y1-(k*x1);
        for (int x = x1; x <= x2; x++){
            float y= (k*x)+q;
            raster.setPixel(x, (int)y, 0xffff00);
        }

    }
}
