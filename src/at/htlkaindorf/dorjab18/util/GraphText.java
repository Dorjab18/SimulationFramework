package at.htlkaindorf.dorjab18.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author dorjab18
 */
public class GraphText extends GraphObject {
    private String text;
    private Font font;
    private Color color = Color.BLACK;
    private double x = 0, y = 0;
    
    public GraphText(String text, Font font) {
        this.text = text;
        this.font = font;
    }
    
    public GraphText(String text, Font font, Color color) {
        this.text = text;
        this.font = font;
        this.color = color;
    }
    
    public GraphText(String text, Font font, double x, double y) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }
    
    public GraphText(String text, Font font, Color color, double x, double y) {
        this.text = text;
        this.font = font;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
    
    @Override
    public void draw(Graphics2D g2d) {
        double x = this.x * ppuX + transX;
        double y = this.y * ppuY + transY;
        
        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(text, (float)x, (float)y);
    }
}
