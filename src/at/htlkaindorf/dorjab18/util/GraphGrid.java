package at.htlkaindorf.dorjab18.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author dorjab18
 */
public class GraphGrid {
    private GraphObject graphObject;
    private Color gridColor, axisColor, axisTicksColor;
    private boolean drawGrid, drawAxis, drawAxisTicks;
    
    private int subGridCountX, subGridCountY;
    
    private PhysicsUnit gridSpacingX, gridSpacingY;
    private int decimalPlacesX, decimalPlacesY;
    
    private AxisPosition axisPositionX, axisPositionY;

    public GraphGrid(GraphObject graphObject, Color gridColor, Color axisColor,
            Color axisTicksColor, boolean drawGrid, boolean drawAxis,
            boolean drawAxisTicks) {
        this.graphObject = graphObject;
        this.gridColor = gridColor;
        this.axisColor = axisColor;
        this.axisTicksColor = axisTicksColor;
        this.drawGrid = drawGrid;
        this.drawAxis = drawAxis;
        this.drawAxisTicks = drawAxisTicks;
        
        this.gridSpacingX = new PhysicsUnit();
        this.gridSpacingY = new PhysicsUnit();
        
        this.axisPositionX = AxisPosition.AUTOMATIC;
        this.axisPositionY = AxisPosition.AUTOMATIC;
    }

    public GraphGrid(GraphObject graphObject, boolean drawGrid, boolean drawAxis,
            boolean drawAxisTicks) {
        this(graphObject, Color.LIGHT_GRAY, Color.GRAY, Color.GRAY,
                drawGrid, drawAxis, drawAxisTicks);
    }
    
    public GraphGrid(GraphObject graphObject) {
        this(graphObject, true, true, true);
    }
    
    public GraphGrid() {
        this(null, true, true, true);
    }

    public void setGraphObject(GraphObject graphObejct) {
        this.graphObject = graphObejct;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public void setAxisColor(Color axisColor) {
        this.axisColor = axisColor;
    }
        
    public void setAxisTicksColor(Color axisTicksColor) {
        this.axisTicksColor = axisTicksColor;
    }

    public void setDrawGrid(boolean drawGrid) {
        this.drawGrid = drawGrid;
    }

    public void setDrawAxis(boolean drawAxis) {
        this.drawAxis = drawAxis;
    }

    public void setDrawAxisTicks(boolean drawAxisTicks) {
        this.drawAxisTicks = drawAxisTicks;
    }

    public void setSubGridCountX(int subGridCountX) {
        this.subGridCountX = subGridCountX;
    }

    public void setSubGridCountY(int subGridCountY) {
        this.subGridCountY = subGridCountY;
    }
    
    public void setGridSpacingX(PhysicsUnit gridSpacingX) {
        this.gridSpacingX.set(gridSpacingX);
    }
    
    public void setGridSpacingX(double num, String unit) {
        this.gridSpacingX.setNumber(num);
        this.gridSpacingX.setUnit(unit);
    }
    
    public void setGridSpacingXNumber(double num) {
        this.gridSpacingX.setNumber(num);
    }
    
    public void setGridSpacingXUnit(String unit) {
        this.gridSpacingX.setUnit(unit);
    }
    
    public void setGridSpacingY(PhysicsUnit gridSpacingY) {
        this.gridSpacingY.set(gridSpacingY);
    }
    
    public void setGridSpacingY(double num, String unit) {
        this.gridSpacingY.setNumber(num);
        this.gridSpacingY.setUnit(unit);
    }
    
    public void setGridSpacingYNumber(double num) {
        this.gridSpacingY.setNumber(num);
    }
    
    public void setGridSpacingYUnit(String unit) {
        this.gridSpacingY.setUnit(unit);
    }

    public void setDecimalPlacesX(int decimalPlacesX) {
        this.decimalPlacesX = decimalPlacesX;
    }

    public void setDecimalPlacesY(int decimalPlacesY) {
        this.decimalPlacesY = decimalPlacesY;
    }

    public void setAxisPositionX(AxisPosition axisPositionX) {
        this.axisPositionX = axisPositionX;
    }

    public void setAxisPositionY(AxisPosition axisPositionY) {
        this.axisPositionY = axisPositionY;
    }
    
    public void draw(Graphics2D g2d, Dimension size) {
        if(graphObject == null)
            return;
        
        double transX = graphObject.getTransX();
        double transY = graphObject.getTransY();
        double ppuX = Math.abs(graphObject.getPpuX() * gridSpacingX.getNumber());
        double ppuY = Math.abs(graphObject.getPpuY() * gridSpacingY.getNumber());
        
        int gridCountH = (int)Math.ceil(size.height / ppuY) + 1;
        int gridCountW = (int)Math.ceil(size.width / ppuX) + 1;
            
        int xAxisTransY = 0;
        int yAxisTransX = 0;
        
        switch(axisPositionX) {
            case AUTOMATIC:
                xAxisTransY = (int)Math.max(0, Math.min(size.height - 1, transY));
                break;
            case LEFT_TOP:
                xAxisTransY = 0;
                break;
            case RIGHT_DOWN:
                xAxisTransY = (int)size.height - 1;
                break;
            case NO_SNAPPING:
                xAxisTransY = (int)transY;
                break;
        }
        
        switch(axisPositionY) {
            case AUTOMATIC:
                yAxisTransX = (int)Math.max(0, Math.min(size.width - 1, transX));
                break;
            case LEFT_TOP:
                yAxisTransX = 0;
                break;
            case RIGHT_DOWN:
                yAxisTransX = (int)size.width - 1;
                break;
            case NO_SNAPPING:
                yAxisTransX = (int)transX;
                break;
        }
        
        int numXShift = yAxisTransX >= (int)size.width - 10?-10:10;
        int numYShift = xAxisTransY >= (int)size.height - 10?-2:22;
        
        g2d.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        
        if(drawGrid) {
            g2d.setColor(gridColor);
            for(int i = -1;i < gridCountH;i++) {
                int posY = (int)(i * ppuY + transY % ppuY);
                g2d.drawLine(0, posY, (int)size.width, posY);
                
                for(int j = 1;j <= subGridCountY;j++) {
                    posY += (int)(ppuY / (subGridCountX + 1.));
                    g2d.drawLine(0, posY, (int)size.width, posY);
                }
            }
            for(int i = -1;i < gridCountW;i++) {
                int posX = (int)(i * ppuX + transX % ppuX);
                g2d.drawLine(posX, (int)size.height, posX, 0);
                
                for(int j = 1;j <= subGridCountX;j++) {
                    posX += (int)(ppuX / (subGridCountX + 1.));
                    g2d.drawLine(posX, (int)size.height, posX, 0);
                }
            }
        }
        
        if(drawAxis) {
            g2d.setColor(axisColor);
            
            g2d.drawLine(0, xAxisTransY, (int)size.width, xAxisTransY);
            g2d.drawLine(yAxisTransX, (int)size.height, yAxisTransX, 0);
        }
        
        if(drawAxisTicks) {
            g2d.setColor(axisTicksColor);
            
            double yTickStartNumber = transY < 0?Math.ceil(transY / ppuY):
                    Math.floor(transY / ppuY);
            if(graphObject.getPpuY() < 0)
                yTickStartNumber = -yTickStartNumber;
            
            for(int i = -1;i < gridCountH;i++) {
                double posY = i * ppuY + transY % ppuY;
                
                String numTxt = String.format("%." + decimalPlacesY + "f",
                        (yTickStartNumber + (graphObject.getPpuY() < 0?i:-i)) *
                                gridSpacingY.getNumber()) + (gridSpacingY.
                                        isUnitLess()?"":(" " + gridSpacingY.
                                                getUnit()));
                int numXSizeShift = numXShift;
                if(numXSizeShift <= 0)
                    numXSizeShift -= g2d.getFontMetrics().stringWidth(numTxt);
                
                g2d.drawString(numTxt, yAxisTransX + numXSizeShift,
                        (int)posY + numYShift);
            }
            
            double xTickStartNumber = transX < 0?Math.ceil(transX / ppuX):
                    Math.floor(transX / ppuX);
            if(graphObject.getPpuX() < 0)
                xTickStartNumber = -xTickStartNumber;
            
            for(int i = -1;i < gridCountW;i++) {
                double posX = i * ppuX + transX % ppuX;
                
                String numTxt = String.format("%." + decimalPlacesX + "f",
                        (-xTickStartNumber + (graphObject.getPpuX() < 0?-i:i)) *
                                gridSpacingX.getNumber()) + (gridSpacingX.
                                        isUnitLess()?"":(" " + gridSpacingX.
                                                getUnit()));
                int numXSizeShift = numXShift;
                if(numXSizeShift <= 0)
                    numXSizeShift -= g2d.getFontMetrics().stringWidth(numTxt);
                
                g2d.drawString(numTxt, (int)posX + numXSizeShift,
                        xAxisTransY + numYShift);
            }
        }
    }
    
    public static enum AxisPosition {
        LEFT_TOP, RIGHT_DOWN, AUTOMATIC, NO_SNAPPING;
    }
}