package pkg3d.engine.objects;

import pkg3d.engine.math.Vector;

public class Plane {

    private Vector v1, v2, nV;
    private double[] p = new double[3];

    public Plane(DPolygon DP) {
        p[0] = DP.getX()[0];
        p[1] = DP.getY()[0];
        p[2] = DP.getZ()[0];

        v1 = new Vector(DP.getX()[1] - DP.getX()[0],
                DP.getY()[1] - DP.getY()[0],
                DP.getZ()[1] - DP.getZ()[0]);

        v2 = new Vector(DP.getX()[2] - DP.getX()[0],
                DP.getY()[2] - DP.getY()[0],
                DP.getZ()[2] - DP.getZ()[0]);

        nV = v1.CrossProduct(v2);
    }

    public Plane(Vector VE1, Vector VE2, double[] Z) {
        p = Z;
        v1 = VE1;
        v2 = VE2;
        nV = v1.CrossProduct(v2);
    }
    
    public Vector getNV(){return nV;}
    public double[] getP(){return p;}
}
