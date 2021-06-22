package at.htlkaindorf.dorjab18.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author dorjab18
 */
public class GraphImage extends GraphObject {
    private BufferedImage img;
    
    private double x, y;
    private double width, height;

    public GraphImage(BufferedImage img, double x, double y, double width,
            double height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g2d) {
        double x = this.x * ppuX + transX;
        double y = this.y * ppuY + transY;
        
        double width = this.width * ppuX;
        double height = this.height * ppuY;
        
        g2d.drawImage(img, (int)x, (int)y, (int)width, (int)height, null);
    }
}
