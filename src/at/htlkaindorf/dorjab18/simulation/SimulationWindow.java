package at.htlkaindorf.dorjab18.simulation;

import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author dorjab18
 */
public class SimulationWindow extends JFrame {
    private static final int TIMEOUT = 10;
    
    private AbstractSimulation sim;
    private AbstractControls con;
    
    private double simulationSpeedFactor = 1;
    private boolean simulationRunning = false;
    
    /**
     * Creates new form SimulationGUI
     */
    public SimulationWindow(AbstractSimulation sim, AbstractControls con) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.sim = sim;
        this.con = con;
        
        this.sim.setSimulationWindow(this);
        this.con.setSimulationWindow(this);
        
        setTitle(sim.getSimulationName());
        
        getContentPane().add(sim, BorderLayout.CENTER);
        getContentPane().add(con, BorderLayout.WEST);
        
        pack();
        setLocationRelativeTo(null);
        
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(simulationRunning) {
                    sim.timeElapsed(simulationSpeedFactor * TIMEOUT);
                    
                    repaint();
                }
            }
        }, 0, TIMEOUT);
    }

    public AbstractSimulation getSimulation() {
        return sim;
    }

    public AbstractControls getControlls() {
        return con;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        
        if(!b)
            stopSimulation();
    }

    public void setSimulationSpeedFactor(double simulationSpeedFactor) {
        this.simulationSpeedFactor = simulationSpeedFactor;
    }

    public double getSimulationSpeedFactor() {
        return simulationSpeedFactor;
    }
    
    public void startSimulation() {
        simulationRunning = true;
    }
    
    public void stopSimulation() {
        simulationRunning = false;
    }
    
    public void invertSimulationRunningState() {
        simulationRunning = !simulationRunning;
    }

    public boolean isSimulationRunning() {
        return simulationRunning;
    }
    
    public void reset() {
        stopSimulation();
        sim.reset();
        
        repaint();
    }
    
    public void forceRedraw() {
        repaint();
    }
}