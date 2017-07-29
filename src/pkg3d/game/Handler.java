package pkg3d.game;

import pkg3d.game.input.KeyManager;
import pkg3d.game.input.MouseManager;

/**
 *
 * @author Alex
 */
public class Handler {
    
    private Game game;
    
    public Handler(Game game){
        this.game = game;
    }
    
    public int getScreenWidth(){return game.getScreenWidth();}
    public int getScreenHeight(){return game.getScreenHeight();}
    
    public KeyManager getKeyManager(){return game.getKeyManager();}
    public MouseManager getMouseManager(){return game.getMouseManager();}
}
