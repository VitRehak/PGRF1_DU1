package model;

import java.awt.*;

public class Line {
    private final Point source , destination;

    public Line(Point source, Point destination) {
        this.source = source;
        this.destination = destination;
    }

    public Point getSource() {
        return source;
    }

    public Point getDestination() {
        return destination;
    }
}
