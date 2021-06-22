package example;

import example.wavemachineExample.WavemachineExampleSimulation;
import at.htlkaindorf.dorjab18.simulation.DefaultControls;
import at.htlkaindorf.dorjab18.simulation.SimulationWindow;

/**
 *
 * @author dorjab18, HS
 */
public class WavemachineExampleMain {
    public static void main(String[] args) {
        SimulationWindow simGUI = new SimulationWindow(
              new WavemachineExampleSimulation(), new DefaultControls());
        simGUI.setVisible(true);
    }
}