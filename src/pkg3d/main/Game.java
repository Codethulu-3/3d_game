package pkg3d.main;

import pkg3d.engine.GameLoop;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import pkg3d.engine.gfx.Display;
import pkg3d.main.states.GameState;
import pkg3d.engine.states.State;

/**
 *
 * @author Alex
 */
public class Game extends GameLoop{
    
    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private int width, height;
    
    private Handler handler;
    
    
    
    private State gameState;
    
    @Override
    public void startup() {
        width = 1080;
        height = 720;
        display = new Display("3d", width, height);
        
        //set up closing operation
        display.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });
        
        handler = new Handler(this);
        
        //states
        gameState = new GameState(handler);
        
        State.setState(gameState);
    }
    
    @Override
    public void shutdown() {
        System.exit(0);
    }

    @Override
    public void update() {
        State.getState().update();
    }

    @Override
    public void render() {
        bs = display.getCanvas().getBufferStrategy();
	if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
	}
	g = bs.getDrawGraphics();
	//Clear Screen
	g.clearRect(0, 0, width, height);
        
        State.getState().render(g);
        
	bs.show();
	g.dispose();
    }
    
    //getter
    public int getScreenWidth(){return width;}
    public int getScreenHeight(){return height;}
}
