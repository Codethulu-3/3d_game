package pkg3d.engine.math;

import pkg3d.engine.gfx.Camera;
import pkg3d.engine.objects.Plane;

/**
 *
 *
 */
public class Calculator {

    private double t = 0;
    private Vector W1, W2, ViewVector, RotationVector, DirectionVector, PlaneVector1, PlaneVector2;
    private Plane p;
    private double[] calcFocusPos = new double[2];

    public double[] calculatePositionP(double[] viewFrom, double[] viewTo, double x, double y, double z) {
        double[] projP = getProj(viewFrom, viewTo, x, y, z, p);
        double[] drawP = getDrawP(projP[0], projP[1], projP[2]);
        return drawP;
    }

    public double[] getProj(double[] ViewFrom, double[] ViewTo, double x, double y, double z, Plane p) {
        Vector ViewToPoint = new Vector(x - ViewFrom[0], y - ViewFrom[1], z - ViewFrom[2]);
        
        t = (p.getNV().getX() * p.getP()[0] + p.getNV().getY() * p.getP()[1] + p.getNV().getZ() * p.getP()[2]
                - (p.getNV().getX() * ViewFrom[0] + p.getNV().getY() * ViewFrom[1] + p.getNV().getZ() * ViewFrom[2]))
                / (p.getNV().getX() * ViewToPoint.getX() + p.getNV().getY() * ViewToPoint.getY() + p.getNV().getZ() * ViewToPoint.getZ());

        x = ViewFrom[0] + ViewToPoint.getX() * t;
        y = ViewFrom[1] + ViewToPoint.getY() * t;
        z = ViewFrom[2] + ViewToPoint.getZ() * t;

        return new double[]{x, y, z};
    }

    public double[] getDrawP(double x, double y, double z) {
        double DrawX = W2.getX() * x + W2.getY() * y + W2.getZ() * z;
        double DrawY = W1.getX() * x + W1.getY() * y + W1.getZ() * z;
        return new double[]{DrawX, DrawY};
    }

    public Vector getRotationVector(double[] viewFrom, double[] viewTo) {
        double dx = Math.abs(viewFrom[0] - viewTo[0]);
        double dy = Math.abs(viewFrom[1] - viewTo[1]);
        double xRot, yRot;
        xRot = dy / (dx + dy);
        yRot = dx / (dx + dy);

        if (viewFrom[1] > viewTo[1]) {
            xRot = -xRot;
        }
        if (viewFrom[0] < viewTo[0]) {
            yRot = -yRot;
        }

        Vector V = new Vector(xRot, yRot, 0);
        return V;
    }

    public void update(double[] viewTo, double[] viewFrom, double zoom) {
        ViewVector = new Vector(viewTo[0] - viewFrom[0], 
                viewTo[1] - viewFrom[1], 
                viewTo[2] - viewFrom[2]);
        DirectionVector = new Vector(1, 1, 1);
        PlaneVector1 = ViewVector.CrossProduct(DirectionVector);
        PlaneVector2 = ViewVector.CrossProduct(PlaneVector1);
        p = new Plane(PlaneVector1, PlaneVector2, viewTo);

        RotationVector = getRotationVector(viewFrom, viewTo);
        W1 = ViewVector.CrossProduct(RotationVector);
        W2 = ViewVector.CrossProduct(W1);

        calcFocusPos = calculatePositionP(viewFrom,viewTo, 
                viewTo[0], viewTo[1], viewTo[2]);
        calcFocusPos[0] = zoom * calcFocusPos[0];
        calcFocusPos[1] = zoom * calcFocusPos[1];
    }
    
    //getters
    public double[] getCalcFocusPos(){return calcFocusPos;}
    public double getT(){return t;}
}
