package rasterize;

import model.Line;
import rester.Raster;


public abstract class LineRasterizer {
    protected Raster raster;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    public void rasterize(int x1, int y1, int x2, int y2){

    }
    public void rasterize(Line line){
        rasterize(line.getSource().x,line.getSource().y,line.getDestination().x,line.getDestination().y);
    }
}
