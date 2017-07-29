package pkg3d.game.entities;

import pkg3d.engine.gfx.Camera;
import pkg3d.engine.math.Vector;
import pkg3d.game.Handler;

/**
 *
 * @author Alex
 */
public class Player extends Entity{
    
    private Handler handler;
    private Camera camera;
    
    //coordinates
    private double[] viewFrom = new double[]{0, 0, 60},//starting coordinates 
            viewTo = new double[]{0, 0, 0};
    private double xMove = 0, yMove = 0, zMove = 0;
    
    public Player(Handler handler){
        super(handler);
        this.handler = handler;
        camera = new Camera(1.5, -0.9, 0, 900, 2200);
    }
    
    public void update(){
        move();
    }
    
    private void move(){
        if(handler.getKeyManager().getWPressed()){
            xMove += camera.moveForward(viewFrom, viewTo)[0];
            yMove += camera.moveForward(viewFrom, viewTo)[1];
        }
        if(handler.getKeyManager().getSPressed()){
            xMove += camera.moveBackwards(viewFrom, viewTo)[0];
            yMove += camera.moveBackwards(viewFrom, viewTo)[1];
        }
        if(handler.getKeyManager().getAPressed()){
            xMove += camera.moveLeft(viewFrom, viewTo)[0];
            yMove += camera.moveLeft(viewFrom, viewTo)[1];
        }
        if(handler.getKeyManager().getDPressed()){
            xMove += camera.moveRight(viewFrom, viewTo)[0];
            yMove += camera.moveRight(viewFrom, viewTo)[1];
        }
        moveTo(xMove,yMove,zMove);
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
    
    public double[] getViewFrom(){return viewFrom;}
    public double[] getViewTo(){return viewTo;}
    
    public double getZoom(){return camera.getZoom();}
}
