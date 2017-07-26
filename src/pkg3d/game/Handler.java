package pkg3d.game;

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
}