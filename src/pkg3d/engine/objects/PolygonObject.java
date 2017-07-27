package pkg3d.engine.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class PolygonObject {

    private Polygon p;
    private Color c;
    private boolean draw = true, visible = true, seeThrough;
    private double lighting = 1;

    public PolygonObject(double[] x, double[] y, Color c, int n, boolean seeThrough) {
        p = new Polygon();
        for (int i = 0; i < x.length; i++) {
            p.addPoint((int) x[i], (int) y[i]);
        }
        this.c = c;
        this.seeThrough = seeThrough;
    }

    public void updatePolygon(double[] x, double[] y) {
        p.reset();
        for (int i = 0; i < x.length; i++) {
            p.xpoints[i] = (int) x[i];
            p.ypoints[i] = (int) y[i];
            p.npoints = x.length;
        }
    }

    public void drawPolygon(Graphics g) {
        if (draw && visible) {
            g.setColor(new Color((int) (c.getRed() * lighting), (int) (c.getGreen() * lighting), (int) (c.getBlue() * lighting)));
            if (seeThrough) {
                g.drawPolygon(p);
            } else {
                g.fillPolygon(p);
            }
            
            g.setColor(new Color(0, 0, 0));
            g.drawPolygon(p);
        }
    }

    public boolean MouseOver(int width, int height) {
        return p.contains(width / 2, height / 2);
    }
    
    public double getLighting(){return lighting;}
    public void setLighting(double lighting){this.lighting = lighting;}
    
    public boolean getDraw(){return draw;}
    public void setDraw(boolean draw){this.draw = draw;}
}
