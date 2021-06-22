package example.circleGraphExample;

import at.htlkaindorf.dorjab18.util.GraphLine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dorjab18
 */
public class CircleCalculator {
    private double radius;

    public CircleCalculator(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    public List<GraphLine.Point> calculatePoints(int pointCount) {
        List<GraphLine.Point> points = new ArrayList<>();
        
        double angleAdd = Math.PI * 2 / pointCount;
        for(double angle = 0;angle < Math.PI * 2;angle += angleAdd) {
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            
            points.add(new GraphLine.Point(x, y));
        }
        
        return points;
    }
}