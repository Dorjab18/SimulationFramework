package example.oscillator2DExample;

/**
 *
 * @author dorjab18
 */
public class Oscillator2D {
    private double mass = 1, springConstant, x0, y0, v0, alpha0;
    private double x, y, v, alpha;

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setSpringConstant(double springConstant) {
        this.springConstant = springConstant;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public void setV0(double v0) {
        this.v0 = v0;
    }

    public void setAlpha0(double alpha0) {
        this.alpha0 = alpha0;
    }
    
    public void calculate(double deltaTime) {
        deltaTime /= 1000; //ms -> s
        
        //Calculate velocity & angle
        double vx = v * Math.cos(alpha);
        double vy = v * Math.sin(alpha);
        
        double oldVx = vx;
        double oldVy = vy;
        
        vx += -springConstant / mass * x * deltaTime; //vx += dvx
        vy += -springConstant / mass * y * deltaTime; //vy += dvy
        alpha = Math.atan2(vy, vx);
        v = Math.hypot(vx, vy);
        
        //Calculate location
        x = x + (oldVx + vx) * .5 * deltaTime;
        y = y + (oldVy + vy) * .5 * deltaTime;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public void reset() {
        x = x0;
        y = y0;
        v = v0;
        alpha = alpha0;
    }
}
