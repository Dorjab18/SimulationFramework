package example.trigonometryGraphExample;

import at.htlkaindorf.dorjab18.simulation.*;
import java.text.DecimalFormat;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author dorjab18
 */
public class TrigonometryGraphExampleControls extends AbstractControls {
    private TrigonometryGraphExampleSimulation simulation;
    
    /**
     * Creates new form DefaultControlls
     */
    public TrigonometryGraphExampleControls(TrigonometryGraphExampleSimulation simulation) {
        initComponents();
        
        this.simulation = simulation;
        
        SpinnerNumberModel model = new SpinnerNumberModel(1., -1000., 1000., .1);
        spSimSpeedFactor.setModel(model);
        
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spSimSpeedFactor.
                getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(3);
        format.setMaximumFractionDigits(10);
        
        model = new SpinnerNumberModel(1., -1000., 1000., .1);
        spAmplitude.setModel(model);
        
        editor = (JSpinner.NumberEditor)spAmplitude.getEditor();
        format = editor.getFormat();
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

        lbGraphControls = new javax.swing.JLabel();
        cbDrawLine = new javax.swing.JCheckBox();
        cbDrawPoints = new javax.swing.JCheckBox();
        spAmplitude = new javax.swing.JSpinner();
        lbTimeControls = new javax.swing.JLabel();
        spSimSpeedFactor = new javax.swing.JSpinner();
        btStartStop = new javax.swing.JToggleButton();
        btReset = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(10, 1));

        lbGraphControls.setText("Graph Controls:");
        lbGraphControls.setToolTipText("");
        add(lbGraphControls);

        cbDrawLine.setSelected(true);
        cbDrawLine.setText("Line");
        cbDrawLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDrawLinePressed(evt);
            }
        });
        add(cbDrawLine);

        cbDrawPoints.setSelected(true);
        cbDrawPoints.setText("Points");
        cbDrawPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDrawPointsPressed(evt);
            }
        });
        add(cbDrawPoints);

        spAmplitude.setToolTipText("Amplitude");
        spAmplitude.setValue(1);
        spAmplitude.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onAmplitudeChanged(evt);
            }
        });
        add(spAmplitude);

        lbTimeControls.setText("Time Controls:");
        lbTimeControls.setToolTipText("");
        add(lbTimeControls);

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

    private void onAmplitudeChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onAmplitudeChanged
        try {
            simulation.setAmplitude(Double.parseDouble(spAmplitude.getValue().
                    toString()));
        }catch(NumberFormatException e) {
            //Ignore
        }
    }//GEN-LAST:event_onAmplitudeChanged

    private void onDrawLinePressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDrawLinePressed
        simulation.setDrawLine(cbDrawLine.isSelected());
    }//GEN-LAST:event_onDrawLinePressed

    private void onDrawPointsPressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDrawPointsPressed
        simulation.setDrawPoints(cbDrawPoints.isSelected());
    }//GEN-LAST:event_onDrawPointsPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btReset;
    private javax.swing.JToggleButton btStartStop;
    private javax.swing.JCheckBox cbDrawLine;
    private javax.swing.JCheckBox cbDrawPoints;
    private javax.swing.JLabel lbGraphControls;
    private javax.swing.JLabel lbTimeControls;
    private javax.swing.JSpinner spAmplitude;
    private javax.swing.JSpinner spSimSpeedFactor;
    // End of variables declaration//GEN-END:variables
}