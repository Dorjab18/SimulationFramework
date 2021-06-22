package at.htlkaindorf.dorjab18.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author dorjab18
 */
public class GraphDrawer {
    private List<GraphObject> graphObjects;
    
    public GraphDrawer() {
        graphObjects = new ArrayList<>();
    }
    
    public GraphLine createGaphLine(Color lineColor, Color pointColor,
            boolean drawLine, boolean drawPoints) {
        GraphLine gl = new GraphLine(lineColor, pointColor, drawLine, drawPoints);
        
        graphObjects.add(gl);
        
        return gl;
    }
    public GraphLine createGaphLine(boolean drawLine, boolean drawPoints) {
        GraphLine gl = new GraphLine(drawLine, drawPoints);
        
        graphObjects.add(gl);
        
        return gl;
    }
    public GraphLine createGaphLine() {
        GraphLine gl = new GraphLine();
        
        graphObjects.add(gl);
        
        return gl;
    }
    
    public GraphText createGraphText(String text, Font font) {
        GraphText gt = new GraphText(text, font);
        
        graphObjects.add(gt);
        
        return gt;
    }
    public GraphText createGraphText(String text, Font font, Color c) {
        GraphText gt = new GraphText(text, font, c);
        
        graphObjects.add(gt);
        
        return gt;
    }
    public GraphText createGraphText(String text, Font font, double x,
            double y) {
        GraphText gt = new GraphText(text, font, x, y);
        
        graphObjects.add(gt);
        
        return gt;
    }
    public GraphText createGraphText(String text, Font font, Color c, double x,
            double y) {
        GraphText gt = new GraphText(text, font, c, x, y);
        
        graphObjects.add(gt);
        
        return gt;
    }
    
    public GraphImage createGraphImage(BufferedImage img, double x, double y,
            double width, double height) {
        GraphImage gi = new GraphImage(img, x, y, width, height);
        
        graphObjects.add(gi);
        
        return gi;
    }
    
    public void addGraphLine(GraphLine gl) {
        graphObjects.add(gl);
    }
    
    public void removeGraphLine(int index) {
        graphObjects.remove(index);
    }
    
    private Stream<GraphLine> getGraphLineStream() {
        return graphObjects.stream().
                filter(go -> go instanceof GraphLine).
                map(go -> (GraphLine)go);
    }
    
    public void clearPoints() {
        getGraphLineStream().forEach(gl -> gl.clearPoints());
    }

    public void setPpuX(double ppuX) {
        graphObjects.forEach(gl -> gl.setPpuX(ppuX));
    }

    public void setPpuY(double ppuY) {
        graphObjects.forEach(gl -> gl.setPpuY(ppuY));
    }

    public void setTransX(double transX) {
        graphObjects.forEach(gl -> gl.setTransX(transX));
    }

    public void setTransY(double transY) {
        graphObjects.forEach(gl -> gl.setTransY(transY));
    }

    public void setDrawLine(boolean drawLine) {
        getGraphLineStream().forEach(gl -> gl.setDrawLine(drawLine));
    }

    public void setDrawPoints(boolean drawPoints) {
        getGraphLineStream().forEach(gl -> gl.setDrawPoints(drawPoints));
    }
    
    public void setLineColor(Color lineColor) {
        getGraphLineStream().forEach(gl -> gl.setLineColor(lineColor));
    }
    
    public void setPointColor(Color pointColor) {
        getGraphLineStream().forEach(gl -> gl.setPointColor(pointColor));
    }
    
    public void setPointSize(int pointSize) {
        getGraphLineStream().forEach(gl -> gl.setPointSize(pointSize));
    }
    
    public void setLineStyle(GraphLine.LineStyle lineStyle) {
        getGraphLineStream().forEach(gl -> gl.setLineStyle(lineStyle));
    }
    
    public void setPointStyle(GraphLine.PointStyle pointStyle) {
        getGraphLineStream().forEach(gl -> gl.setPointStyle(pointStyle));
    }
    
    public void setPointFlag(GraphLine.PointFlag pointFlag) {
        getGraphLineStream().forEach(gl -> gl.setPointFlag(pointFlag));
    }
    
    public void draw(Graphics2D g2d) {
        graphObjects.forEach(gl -> gl.draw(g2d));
    }
}
