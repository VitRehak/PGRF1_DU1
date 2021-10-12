package rester;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferdImage implements Raster {

    private BufferedImage img;

    public RasterBufferdImage(int width,int height) {
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    }

    public RasterBufferdImage(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void present(Graphics graphics){
        graphics.drawImage(img, 0, 0, null);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        img.setRGB(x,y,color);
    }

    @Override
    public void clear() {
        Graphics gr = img.getGraphics();
        gr.setColor(new Color(0x2f2f2f));
        gr.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }
}
