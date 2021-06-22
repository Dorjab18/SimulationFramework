package example;

import example.rotatorExample.RotatorExampleSimulation;
import at.htlkaindorf.dorjab18.simulation.DefaultControls;
import at.htlkaindorf.dorjab18.simulation.SimulationWindow;

/**
 * @author dorjab18
 */
public class RotatorExampleMain {
    public static void main(String[] args) {
        SimulationWindow simGUI = new SimulationWindow(
              new RotatorExampleSimulation(), new DefaultControls());
        simGUI.setVisible(true);
    }
}