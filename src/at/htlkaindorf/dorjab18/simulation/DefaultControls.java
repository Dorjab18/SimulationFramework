package at.htlkaindorf.dorjab18.simulation;

import java.text.DecimalFormat;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author dorjab18
 */
public class DefaultControls extends AbstractControls {
    
    /**
     * Creates new form DefaultControlls
     */
    public DefaultControls() {
        initComponents();
        
        SpinnerNumberModel model = new SpinnerNumberModel(1., -1000., 1000., .1);
        spSimSpeedFactor.setModel(model);
        
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spSimSpeedFactor.
                getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(3);
        format.setMaximumFractionDigits(10);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spSimSpeedFactor = new javax.swing.JSpinner();
        btStartStop = new javax.swing.JToggleButton();
        btReset = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(10, 1));

        spSimSpeedFactor.setToolTipText("Simulation speed");
        spSimSpeedFactor.setValue(1);
        spSimSpeedFactor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onSimSpeedFactorChanged(evt);
            }
        });
        add(spSimSpeedFactor);

        btStartStop.setText("Start");
        btStartStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onStartStop(evt);
            }
        });
        add(btStartStop);

        btReset.setText("Reset");
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onReset(evt);
            }
        });
        add(btReset);
    }// </editor-fold>//GEN-END:initComponents

    private void onStartStop(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onStartStop
        simlationWindow.invertSimulationRunningState();
        
        if(simlationWindow.isSimulationRunning()) {
            btStartStop.setText("Stop");
        }else {
            btStartStop.setText("Start");
        }
    }//GEN-LAST:event_onStartStop

    private void onSimSpeedFactorChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onSimSpeedFactorChanged
        try {
            simlationWindow.setSimulationSpeedFactor(Double.
                    parseDouble(spSimSpeedFactor.getValue().toString()));
        }catch(NumberFormatException e) {
            //Ignore
        }
    }//GEN-LAST:event_onSimSpeedFactorChanged

    private void onReset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onReset
        simlationWindow.reset();
        btStartStop.setSelected(false);
        btStartStop.setText("Start");
    }//GEN-LAST:event_onReset


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btReset;
    private javax.swing.JToggleButton btStartStop;
    private javax.swing.JSpinner spSimSpeedFactor;
    // End of variables declaration//GEN-END:variables
}