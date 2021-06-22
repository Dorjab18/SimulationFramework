package at.htlkaindorf.dorjab18.util;

import java.awt.Graphics2D;

/**
 *
 * @author dorjab18
 */
public abstract class GraphObject {
    protected double ppuX = 1, ppuY = 1;
    protected double transX = 0, transY = 0;

    public GraphObject(double ppuX, double ppuY, double transX, double transY) {
        this.ppuX = ppuX;
        this.ppuY = ppuY;
        this.transX = transX;
        this.transY = transY;
    }
    
    public GraphObject() {}

    public double getPpuX() {
        return ppuX;
    }

    public void setPpuX(double ppuX) {
        this.ppuX = ppuX;
    }

    public double getPpuY() {
        return ppuY;
    }

    public void setPpuY(double ppuY) {
        this.ppuY = ppuY;
    }

    public double getTransX() {
        return transX;
    }

    public void setTransX(double transX) {
        this.transX = transX;
    }

    public double getTransY() {
        return transY;
    }

    public void setTransY(double transY) {
        this.transY = transY;
    }
    
    public abstract void draw(Graphics2D g2d);
}