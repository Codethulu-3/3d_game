package pkg3d.main.states;

import java.awt.Graphics;
import pkg3d.main.Handler;

/**
 *
 * @author Alex
 */
public abstract class State {
    protected Handler handler;
    
    public State(Handler handler) {
        this.handler = handler;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    
    private static State currentState = null;
    
    public static void setState(State state) {currentState = state;}
    public static State getState() {return currentState;}
}
