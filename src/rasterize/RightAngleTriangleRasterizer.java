package rasterize;

import model.Polygon;
import rester.Raster;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RightAngleTriangleRasterizer extends LineRasterizer {

    private final LineRasterizer lineRasterizer;
    public RightAngleTriangleRasterizer(Raster raster,LineRasterizer lineRasterizer) {
        super(raster);
        this.lineRasterizer = lineRasterizer;
    }

    public Polygon createRightAngleTriangle(Point source, Point destination, Color color) {
        Polygon polygon = new Polygon(color);
        if (source.y < destination.y) {
            Point tmp = new Point(source.x, source.y);
            source = new Point(destination.x, destination.y);
            destination = new Point(tmp.x, tmp.y);
        }
        int x = source.x;
        if (source.x < destination.x)
            while (destination.x > x)
                x++;
        if (source.x > destination.x)
            while (destination.x < x)
                x--;
        polygon.addPoint(source);
        polygon.addPoint(destination);
        polygon.addPoint(new Point(x, source.y));

        return polygon;
    }
    public void assistanLines(MouseEvent e, Point source, Color color){
        Point destination = new Point(e.getX(),e.getY());
        if (source.y < destination.y) {
            Point tmp = new Point(source.x, source.y);
            source = new Point(destination.x, destination.y);
            destination = new Point(tmp.x, tmp.y);
        }
        int x = source.x;
        if (source.x < destination.x)
            while (destination.x > x)
                x++;
        if (source.x > destination.x)
            while (destination.x < x)
                x--;
            lineRasterizer.rasterize(source.x, source.y, e.getX(),e.getY(),color);
            lineRasterizer.rasterize(destination.x, e.getY(), e.getX(),e.getY(),color);
    }
}

