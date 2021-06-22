package example;

import at.htlkaindorf.dorjab18.simulation.SimulationWindow;
import example.oscillator2DExample.Oscillator2DExampleControls;
import example.oscillator2DExample.Oscillator2DExampleSimulation;

/**
 *
 * @author dorjab18
 */
public class Oscillator2DExampleMain {
    public static void main(String[] args) {
        Oscillator2DExampleSimulation simulation = new Oscillator2DExampleSimulation();
        Oscillator2DExampleControls controls = new Oscillator2DExampleControls(simulation);
        
        simulation.setOscilattor2DControls(controls);
        
        SimulationWindow simGUI = new SimulationWindow(simulation, controls);
        simGUI.setVisible(true);
    }
}