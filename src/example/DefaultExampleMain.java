package example;

import at.htlkaindorf.dorjab18.simulation.SimulationWindow;
import at.htlkaindorf.dorjab18.simulation.DefaultSimulation;
import at.htlkaindorf.dorjab18.simulation.DefaultControls;

/**
 * @author dorjab18
 */
public class DefaultExampleMain {
    public static void main(String[] args) {
        SimulationWindow simGUI = new SimulationWindow(new DefaultSimulation(),
                new DefaultControls());
        simGUI.setVisible(true);
    }
}