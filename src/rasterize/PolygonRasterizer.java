package rasterize;

import model.Polygon;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class PolygonRasterizer{

    private final LineRasterizer lineRasterizer;
    private final LineRasterizer dottedLineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer,LineRasterizer dottedLineRasterizer) {
        this.dottedLineRasterizer = dottedLineRasterizer;
        this.lineRasterizer = lineRasterizer;
    }

    public void rasterize(Polygon p) {
        for (int i = 0; i < p.getPoints().size(); i++)
            if (i < p.getPoints().size() - 1)
                lineRasterizer.rasterize(
                        p.getPoints().get(i).x,
                        p.getPoints().get(i).y,
                        p.getPoints().get(i + 1).x,
                        p.getPoints().get(i + 1).y,
                        p.getColor());
    }

    public void rasterize(List<Polygon> polygons) {
        polygons.forEach(p -> {
            for (int i = 0; i < p.getPoints().size(); i++)
                if (i < p.getPoints().size() - 1)
                    lineRasterizer.rasterize(
                            p.getPoints().get(i).x,
                            p.getPoints().get(i).y,
                            p.getPoints().get(i + 1).x,
                            p.getPoints().get(i + 1).y,
                            p.getColor());
            lineRasterizer.rasterize(
                    p.getPoints().get(0).x,
                    p.getPoints().get(0).y,
                    p.getPoints().get(p.getPoints().size() - 1).x,
                    p.getPoints().get(p.getPoints().size() - 1).y,
                    p.getColor());
        });
    }

    public void assistantLines(MouseEvent e, Polygon polygon) {
        if (polygon != null)
            if (polygon.getPoints().size() == 1) {
                dottedLineRasterizer.rasterize(polygon.getPoints().get(0).x, polygon.getPoints().get(0).y, e.getX(), e.getY(), Color.RED);
            } else if (polygon.getPoints().size() >= 2) {
                dottedLineRasterizer.rasterize(
                        polygon.getPoints().get(0).x,
                        polygon.getPoints().get(0).y,
                        e.getX(),
                        e.getY(),
                        Color.RED);
                dottedLineRasterizer.rasterize(
                        e.getX(),
                        e.getY(),
                        polygon.getPoints().get(polygon.getPoints().size() - 1).x,
                        polygon.getPoints().get(polygon.getPoints().size() - 1).y,
                        Color.RED);
            }
    }
}
