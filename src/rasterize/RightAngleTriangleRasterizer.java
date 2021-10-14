package rasterize;

import model.Polygon;
import rester.Raster;

import java.awt.*;

public class RightAngleTriangleRasterizer extends LineRasterizer {

    public RightAngleTriangleRasterizer(Raster raster) {
        super(raster);
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
}

