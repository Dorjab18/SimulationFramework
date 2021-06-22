package at.htlkaindorf.dorjab18.simulation;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author dorjab18
 */
public abstract class AbstractControls extends JPanel {
    protected SimulationWindow simlationWindow;

    public AbstractControls() {
        setPreferredSize(new Dimension(200, 0));
    }
    
    public void setSimulationWindow(SimulationWindow simGUI) {
        this.simlationWindow = simGUI;
    }

    public SimulationWindow getSimulationWindow() {
        return simlationWindow;
    }
}