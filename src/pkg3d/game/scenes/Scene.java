package pkg3d.game.scenes;

import java.awt.Color;
import java.awt.Graphics;
import pkg3d.engine.math.Calculator;
import pkg3d.engine.objects.DPolygon;
import pkg3d.game.Handler;
import pkg3d.game.entities.Player;

/**
 *
 * @author Alex
 */
public class Scene {
    private Color G = new Color(120, 0, 0);
    private Color G2 = new Color(0,0,120);
    private Color G3 = new Color(0,120,200);
    private Color G4 = new Color(200, 120, 0);
    
    private Calculator calculator;
    //private Utils utils;
    private ObjectManager objectManager;
    private Player player;
    
    public Scene(Handler handler){

        calculator = new Calculator();
        //utils = new Utils(handler);
        player = new Player(handler);
        objectManager = new ObjectManager();
        //utils.setInvisibleMouse(handler.getDisplay().getFrame());
        
        //temp world
        
    }
    
    public void update(){
        player.update();
        calculator.update(player.getViewFrom(), player.getViewTo(), player.getZoom());
        //handler.getShapeManager().update(calculator, handler.getCamera());
    }
    
    public void render(Graphics g){
        //handler.getShapeManager().render(g);
        //utils.drawMouseAim(g, 5);
    }
    
    public Calculator getCalculator(){
        return calculator;
    }
}
