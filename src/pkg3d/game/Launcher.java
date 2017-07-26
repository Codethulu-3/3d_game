package pkg3d.game;

/**
 *
 * @author Alex
 */
public class Launcher {
    
    public static void main(String[] args){
        Game game = new Game();
        game.run(1.0 / 120.0);
    }
    
}
