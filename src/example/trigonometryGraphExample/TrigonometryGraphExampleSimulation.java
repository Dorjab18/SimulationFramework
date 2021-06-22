package example.trigonometryGraphExample;

import at.htlkaindorf.dorjab18.simulation.AbstractSimulation;
import at.htlkaindorf.dorjab18.util.GraphDrawer;
import at.htlkaindorf.dorjab18.util.GraphGrid;
import at.htlkaindorf.dorjab18.util.GraphLine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 * @author dorjab18
 */
public class TrigonometryGraphExampleSimulation extends AbstractSimulation {
    private double time;
    
    private TrigonometryFunctions trigFuncs;
    
    private GraphDrawer graphDrawer;
    private GraphLine sinGraphLine;
    private GraphLine cosGraphLine;
    private GraphLine tanGraphLine;
    
    private GraphGrid graphGrid;
    
    private boolean alreadyScrolled;
    
    public TrigonometryGraphExampleSimulation() {
        setPreferredSize(new Dimension(500, 300));
        
        trigFuncs = new TrigonometryFunctions(1);
        
        graphDrawer = new GraphDrawer();
        
        sinGraphLine = graphDrawer.createGaphLine();
        
        cosGraphLine = graphDrawer.createGaphLine();
        cosGraphLine.setPointColor(Color.RED);
        cosGraphLine.setLineColor(Color.GRAY);
        
        tanGraphLine = graphDrawer.createGaphLine();
        tanGraphLine.setPointColor(Color.GREEN);
        tanGraphLine.setLineColor(Color.ORANGE);
        tanGraphLine.setLineDisconnectThreshold(25);
        
        //Set ppu and transY for all graph lines
        graphDrawer.setPpuY(100);
        graphDrawer.setPpuX(1000);
        graphDrawer.setTransY(getBounds().height * .5);
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                graphDrawer.setTransY(getBounds().height * .5);
            }
        });
        
        graphGrid = new GraphGrid(sinGraphLine);
        graphGrid.setGridSpacingX(.1, "s");
        graphGrid.setDecimalPlacesX(1);
    }
    
    @Override
    public void timeElapsed(double deltaTime) {
        this.time += deltaTime;
        
        trigFuncs.calculate(deltaTime);
        sinGraphLine.addPoint(new GraphLine.Point(trigFuncs.getX(),
                trigFuncs.getSinY()));
        cosGraphLine.addPoint(new GraphLine.Point(trigFuncs.getX(),
                trigFuncs.getCosY()));
        tanGraphLine.addPoint(new GraphLine.Point(trigFuncs.getX(),
                trigFuncs.getTanY()));
        
        if(alreadyScrolled || this.time > getSize().width || this.time < 0) {
            if(deltaTime > 0) {
                graphDrawer.setTransX(-this.time + getBounds().width);
            }else if(deltaTime < 0) {
                graphDrawer.setTransX(-this.time);
            }
            
            alreadyScrolled = true;
        }
    }

    @Override
    public void reset() {
        this.time = 0;
        
        trigFuncs.reset();
        
        graphDrawer.clearPoints();
        graphDrawer.setTransX(0);
        
        alreadyScrolled = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        graphGrid.draw(g2d, getSize());
        graphDrawer.draw(g2d);
    }

    @Override
    public String getSimulationName() {
        return "Trigonomertry Graph Example Simulation";
    }
    
    public void setAmplitude(double amplitude) {
        trigFuncs.setAmplitude(amplitude);
    }
    
    public void setDrawLine(boolean drawLine) {
        graphDrawer.setDrawLine(drawLine);
        /* You can also set draw line on each line seperatly
            sinGraphLine.setDrawLine(drawLine);
            cosGraphLine.setDrawLine(drawLine);
            tanGraphLine.setDrawLine(drawLine);
        */
        
        simlationWindow.forceRedraw();
    }
    
    public void setDrawPoints(boolean drawPoints) {
        graphDrawer.setDrawPoints(drawPoints);
        
        simlationWindow.forceRedraw();
    }
                   
}