package example;

import at.htlkaindorf.dorjab18.simulation.SimulationWindow;
import example.trigonometryGraphExample.TrigonometryGraphExampleControls;
import example.trigonometryGraphExample.TrigonometryGraphExampleSimulation;

/**
 *
 * @author dorjab18
 */
public class TrigonometryGraphExampleMain {
    public static void main(String[] args) {
        TrigonometryGraphExampleSimulation simulation = new TrigonometryGraphExampleSimulation();
        
        SimulationWindow simGUI = new SimulationWindow(
              simulation, new TrigonometryGraphExampleControls(simulation));
        simGUI.setVisible(true);
    }
}