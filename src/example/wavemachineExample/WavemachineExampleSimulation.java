package example.wavemachineExample;

import at.htlkaindorf.dorjab18.simulation.AbstractSimulation;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author dorjab18, HS
 */
public class WavemachineExampleSimulation extends AbstractSimulation {
    // wavemachine configuration - "physics" variables
    private Wavemachine machine;
    private final int NUM_BODIES = 50;
    private final double BODY_MASS = 0.01;
    private final double COUPLING_K = 0.1;

    // simulation related variables
    private final int SUB_TIMESTEPS = 100; 
    
    // display variables
    private final int DISP_MARGIN = 20;
    private final int DISP_BODY_SIZE = 20;
    
    /**
     * Creates new form WavemachineExampleSimulation
     */
    public WavemachineExampleSimulation() {
        initComponents();
        
        setPreferredSize(new Dimension(1200, 500));
        
        machine = new Wavemachine(NUM_BODIES, BODY_MASS, COUPLING_K);
    }
    
    private Point simToScreenCoordinates(BodyPosition pos) {
        int width = getWidth() - 2*DISP_MARGIN;
        int offsetX = DISP_MARGIN;
        int offsetY = getHeight()/2;
        
        double simToScreenScale = (double)width/machine.getWidth();
        double screenPosX = pos.xPosition * simToScreenScale + offsetX;
        double screenPosY = -pos.displacement * simToScreenScale + offsetY;
        
        return new Point((int) screenPosX, (int) screenPosY);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void timeElapsed(double deltaTime) {
        // deltaTime is given in seconds
        for (int i=0; i<SUB_TIMESTEPS; i++){
            machine.calculateNewPosition(deltaTime/(1000*SUB_TIMESTEPS));
        }
    }

    @Override
    public void reset() {
        machine.reset();
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (int i=0; i<NUM_BODIES; i++){
            BodyPosition pos = this.machine.getPosition(i);
            Point coords = this.simToScreenCoordinates(pos);
            g2d.fillOval(coords.x-DISP_BODY_SIZE/2, 
                    coords.y-DISP_BODY_SIZE/2, 
                    DISP_BODY_SIZE, 
                    DISP_BODY_SIZE); 
        }
    }

    @Override
    public String getSimulationName() {
        return "Wavemachine Example Simulation";
    }
}
