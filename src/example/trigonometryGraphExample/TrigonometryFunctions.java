package example.trigonometryGraphExample;

/**
 *
 * @author dorjab18
 */
public class TrigonometryFunctions {
    private double amplitude;
    private double x;

    public TrigonometryFunctions(double amplitude) {
        this.amplitude = amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public void calculate(double deltaTime) {
        this.x += deltaTime / 1000.;
    }
    
    public void reset() {
        this.x = 0;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getSinY() {
        return amplitude * Math.sin(Math.toRadians(this.x * 1000));
    }
    
    public double getCosY() {
        return amplitude * Math.cos(Math.toRadians(this.x * 1000));
    }
    
    public double getTanY() {
        return amplitude * Math.tan(Math.toRadians(this.x * 1000));
    }
}