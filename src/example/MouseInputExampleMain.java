package example;

import example.mosueInputExample.MouseInputExampleSimulation;
import at.htlkaindorf.dorjab18.simulation.DefaultControls;
import at.htlkaindorf.dorjab18.simulation.SimulationWindow;

/**
 * @author dorjab18
 */
public class MouseInputExampleMain {
    public static void main(String[] args) {
        SimulationWindow simGUI = new SimulationWindow(
              new MouseInputExampleSimulation(), new DefaultControls());
        simGUI.setVisible(true);
    }
}