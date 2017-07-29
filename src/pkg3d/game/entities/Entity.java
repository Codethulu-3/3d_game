package pkg3d.game.entities;

import pkg3d.game.Handler;

/**
 *
 * @author Alex
 */
public abstract class Entity {
    
    private Handler handler;
    
    public Entity(Handler handler){
        this.handler = handler;
    }
    
    public abstract void update();
    
}
