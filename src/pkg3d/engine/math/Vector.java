package pkg3d.engine.math;

/**
 *
 * @author Alex
 */
public class Vector {
    private double x, y, z;

    public Vector(double x, double y, double z) {
        double length = Math.sqrt(x * x + y * y + z * z);

        if (length > 0) {
            this.x = x / length;
            this.y = y / length;
            this.z = z / length;
        }

    }

    public Vector CrossProduct(Vector v) {
        Vector crossVector = new Vector(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x);
        return crossVector;
    }
    
    public double getX(){return x;}
    public double getY(){return y;}
    public double getZ(){return z;}
}
