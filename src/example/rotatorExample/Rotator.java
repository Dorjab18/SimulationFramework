package example.rotatorExample;

/**
 * @author HS, dorjab18
 */
public class Rotator {
    private double length;
    private double angle;
    private double angleStart;

    public Rotator(double length, double angleStart) {
        this.length = length;
        this.angle = this.angleStart = angleStart;
    }

    public void calculateNewPosition(double deltaTime) {
        this.angle += .5 * deltaTime;
    }
    
    public void reset() {
        this.angle = angleStart;
    }
    
    public double getX() {
        return Math.sin(Math.toRadians(this.angle)) * this.length;
    }
    
    public double getY() {
        return Math.cos(Math.toRadians(this.angle)) * this.length;
    }
}