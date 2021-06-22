package example;

import at.htlkaindorf.dorjab18.simulation.SimulationWindow;
import example.circleGraphExample.CircleGraphExampleControls;
import example.circleGraphExample.CircleGraphExampleSimulation;

/**
 *
 * @author dorjab18
 */
public class CircleGraphExampleMain {
    public static void main(String[] args) {
        CircleGraphExampleSimulation simulation = new CircleGraphExampleSimulation();
        
        SimulationWindow simGUI = new SimulationWindow(
              simulation, new CircleGraphExampleControls(simulation));
        simGUI.setVisible(true);
    }
}