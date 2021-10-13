package rester;

import java.awt.*;

public interface Raster {
    void setPixel(int x, int y, Color color);

    int getWidth();

    int getHeight();

    void clear();
}
