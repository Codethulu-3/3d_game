package pkg3d.game.states;

import pkg3d.engine.State;
import java.awt.Graphics;
import pkg3d.game.Handler;
import pkg3d.game.scenes.Scene;

/**
 *
 * @author Alex
 */
public class GameState extends State{
    
    private Handler handler;
    private Scene scene;
    
    public GameState(Handler handler){
        this.handler = handler;
        scene = new Scene(handler);
    }
    
    @Override
    public void update() {
        scene.update();
    }

    @Override
    public void render(Graphics g) {
    }
    
    
}
