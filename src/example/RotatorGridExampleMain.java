package example;

import at.htlkaindorf.dorjab18.simulation.DefaultControls;
import at.htlkaindorf.dorjab18.simulation.SimulationWindow;
import example.rotatorGridExample.RotatorGridExampleSimulation;

/**
 *
 * @author dorjab18
 */
public class RotatorGridExampleMain {
    public static void main(String[] args) {
        SimulationWindow simGUI = new SimulationWindow(
              new RotatorGridExampleSimulation(), new DefaultControls());
        simGUI.setVisible(true);
    }
}