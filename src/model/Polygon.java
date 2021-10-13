package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private final Color color;
    private final List<Point> points = new ArrayList<>();

    public void addPoint(Point p){
        points.add(p);
    }
    public Polygon(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public List<Point> getPoints() {
        return points;
    }
}
