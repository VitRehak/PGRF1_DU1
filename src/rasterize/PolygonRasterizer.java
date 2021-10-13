package rasterize;

import model.Polygon;
import rester.Raster;

import java.util.List;

public class PolygonRasterizer extends LineRasterizer {

    private final LineRasterizer lineRasterizer;

    public PolygonRasterizer(Raster raster, LineRasterizer lineRasterizer) {
        super(raster);
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
                    p.getPoints().get(p.getPoints().size()-1).x,
                    p.getPoints().get(p.getPoints().size()-1).y,
                    p.getColor());
        });
    }
}
