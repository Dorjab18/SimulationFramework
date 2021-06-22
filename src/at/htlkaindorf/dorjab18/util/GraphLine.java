package at.htlkaindorf.dorjab18.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author dorjab18
 */
public class GraphLine extends GraphObject {
    private List<Point> points;
    private Color lineColor, pointColor;
    private boolean drawLine, drawPoints, connectLastPointToFirst;
    
    private int pointSize = 6;
    
    private LineStyle lineStyle = LineStyle.NORMAL;
    private PointStyle pointStyle = PointStyle.NORMAL;
    private PointFlag pointFlag = PointFlag.ALL_POINTS;
    
    private double lineDisconnectThreshold = 0;
    private int maxPointCount = 0;

    public GraphLine(Color lineColor, Color pointColor, boolean drawLine,
            boolean drawPoints) {
        points = new ArrayList<>();
        
        this.lineColor = lineColor;
        this.pointColor = pointColor;
        this.drawLine = drawLine;
        this.drawPoints = drawPoints;
    }
    
    public GraphLine(boolean drawLine, boolean drawPoints) {
        this(Color.BLACK, Color.BLUE, drawLine, drawPoints);
    }
    
    public GraphLine() {
        this(true, true);
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
    }

    public void setDrawPoints(boolean drawPoints) {
        this.drawPoints = drawPoints;
    }

    public void setConnectLastPointToFirst(boolean connectLastPointToFirst) {
        this.connectLastPointToFirst = connectLastPointToFirst;
    }

    public void setPointColor(Color pointColor) {
        this.pointColor = pointColor;
    }
    
    public void setPoints(Collection<Point> points) {
        this.points = new ArrayList<>(points);
        
        removeIfTooManyPoints();
    }
    
    public void addPoints(Collection<Point> points) {
        this.points.addAll(points);
        
        removeIfTooManyPoints();
    }
    
    public void addPoint(Point p) {
        points.add(p);
        
        removeIfTooManyPoints();
    }
    
    public void addPoint(int index, Point p) {
        points.add(index, p);
        
        removeIfTooManyPoints();
    }
    
    public void removePoint(Point p) {
        points.remove(p);
    }
    
    public void removePoint(int index) {
        points.remove(index);
    }
    
    private void removeIfTooManyPoints() {
        if(maxPointCount > 0 && points.size() > maxPointCount) {
            int removeCount = points.size() - maxPointCount;
            
            for(int i = 0;i < removeCount;i++)
                points.remove(0);
        }
    }
    
    public void clearPoints() {
        points.clear();
    }
    
    public Point getPoint(int index) {
        return points.get(index);
    }
    
    public int getPointCount() {
        return points.size();
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    public void setPointStyle(PointStyle pointStyle) {
        this.pointStyle = pointStyle;
    }

    public void setPointFlag(PointFlag pointFlag) {
        this.pointFlag = pointFlag;
    }

    /**
     * If the length of a line (graph coordinates) is higher than the threshold,
     * the line will be disconnected. If the threshold is &#60;= 0, the length
     * of a line won't be checked.
     */
    public void setLineDisconnectThreshold(double lineDisconnectThreshold) {
        this.lineDisconnectThreshold = lineDisconnectThreshold;
    }

    public double getLineDisconnectThreshold() {
        return lineDisconnectThreshold;
    }

    public void setMaxPointCount(int maxPointCount) {
        this.maxPointCount = maxPointCount;
    }

    public int getMaxPointCount() {
        return maxPointCount;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        if(points.isEmpty())
            return;

        Point lastPoint = connectLastPointToFirst?points.get(points.size() - 1):
                null;

        if(drawLine) {
            g2d.setColor(lineColor);
            
            for(int i = 0;i < points.size();i++) {
                Point p = points.get(i);
                
                if(lastPoint != null) {
                    //Line disconnect threshold
                    double len = Math.hypot(lastPoint.x - p.x,
                            lastPoint.y - p.y);
                    if(lineDisconnectThreshold <= 0 ||
                            len < lineDisconnectThreshold) {
                        switch(lineStyle) {
                            case VECTOR:
                                drawLineVector(g2d, lastPoint, p);

                                //No break, line should be drawn
                            case NORMAL:
                                g2d.drawLine((int)(lastPoint.x * ppuX + transX),
                                        (int)(-lastPoint.y * ppuY + transY),
                                        (int)(p.x * ppuX + transX),
                                        (int)(-p.y * ppuY + transY));

                                break;
                            case DASHED:
                                drawLineDashed(g2d, lastPoint, p);

                                break;
                            case SPRING:
                                drawLineSpring(g2d, lastPoint, p);

                                break;

                        }
                    }
                }

                lastPoint = p;
            }
        }
        

        if(drawPoints) {
            double halfPointSize = .5 * pointSize;
            g2d.setColor(pointColor);
            
            for(int i = 0;i < points.size();i++) {
                Point p = points.get(i);
                
                if(shouldDrawPoint(i)) {
                    double x = p.x * ppuX + transX;
                    double y = -p.y * ppuY + transY;

                    switch(pointStyle) {
                        case NORMAL:
                            g2d.fillOval((int)(x - halfPointSize),
                                    (int)(y - halfPointSize), pointSize,
                                    pointSize);

                            break;
                        case TRIANGLE:
                            drawPointTriangle(g2d, p);

                            break;
                        case SQUARE:
                            g2d.fillRect((int)(x - halfPointSize),
                                    (int)(y - halfPointSize), pointSize,
                                    pointSize);

                            break;
                        case ASTERISK:
                            drawPointAsterisk(g2d, p);

                            break;
                    }
                }
            }
        }
    }
    
    private void drawLineVector(Graphics2D g2d, Point pStart, Point pEnd) {
        final int LEN = 20;
        final int ANGLE = 35;
        
        int startX = (int)(pStart.x * ppuX + transX);
        int startY = (int)(-pStart.y * ppuY + transY);
        int endX = (int)(pEnd.x * ppuX + transX);
        int endY = (int)(-pEnd.y * ppuY + transY);

        double angleLine = Math.atan2(startX - endX, startY - endY);

        int p1X = (int)(endX + Math.sin(angleLine - Math.toRadians(ANGLE)) *
                LEN);
        int p1Y = (int)(endY + Math.cos(angleLine - Math.toRadians(ANGLE)) *
                LEN);
        int p2X = (int)(endX + Math.sin(angleLine + Math.toRadians(ANGLE)) *
                LEN);
        int p2Y = (int)(endY + Math.cos(angleLine + Math.toRadians(ANGLE)) *
                LEN);

        Polygon triangle = new Polygon();
        triangle.addPoint(p1X, p1Y);
        triangle.addPoint(endX, endY);
        triangle.addPoint(p2X, p2Y);

        g2d.fillPolygon(triangle);
    }
    private void drawLineDashed(Graphics2D g2d, Point pStart, Point pEnd) {
        //ppu independent (-> len in px, NOT in graph coordinates)
        final int DASH_LENGTH = 20;
        
        double lenXFull = (pEnd.x - pStart.x) * ppuX;
        double lenYFull = (pEnd.y - pStart.y) * ppuY;

        double alpha = Math.atan2(lenYFull, lenXFull);
        
        double dashLenX = DASH_LENGTH * Math.cos(alpha);
        double dashLenY = DASH_LENGTH * Math.sin(alpha);

        double posX = pStart.x * ppuX;
        double posY = pStart.y * ppuY;

        int drawCount = (int)Math.max(lenXFull / dashLenX
                / 2, lenYFull / dashLenY / 2);

        for(int j = 0;j < drawCount;j++) {
            g2d.drawLine((int)(posX + transX),
                (int)(-posY + transY),
                (int)(posX + dashLenX + transX),
                (int)(-posY - dashLenY + transY));

            posX += 2 * dashLenX;
            posY += 2 * dashLenY;
        }

        //Draw last dash (Part)
        int drawCountWithPart = (int)Math.ceil(Math.max(lenXFull / dashLenX,
                lenYFull / dashLenY));
        drawCount *= 2; //With parts between dashes

        if(drawCountWithPart - drawCount > 1) {
            //Part contains the dash segment and empty space
            g2d.drawLine((int)(posX + transX),
                (int)(-posY + transY),
                (int)(posX + dashLenX + transX),
                (int)(-posY - dashLenY + transY));
        } else if(drawCountWithPart - drawCount > 0) {
            //Part contains the dash segment only
            g2d.drawLine((int)(posX + transX),
                (int)(-posY + transY),
                (int)(pEnd.x * ppuX + transX),
                (int)(-pEnd.y * ppuY + transY));
        }
    }
    private void drawLineSpring(Graphics2D g2d, Point pStart, Point pEnd) {
        final int SEGMENT_COUNT = 10;
        
        double lenX = pEnd.x - pStart.x;
        double lenY = pEnd.y - pStart.y;

        //Spring start line (5% of length)
        g2d.drawLine((int)(pStart.x * ppuX + transX),
                (int)(-pStart.y * ppuY + transY),
                (int)((pEnd.x - lenX * .95) * ppuX + transX),
                (int)((-pEnd.y + lenY * .95) * ppuY + transY));
        
        //Spring end line (5% of length)
        g2d.drawLine((int)(pEnd.x * ppuX + transX),
                (int)(-pEnd.y * ppuY + transY),
                (int)((pEnd.x - lenX * .05) * ppuX + transX),
                (int)((-pEnd.y + lenY * .05) * ppuY + transY));

        double lenXPerSegment = (lenX * .9) / SEGMENT_COUNT;
        double lenYPerSegment = (lenY * .9) / SEGMENT_COUNT;

        double startXSegment = pEnd.x - lenX * .95;
        double startYSegment = pEnd.y - lenY * .95;

        double angle = Math.atan2(lenX, lenY);

        double dx = lenXPerSegment / 2;
        double dy = lenYPerSegment / 2;

        double dx2 = Math.sin(angle - Math.PI / 2) / 20;
        double dy2 = Math.cos(angle - Math.PI / 2) / 20;

        //Spring segments (90% of length)
        for(int j = 0;j < SEGMENT_COUNT;j++) {
            g2d.drawLine((int)(startXSegment * ppuX + transX),
                    (int)(-startYSegment * ppuY + transY),
                    (int)((startXSegment + dx + dx2) * ppuX + transX),
                    (int)((-startYSegment - dy - dy2) * ppuY + transY));
            g2d.drawLine((int)((startXSegment + dx + dx2) * ppuX + transX),
                    (int)((-startYSegment - dy - dy2) * ppuY + transY),
                    (int)((startXSegment + dx - dx2) * ppuX + transX),
                    (int)((-startYSegment - dy + dy2) * ppuY + dy2 + transY));
            g2d.drawLine((int)((startXSegment + dx - dx2) * ppuX + transX),
                    (int)((-startYSegment - dy + dy2) * ppuY + transY),
                    (int)((startXSegment + lenXPerSegment) * ppuX + transX),
                    (int)((-startYSegment - lenYPerSegment) * ppuY + transY));

            startXSegment += lenXPerSegment;
            startYSegment += lenYPerSegment;
        }
    }
    
    private boolean shouldDrawPoint(int index) {
        switch(pointFlag) {
            case ALL_POINTS:
                return true;
            case FIRST_POINT_ONLY:
                return index == 0;
            case LAST_POINT_ONLY:
                return index == points.size() - 1;
            case NO_FIRST_POINT:
                return index != 0;
            case NO_LAST_POINT:
                return index != points.size() - 1;
        }
                
        return false;
    }
    private void drawPointTriangle(Graphics2D g2d, Point p) {
        double x = p.x * ppuX + transX;
        double y = -p.y * ppuY + transY;
        double halfPointSize = .5 * pointSize;
        
        Polygon triangle = new Polygon();
        triangle.addPoint((int)(x - halfPointSize), (int)(y + halfPointSize));
        triangle.addPoint((int)x, (int)(y - halfPointSize));
        triangle.addPoint((int)(x + halfPointSize), (int)(y + halfPointSize));

        g2d.fillPolygon(triangle);
    }
    private void drawPointAsterisk(Graphics2D g2d, Point p) {
        double x = p.x * ppuX + transX;
        double y = -p.y * ppuY + transY;
        
        g2d.drawLine((int)x, (int)y, (int)x, (int)(y - .5 * pointSize));
        g2d.drawLine((int)x, (int)y, (int)(x + .57 * pointSize),
                (int)(y - .15 * pointSize));
        g2d.drawLine((int)x, (int)y, (int)(x - .57 * pointSize),
                (int)(y - .15 * pointSize));
        g2d.drawLine((int)x, (int)y, (int)(x + .29 * pointSize),
                (int)(y + .4 * pointSize));
        g2d.drawLine((int)x, (int)y, (int)(x - .29 * pointSize),
                (int)(y + .4 * pointSize));
    }
    
    public static class Point {
        public double x;
        public double y;

        public Point() {}
        
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public Point(Point p) {
            this.x = p.x;
            this.y = p.y;
        }
        
        public Point(java.awt.Point p) {
            this.x = p.x;
            this.y = p.y;
        }
        
        public void setX(double x) {
            this.x = x;
        }
        
        public double getX() {
            return x;
        }
        
        public void setPoint(Point p) {
            this.x = p.x;
            this.y = p.y;
        }
        
        public void setPoint(java.awt.Point p) {
            this.x = p.x;
            this.y = p.y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getY() {
            return y;
        }
        
        public java.awt.Point getAsAWTPoint() {
            return new java.awt.Point((int)x, (int)y);
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 97 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
            hash = 97 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Point other = (Point) obj;
            if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
                return false;
            }
            if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
                return false;
            }
            return true;
        }
    }
    
    public static enum LineStyle {
        NORMAL, DASHED, VECTOR, SPRING;
    }
    
    public static enum PointStyle {
        NORMAL, TRIANGLE, SQUARE, ASTERISK;
    }
    
    public static enum PointFlag {
        ALL_POINTS, FIRST_POINT_ONLY, LAST_POINT_ONLY, NO_FIRST_POINT,
        NO_LAST_POINT
    }
}