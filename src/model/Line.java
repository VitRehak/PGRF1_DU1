package model;


import java.awt.*;

public class Line {
    private final Point source, destination;
    private final Color color;

    public Line(Point source, Point destination, Color color) {
        this.source = source;
        this.destination = destination;
        this.color = color;
    }

    public Point getSource() {
        return source;
    }

    public Point getDestination() {
        return destination;
    }

    public Color getColor() {
        return color;
    }
}

