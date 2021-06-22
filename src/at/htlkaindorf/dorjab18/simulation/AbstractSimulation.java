package at.htlkaindorf.dorjab18.simulation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author dorjab18
 */
public abstract class AbstractSimulation extends JPanel {
    protected SimulationWindow simlationWindow;

    public AbstractSimulation() {
        setPreferredSize(new Dimension(500, 500));
    }
    
    public void setSimulationWindow(SimulationWindow simGUI) {
        this.simlationWindow = simGUI;
    }

    public SimulationWindow getSimulationWindow() {
        return simlationWindow;
    }

    @Override
    protected final void paintComponent(Graphics g) {  
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        draw(g2d);
    }
    
    public abstract void timeElapsed(double deltaTime);
    
    public abstract void reset();
    
    public abstract void draw(Graphics2D g2d);
    
    public abstract String getSimulationName();
}