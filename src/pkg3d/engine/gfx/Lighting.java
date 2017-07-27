package pkg3d.engine.gfx;

/**
 *
 * @author Alex
 */
public class Lighting {
    private double[] lightDir = new double[]{1, 1, 1};
    private double sunPos = 0;
    
    private void calculateSunPos(double mapSize) {
        sunPos += 0.005;
        lightDir[0] = mapSize / 2 - (mapSize / 2 + Math.cos(sunPos) * mapSize * 10);
        lightDir[1] = mapSize / 2 - (mapSize / 2 + Math.sin(sunPos) * mapSize * 10);
        lightDir[2] = -200;
    }
    
    public double[] getLightDir(){return lightDir;}
    
}
