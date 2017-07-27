package pkg3d.game.entities;

import pkg3d.engine.gfx.Camera;

/**
 *
 * @author Alex
 */
public class Player {
    
    private Camera camera;
    //coordinates
    private double[] viewFrom = new double[]{0, 0, 60},//starting coordinates 
            viewTo = new double[]{0, 0, 0};
    
    public Player(){
        camera = new Camera(1.5, -0.9, 0, 900, 2200);
    }
    
    public void moveTo(double x, double y, double z) {
        viewFrom[0] = x;
        viewFrom[1] = y;
        viewFrom[2] = z;
        updateView();
    }
    
    private void updateView() {
        double r = Math.sqrt(1 - (camera.getVertLook() * camera.getVertLook()));
        viewTo[0] = viewFrom[0] + r * Math.cos(camera.getHorLook());
        viewTo[1] = viewFrom[1] + r * Math.sin(camera.getHorLook());
        viewTo[2] = viewFrom[2] + camera.getVertLook();
    }
}
