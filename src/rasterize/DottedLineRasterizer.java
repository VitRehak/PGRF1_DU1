package rasterize;

import rester.Raster;

import java.awt.*;

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
            if (x2 < x1) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
            }

            int xDiference = Math.abs(x2 - x1) - 1;
            int intervalSize = 5;
            //pocet pravidelnych itervalu
            int intervalCount = (xDiference) / (intervalSize + 1);
            int rest = (xDiference) % (intervalSize + 1)-1;

            int i = 6;
            //rest == 0
            if (rest == 0){
                for (int x = x1; x <= x2; x++) {
                    if (i % (intervalSize + 1) == 0) {
                        float y = (k * x) + q;
                        raster.setPixel(x, (int) y, color);
                    }
                    i++;
                }
            }
            //asi blbost
            //pocet pravidelnych intervalu je sudy
            else if(intervalCount%2==0){
                int intervalNum=0;
                for (int x = x1; x <= x2; x++) {
                    if (i % (intervalSize + 1) == 0) {
                        if(intervalNum==0||intervalNum==intervalCount-1){
                            i=6-rest/2;
                        }
                        intervalNum++;
                        float y = (k * x) + q;
                        raster.setPixel(x, (int) y, color);
                    }
                    i++;
                }
            }
            else if(intervalCount%2==1){
                int intervalNum=0;
                for (int x = x1; x <= x2; x++) {
                    if (i % (intervalSize + 1) == 0) {
                        if(intervalNum==((float)(intervalCount-1)/2)){
                            i=6-rest;
                        }
                        intervalNum++;
                        float y = (k * x) + q;
                        raster.setPixel(x, (int) y, color);
                    }
                    i++;
                }
            }
            /*for (int x = x1; x <= x2; x++) {
                float y = (k * x) + q;
                raster.setPixel(x, (int) y, color);
            }*/
        } else {
            if (y2 < y1) {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }

            int yDiference = Math.abs(y2 - y1) - 1;
            int intervalSize = 5;
            //pocet pravidelnych itervalu + 1 zbytkovy
            int intervalCount = (yDiference) / (intervalSize + 1) + 1;
            int rest = (yDiference) % (intervalSize + 1) - 1;

            if (x1 == x2) {
                for (int y = y1; y <= y2; y++)
                    raster.setPixel(x1, y, color);
            } else {
                for (int y = y1; y <= y2; y++) {
                    float x = (y - q) / k;
                    raster.setPixel((int) x, y, color);
                }
            }
        }
    }
}
