package example.wavemachineExample;

import java.util.Arrays;

/**
 *
 * @author HS, dorjab18
 */
public class Wavemachine {
    private int numBodies;
    private double mass; // in kg
    private double k; // in N/m
    private double displacements[];
    private double xPositions[];
    private double simulationTime;
    private double velocities[];
    
    private final double BODY_DISTANCE = 0.1; // in m

    // variables for excitation of first element
    private final double EXC_OMEGA = 2;       // in rad/s
    private final double EXC_AMPLITUDE = 0.5; // in m


    public Wavemachine(int numBodies, double mass, double k) {
        this.numBodies = numBodies;
        this.mass = mass;
        this.k = k; 
        this.displacements = new double[this.numBodies];
        Arrays.fill(this.displacements, 0.0);
        
        xPositions = new double[this.numBodies];
        for (int i=0; i<numBodies; i++) {
            xPositions[i] = BODY_DISTANCE*i;
        }
        velocities = new double[this.numBodies];
        Arrays.fill(velocities, 0.0);
        
        simulationTime = 0;
    }

    public void calculateNewPosition(double deltaTime) {
        simulationTime += deltaTime;
        
        double[] newDisplacements = new double[this.numBodies];
        
        // set position of first element
        double excPhi = simulationTime*EXC_OMEGA;
        if (Math.abs(excPhi) < Math.PI){
            newDisplacements[0] = EXC_AMPLITUDE*Math.sin(excPhi);
            velocities[0] = EXC_OMEGA*EXC_AMPLITUDE*Math.cos(excPhi);
        } else {
            newDisplacements[0] = 0;
            velocities[0] = 0;
        }
        
        // calculate reaction of the other elements
        for (int i=1; i<this.numBodies; i++) {
            double forceLeft = k * (displacements[i-1]-displacements[i]);
            
            // treatment of rightmost element: 
            // "open end"
            double forceRight = 0;
            if (i<this.numBodies-1) { // the rightmost element does not have a right partner
                forceRight = k * (displacements[i+1]-displacements[i]);
            }
            
            double a = (forceLeft+forceRight)/this.mass;
            double deltaV = a*deltaTime;
            double oldV = velocities[i];
            double newV = oldV + deltaV;
            velocities[i] = newV; // in-place replacement as there are no correlations to the neighbours
            double deltaDisplacement = (oldV+newV)*deltaTime/2;
            newDisplacements[i] = displacements[i] + deltaDisplacement;
        }
        displacements = Arrays.copyOf(newDisplacements, numBodies);
    }
    
    public void reset() {
        Arrays.fill(displacements, 0.0);
        Arrays.fill(velocities, 0.0);
        simulationTime = 0;
    }
    
    public BodyPosition getPosition(int index) {
        return new BodyPosition(xPositions[index], displacements[index]);
    }
    
    public double getWidth(){
        return BODY_DISTANCE * (numBodies-1);
    }
    
}