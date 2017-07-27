package pkg3d.engine.objects;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import pkg3d.engine.gfx.Lighting;
import pkg3d.engine.math.Calculator;

public class DPolygon {

    private Color c;
    private double[] x, y, z;
    private boolean draw = true, seeThrough = false;
    private double[] calcPos, newX, newY;
    private PolygonObject drawablePolygon;
    private double avgDist;
    
    /**
     * Points are assigned counter clockwise from the original point
     * @param x all x coordinates of the points
     * @param y all y coordinates of the points
     * @param z all z coordinates of the points
     * @param c the color of the polygon
     * @param seeThrough if the polygon is invisible
     */
    public DPolygon(double[] x, double[] y, double[] z, Color c, boolean seeThrough, int i) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.c = c;
        this.seeThrough = seeThrough;
        createPolygon(i);
    }

    private void createPolygon(int i) {
        drawablePolygon = new PolygonObject(new double[x.length], new double[x.length], c, i, seeThrough);
    }

    public void updatePolygon(Calculator calculator, double[] viewFrom, double[] viewTo, double zoom, int width, int height, Lighting lighting) {
        newX = new double[x.length];
        newY = new double[x.length];
        draw = true;
        for (int i = 0; i < x.length; i++) {
            calcPos = calculator.calculatePositionP(viewFrom, viewTo, x[i], y[i], z[i]);
            newX[i] = (width / 2 - calculator.getCalcFocusPos()[0]) + calcPos[0] * zoom;
            newY[i] = (height / 2 - calculator.getCalcFocusPos()[1]) + calcPos[1] * zoom;
            if (calculator.getT() < 0) {
                draw = false;
            }
        }

        calcLighting(lighting);

        drawablePolygon.setDraw(draw);
        drawablePolygon.updatePolygon(newX, newY);
        avgDist = GetDist(viewFrom, viewTo);
    }

    private void calcLighting(Lighting lighting) {
        Plane lightingPlane = new Plane(this);
        double angle = Math.acos(((lightingPlane.getNV().getX() * lighting.getLightDir()[0])
                + (lightingPlane.getNV().getY() * lighting.getLightDir()[1]) + (lightingPlane.getNV().getZ() 
                * lighting.getLightDir()[2]))
                / (Math.sqrt(lighting.getLightDir()[0] * lighting.getLightDir()[0] 
                + lighting.getLightDir()[1] * lighting.getLightDir()[1] + lighting.getLightDir()[2] 
                * lighting.getLightDir()[2])));

        drawablePolygon.setLighting(0.1 + 1 - Math.sqrt(Math.toDegrees(angle) / 180));

        if (drawablePolygon.getLighting() > 1) {
            drawablePolygon.setLighting(1);
        }
        if (drawablePolygon.getLighting() < 0) {
            drawablePolygon.setLighting(0);
        }
    }

    private double GetDist(double[] viewFrom, double[] viewTo) {
        double total = 0;
        for (int i = 0; i < x.length; i++) {
            total += GetDistanceToP(i,viewFrom, viewTo);
        }
        return total / x.length;
    }

    public double GetDistanceToP(int i, double[] viewFrom, double[] viewTo) {
        return Math.sqrt((viewFrom[0] - x[i]) * (viewTo[0] - x[i])
                + (viewFrom[1] - y[i]) * (viewTo[1] - y[i])
                + (viewFrom[2] - z[i]) * (viewTo[2] - z[i]));
    }
    
    public boolean containsPoint(double xPoint, double yPoint){
        Polygon p = new Polygon();
        for(int i = 0; i < x.length; i++){
            p.addPoint((int)x[i], (int)y[i]);
        }
        Point l = new Point((int)xPoint,(int)yPoint);
        if(p.contains(l)){
            return true;
        }
        return false;
    }
    
    public double getZAtPoint(double x, double y){
        return z[0];
    }
    
    //getters & setters
    public double[] getX(){return x;}
    public double[] getY(){return y;}
    public double[] getZ(){return z;}
    
    public void setX(double[] x){this.x = x;}
    public void setY(double[] y){this.y = y;}
    public void setZ(double[] z){this.z = z;}
    
    public double getAvgDist(){return avgDist;}
    
    public PolygonObject getDrawablePolygon(){return drawablePolygon;}

}
