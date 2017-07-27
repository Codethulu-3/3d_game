package pkg3d.engine.gfx;

import pkg3d.engine.math.Vector;

/**
 *
 * @author Alex
 */
public class Camera {
    
    //zoom
    private double zoom = 1000;
    private double minZoom=500, maxZoom=1500, startZoom=1000;
    private double zoomSpeed=20.5;
    

    
    //viewing
    public double movementSpeed = 001.5;
    private double vertLook = -0.9, horLook = 0;
    private double horRotSpeed = 900, vertRotSpeed = 2200;
    
    public Camera(double movementSpeed, double vertLook, double horLook, double horRotSpeed, double vertRotSpeed){
        this.movementSpeed = movementSpeed;
        this.vertLook = vertLook;
        this.horLook = horLook;
        this.horRotSpeed = horRotSpeed;
        this.vertRotSpeed = vertRotSpeed;
    }
    
    private void calculateZoom(double zoom){
        double tempZoom = zoom * zoomSpeed;
        if(tempZoom + startZoom > maxZoom){
            zoom = maxZoom;
        } else if(tempZoom + startZoom < minZoom){
            zoom=minZoom;
        } else {
            zoom = tempZoom + startZoom;
        }
    }
    
    public void mouseMovement(double NewMouseX, double NewMouseY, int width, int height) {
        double difX = (NewMouseX - width / 2);
        double difY = (NewMouseY - height / 2);
        difY *= 6 - Math.abs(vertLook) * 5;
        vertLook -= difY / vertRotSpeed;
        horLook += difX / horRotSpeed;

        if (vertLook > 0.999) {
            vertLook = 0.999;
        }

        if (vertLook < -0.999) {
            vertLook = -0.999;
        }
    }
    
    public Vector moveForward(double[] viewFrom, double[] viewTo){
        Vector viewVector = new Vector(viewTo[0] - viewFrom[0], viewTo[1] - viewFrom[1], viewTo[2] - viewFrom[2]);
        double xMove = 0, yMove = 0, zMove = 0;

        xMove += viewVector.getX();
        yMove += viewVector.getY();

        Vector moveVector = new Vector(xMove, yMove, zMove);
        return moveVector;
    }
    
    public Vector moveBackwards(double[] viewFrom, double[] viewTo){
        Vector viewVector = new Vector(viewTo[0] - viewFrom[0], viewTo[1] - viewFrom[1], viewTo[2] - viewFrom[2]);
        double xMove = 0, yMove = 0, zMove = 0;

        xMove -= viewVector.getX();
        yMove -= viewVector.getY();

        Vector moveVector = new Vector(xMove, yMove, zMove);
        return moveVector;
    }
    
    public Vector moveRight(double[] viewFrom, double[] viewTo){
        Vector viewVector = new Vector(viewTo[0] - viewFrom[0], viewTo[1] - viewFrom[1], viewTo[2] - viewFrom[2]);
        double xMove = 0, yMove = 0, zMove = 0;
        Vector verticalVector = new Vector(0, 0, 1);
        Vector sideViewVector = viewVector.CrossProduct(verticalVector);
        
        xMove -= sideViewVector.getX();
        yMove -= sideViewVector.getY();

        Vector moveVector = new Vector(xMove, yMove, zMove);
        return moveVector;
    }
    
    public Vector moveLeft(double[] viewFrom, double[] viewTo){
        Vector viewVector = new Vector(viewTo[0] - viewFrom[0], viewTo[1] - viewFrom[1], viewTo[2] - viewFrom[2]);
        double xMove = 0, yMove = 0, zMove = 0;
        Vector verticalVector = new Vector(0, 0, 1);
        Vector sideViewVector = viewVector.CrossProduct(verticalVector);

        xMove += sideViewVector.getX();
        yMove += sideViewVector.getY();

        Vector moveVector = new Vector(xMove, yMove, zMove);
        return moveVector;
    }
    
    public double getVertLook(){return vertLook;}
    public double getHorLook(){return horLook;}
}
