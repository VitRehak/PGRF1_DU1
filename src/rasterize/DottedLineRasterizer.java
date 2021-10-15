package rasterize;

import raster.Raster;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DottedLineRasterizer extends LineRasterizer {
    public DottedLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        float k, q;
        k = ((float) (y2 - y1) / (x2 - x1));
        q = y1 - (k * x1);

        if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
            //ridici osa X
            if (x2 < x1) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
            }

            int xDifference = Math.abs(x2 - x1) + 1;
            int intervalSize = 5;
            int intervalCount = (xDifference) / (intervalSize + 1);
            int rest = (xDifference) % (intervalSize + 1) - 1;


            int i = 18;
            //přebytek bodu 0
            if (rest == 0) {
                for (int x = x1; x <= x2; x++) {
                    if (i % (intervalSize + 1) == 0) {
                        float y = (k * x) + q;
                        raster.setPixel(x, (int) y, color);
                    }
                    i++;
                }

            }
            //sudy pocet intervalu -> odstraneni prostredniho bodu a doplneni přebytku bodu aby byla zajistena symetrie
            else if (intervalCount % 2 == 0) {
                int intervalNum = 0;
                for (int x = x1; x <= x2; x++) {
                    if (i % (intervalSize + 1) == 0 && i >= 18) {
                        if (intervalNum == (intervalCount / 2) - 1) {
                            i = 11 - intervalSize - rest;
                        }
                        intervalNum++;
                        float y = (k * x) + q;
                        raster.setPixel(x, (int) y, color);
                    }
                    i++;
                }

            }
            //lichy pocet intervalu -> doplneni prebytku bodu do prostredniho aby byla zajistena symetrie
            else if (intervalCount % 2 == 1) {
                int intervalNum = 0;
                for (int x = x1; x <= x2; x++) {
                    if (i % (intervalSize + 1) == 0 && i >= 18) {
                        if (intervalNum == ((float) (intervalCount - 1) / 2)) {
                            i = 17 - intervalSize - rest;
                        }
                        intervalNum++;
                        float y = (k * x) + q;
                        raster.setPixel(x, (int) y, color);
                    }
                    i++;
                }
            }
        } else {
            //ridici osa Y
            if (y2 < y1) {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }

            int yDifference = Math.abs(y2 - y1) + 1;
            int intervalSize = 5;
            int intervalCount = (yDifference) / (intervalSize + 1);
            int rest = (yDifference) % (intervalSize + 1) - 1;


            int i = 18;
            //specialni pripad pro svisle cary
            if (x1 == x2) {
                //přebytek bodu 0
                if (rest == 0) {
                    for (int y = y1; y <= y2; y++) {
                        if (i % (intervalSize + 1) == 0) {
                            raster.setPixel(x1, y, color);
                        }
                        i++;
                    }
                }
                //sudy pocet intervalu -> odstraneni prostredniho bodu a doplneni přebytku bodu aby byla zajistena symetrie
                else if (intervalCount % 2 == 0) {
                    int intervalNum = 0;
                    for (int y = y1; y <= y2; y++) {
                        if (i % (intervalSize + 1) == 0 && i >= 18) {
                            if (intervalNum == (intervalCount / 2) - 1) {
                                i = 11 - intervalSize - rest;
                            }
                            intervalNum++;
                            raster.setPixel(x1, y, color);
                        }
                        i++;
                    }
                }
                //lichy pocet intervalu -> doplneni prebytku bodu do prostredniho aby byla zajistena symetrie
                else if (intervalCount % 2 == 1) {
                    int intervalNum = 0;
                    for (int y = y1; y <= y2; y++) {
                        if (i % (intervalSize + 1) == 0 && i >= 18) {
                            if (intervalNum == ((float) (intervalCount - 1) / 2)) {
                                i = 17 - intervalSize - rest;
                            }
                            intervalNum++;
                            raster.setPixel(x1, y, color);
                        }
                        i++;
                    }
                }
            } else {
                //přebytek bodu 0
                if (rest == 0) {
                    for (int y = y1; y <= y2; y++) {
                        if (i % (intervalSize + 1) == 0) {
                            float x = (y - q) / k;
                            raster.setPixel((int) x, y, color);
                        }
                        i++;
                    }
                }
                //sudy pocet intervalu -> odstraneni prostredniho bodu a doplneni přebytku bodu aby byla zajistena symetrie
                else if (intervalCount % 2 == 0) {
                    int intervalNum = 0;
                    for (int y = y1; y <= y2; y++) {
                        if (i % (intervalSize + 1) == 0 && i >= 18) {
                            if (intervalNum == (intervalCount / 2) - 1) {
                                i = 11 - intervalSize - rest;
                            }
                            intervalNum++;
                            float x = (y - q) / k;
                            raster.setPixel((int) x, y, color);
                        }
                        i++;
                    }
                }
                //lichy pocet intervalu -> doplneni prebytku bodu do prostredniho aby byla zajistena symetrie
                else if (intervalCount % 2 == 1) {
                    int intervalNum = 0;
                    for (int y = y1; y <= y2; y++) {
                        if (i % (intervalSize + 1) == 0 && i >= 18) {
                            if (intervalNum == ((float) (intervalCount - 1) / 2)) {
                                i = 17 - intervalSize - rest;
                            }
                            intervalNum++;
                            float x = (y - q) / k;
                            raster.setPixel((int) x, y, color);
                        }
                        i++;
                    }
                }
            }
        }
    }

    public void lineAssistant(Point source, MouseEvent e, Color color) {
        rasterize(source.x, source.y, e.getX(), e.getY(), color);
    }
}
