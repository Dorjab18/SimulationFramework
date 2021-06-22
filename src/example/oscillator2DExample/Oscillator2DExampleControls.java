package example.oscillator2DExample;

import at.htlkaindorf.dorjab18.simulation.AbstractControls;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author dorjab18
 */
public class Oscillator2DExampleControls extends AbstractControls {
    private Oscillator2DExampleSimulation simulation;
    
    /**
     * Creates new form DefaultControlls
     */
    public Oscillator2DExampleControls(Oscillator2DExampleSimulation simulation) {
        initComponents();
        
        setPreferredSize(new Dimension(300, 300));
        
        this.simulation = simulation;
        
        setSpinnerModel(spSimSpeedFactor, 1., -1000., 1000., .1, 3, 10);
        setSpinnerModel(spMass, 1., .0000000001, 1000., .1, 3, 10);
        setSpinnerModel(spSpringConstant, 36., .0000000001, 1000., .1, 3, 10);
        setSpinnerModel(spX0, .8, -1000., 1000., .1, 3, 5);
        setSpinnerModel(spY0, 0, -1000., 1000., .1, 3, 5);
        setSpinnerModel(spV0, .5, -1000., 1000., .1, 3, 5);
        setSpinnerModel(spAlpha, 120., 0., 360., 1, 1, 3);
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
        jPanel1 = new javax.swing.JPanel();
        spMass = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        spSpringConstant = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        spX0 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        spY0 = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        spV0 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        spAlpha = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbTimeControls = new javax.swing.JLabel();
        spSimSpeedFactor = new javax.swing.JSpinner();
        btStartStop = new javax.swing.JToggleButton();
        btReset = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(15, 1));

        lbGraphControls.setText("Graph Controls:");
        lbGraphControls.setToolTipText("");
        add(lbGraphControls);

        jPanel1.setLayout(new java.awt.BorderLayout());

        spMass.setToolTipText("m [kg]");
        spMass.setValue(1);
        spMass.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onMassChanged(evt);
            }
        });
        jPanel1.add(spMass, java.awt.BorderLayout.CENTER);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("m = ");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jLabel7.setText("kg");
        jPanel1.add(jLabel7, java.awt.BorderLayout.LINE_END);

        add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        spSpringConstant.setToolTipText("k [N/m]");
        spSpringConstant.setValue(1);
        spSpringConstant.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onSpringConstantChanged(evt);
            }
        });
        jPanel2.add(spSpringConstant, java.awt.BorderLayout.CENTER);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("k = ");
        jLabel2.setToolTipText("");
        jPanel2.add(jLabel2, java.awt.BorderLayout.LINE_START);

        jLabel8.setText("N/m");
        jPanel2.add(jLabel8, java.awt.BorderLayout.LINE_END);

        add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        spX0.setToolTipText("x₀ [m]");
        spX0.setValue(1);
        spX0.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onX0Changed(evt);
            }
        });
        jPanel3.add(spX0, java.awt.BorderLayout.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("x₀ = ");
        jPanel3.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jLabel9.setText("m");
        jPanel3.add(jLabel9, java.awt.BorderLayout.LINE_END);

        add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        spY0.setToolTipText("y₀ [m]");
        spY0.setValue(1);
        spY0.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onY0Changed(evt);
            }
        });
        jPanel4.add(spY0, java.awt.BorderLayout.CENTER);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("y₀ = ");
        jPanel4.add(jLabel4, java.awt.BorderLayout.LINE_START);

        jLabel10.setText("m");
        jPanel4.add(jLabel10, java.awt.BorderLayout.LINE_END);

        add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        spV0.setToolTipText("|v₀| [m/s]");
        spV0.setValue(1);
        spV0.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onV0Changed(evt);
            }
        });
        jPanel5.add(spV0, java.awt.BorderLayout.CENTER);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("|v₀| = ");
        jPanel5.add(jLabel5, java.awt.BorderLayout.LINE_START);

        jLabel11.setText("m/s");
        jPanel5.add(jLabel11, java.awt.BorderLayout.LINE_END);

        add(jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        spAlpha.setToolTipText("α [°]");
        spAlpha.setValue(1);
        spAlpha.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                onAlphaChanged(evt);
            }
        });
        jPanel6.add(spAlpha, java.awt.BorderLayout.CENTER);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("α =");
        jPanel6.add(jLabel6, java.awt.BorderLayout.LINE_START);

        jLabel12.setText("°");
        jPanel6.add(jLabel12, java.awt.BorderLayout.LINE_END);

        add(jPanel6);

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

    private void onMassChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onMassChanged
        double m = getMass();
        if(m != Double.NaN)
            simulation.setMass(m);
    }//GEN-LAST:event_onMassChanged

    private void onSpringConstantChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onSpringConstantChanged
        double k = getSpringConstant();
        if(k != Double.NaN)
            simulation.setSpringConstant(k);
    }//GEN-LAST:event_onSpringConstantChanged

    private void onX0Changed(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onX0Changed
        double x0 = getX0();
        if(x0 != Double.NaN)
            simulation.setX0(x0);
    }//GEN-LAST:event_onX0Changed

    private void onY0Changed(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onY0Changed
        double y0 = getY0();
        if(y0 != Double.NaN)
            simulation.setY0(y0);
    }//GEN-LAST:event_onY0Changed

    private void onV0Changed(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onV0Changed
        double v0 = getV0();
        if(v0 != Double.NaN)
            simulation.setV0(v0);
    }//GEN-LAST:event_onV0Changed

    private void onAlphaChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_onAlphaChanged
        double alpha = getAlpha();
        if(alpha != Double.NaN)
            simulation.setAlpha(alpha);
    }//GEN-LAST:event_onAlphaChanged

    private void setSpinnerModel(JSpinner spinner, double val, double min,
            double max, double step, int minDec, int maxDec) {
        
        SpinnerNumberModel model = new SpinnerNumberModel(val, min, max, step);
        spinner.setModel(model);
        
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spinner.
                getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(minDec);
        format.setMaximumFractionDigits(maxDec);
    }
    
    public double getMass() {
        try {
            return Double.parseDouble(spMass.getValue().toString());
        }catch(NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public double getSpringConstant() {
        try {
            return Double.parseDouble(spSpringConstant.getValue().toString());
        }catch(NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public double getX0() {
        try {
            return Double.parseDouble(spX0.getValue().toString());
        }catch(NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public double getY0() {
        try {
            return Double.parseDouble(spY0.getValue().toString());
        }catch(NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public double getV0() {
        try {
            return Double.parseDouble(spV0.getValue().toString());
        }catch(NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public double getAlpha() {
        try {
            return Double.parseDouble(spAlpha.getValue().toString());
        }catch(NumberFormatException e) {
            return Double.NaN;
        }
    }
    
    public void setX0(double x0) {
        spX0.setValue(x0);
    }
    
    public void setY0(double y0) {
        spY0.setValue(y0);
    }
    
    public void setV0(double v0) {
        spV0.setValue(v0);
    }
    
    public void setAlpha(double alpha) {
        spAlpha.setValue(alpha);
    }
    
    public void setStop() {
        btStartStop.setSelected(false);
        btStartStop.setText("Start");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btReset;
    private javax.swing.JToggleButton btStartStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbGraphControls;
    private javax.swing.JLabel lbTimeControls;
    private javax.swing.JSpinner spAlpha;
    private javax.swing.JSpinner spMass;
    private javax.swing.JSpinner spSimSpeedFactor;
    private javax.swing.JSpinner spSpringConstant;
    private javax.swing.JSpinner spV0;
    private javax.swing.JSpinner spX0;
    private javax.swing.JSpinner spY0;
    // End of variables declaration//GEN-END:variables
}