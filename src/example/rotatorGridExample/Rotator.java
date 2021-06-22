package example.rotatorGridExample;

/**
 *
 * @author HS, dorjab18
 */
public class Rotator {
    private double length;
    private double angle;

    public Rotator(double length) {
        this.length = length;
    }

    public void calculateNewPosition(double deltaTime) {
        this.angle += deltaTime / 10.;
    }
    
    public void reset() {
        this.angle = 0;
    }
    
    public double getX() {
        return Math.sin(Math.toRadians(this.angle)) * this.length;
    }
    
    public double getY() {
        return Math.cos(Math.toRadians(this.angle)) * this.length;
    }
}