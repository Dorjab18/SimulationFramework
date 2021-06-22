package example.mosueInputExample;

import at.htlkaindorf.dorjab18.simulation.AbstractSimulation;
import at.htlkaindorf.dorjab18.util.GraphDrawer;
import at.htlkaindorf.dorjab18.util.GraphGrid;
import at.htlkaindorf.dorjab18.util.GraphLine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * @author dorjab18 (Code in draw: HS)
 */
public class MouseInputExampleSimulation extends AbstractSimulation
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    private final double MOUSE_WHEEL_ROTATION_FACTOR = 10.;
    
    private double time;
    
    private GraphDrawer graphDrawer;
    private GraphLine graphLine;
    
    private GraphGrid graphGrid;
    
    private Point pointLastDragged = null;
    
    public MouseInputExampleSimulation() {
        setPreferredSize(new Dimension(500, 500));
        
        graphDrawer = new GraphDrawer();
        
        graphLine = graphDrawer.createGaphLine(Color.GREEN, null, true, false);
        graphLine.setConnectLastPointToFirst(true);
        //Triangle shape
        graphLine.addPoint(new GraphLine.Point(0, 2.5));
        graphLine.addPoint(new GraphLine.Point(-2.5, -2.5));
        graphLine.addPoint(new GraphLine.Point(2.5, -2.5));
        
        //Set ppu for "graphLine" only
        graphLine.setPpuX(100);
        graphLine.setPpuY(100);
        
        graphGrid = new GraphGrid(graphLine);
        graphGrid.setSubGridCountX(1);
        graphGrid.setSubGridCountY(1);
        
        //Click-Events (clicked, pressed, release, enter, exit)
        addMouseListener(this);
        
        //Motion-Events (draged [pressed button], moved [no pressed button])
        addMouseMotionListener(this);
        
        //Mouse-Wheel-Events (mouseWheelMoved)
        addMouseWheelListener(this);
    }
    
    @Override
    public void timeElapsed(double deltaTime) {
        this.time += deltaTime;
    }

    @Override
    public void reset() {
        this.time = 0;
        
        graphDrawer.setTransX(0);
        graphDrawer.setTransY(0);
        graphDrawer.setPpuX(100);
        graphDrawer.setPpuY(100);
    }

    @Override
    public void draw(Graphics2D g2d) {
        graphGrid.draw(g2d, getSize());
        
        graphDrawer.draw(g2d);
    }

    @Override
    public String getSimulationName() {
        return "Mouse Input Example Simulation";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Do something, if main mouse button is pressed
        if(e.getButton() != MouseEvent.BUTTON1)
            return;
        
        GraphLine.Point coord = convertCoordsToGraph(e.getPoint());
        
        System.out.println("Clicked: (" + coord.x + ", " + coord.y + ")");
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //Do something, if main mouse button is pressed
        if(e.getButton() != MouseEvent.BUTTON1)
            return;
        
        GraphLine.Point coord = convertCoordsToGraph(e.getPoint());
       
        System.out.println("Pressed: (" + coord.x + ", " + coord.y + ")");
        
        //Drag graph grid
        pointLastDragged = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Do something, if main mouse button is pressed
        if(e.getButton() != MouseEvent.BUTTON1)
            return;
        
        GraphLine.Point coord = convertCoordsToGraph(e.getPoint());
       
        System.out.println("Released: (" + coord.x + ", " + coord.y + ")");
        
        //Drag graph grid
        pointLastDragged = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        GraphLine.Point coord = convertCoordsToGraph(e.getPoint());
       
        System.out.println("Entered: (" + coord.x + ", " + coord.y + ")");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        GraphLine.Point coord = convertCoordsToGraph(e.getPoint());
       
        System.out.println("Exited: (" + coord.x + ", " + coord.y + ")");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GraphLine.Point coord = convertCoordsToGraph(e.getPoint());
       
        System.out.println("Dragged: (" + coord.x + ", " + coord.y + ")");
        
        //Drag graph grid
        if(getSimulationWindow().isSimulationRunning() &&
                pointLastDragged != null) {
            double diffX = e.getPoint().x - pointLastDragged.x;
            double diffY = e.getPoint().y - pointLastDragged.y;
            
            graphLine.setTransX(graphLine.getTransX() + diffX);
            graphLine.setTransY(graphLine.getTransY() + diffY);
            
            pointLastDragged = e.getPoint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GraphLine.Point coord = convertCoordsToGraph(e.getPoint());
        
        System.out.println("Moved: (" + coord.x + ", " + coord.y + ")");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(!getSimulationWindow().isSimulationRunning())
            return;
        
        double rotation = e.getPreciseWheelRotation();
        rotation *= MOUSE_WHEEL_ROTATION_FACTOR;
        
        //Scale gaph grid
        graphLine.setPpuX(graphLine.getPpuX() - rotation);
        graphLine.setPpuY(graphLine.getPpuY() - rotation);
        
        System.out.println("Mouse wheel rotation: (" + e.
                getPreciseWheelRotation() + ")");
    }
    
    //Helper function (Coordination convertion from jframe to graph)
    private GraphLine.Point convertCoordsToGraph(Point coord) {
        GraphLine.Point graphCoord = new GraphLine.Point();
        
        graphCoord.x = (coord.x - graphLine.getTransX()) / graphLine.getPpuX();
        graphCoord.y = -(coord.y - graphLine.getTransY()) / graphLine.getPpuY();
        
        return graphCoord;
    }
}