package pkg3d.game;

import java.awt.Color;
import pkg3d.engine.GameLoop;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import pkg3d.engine.gfx.Display;
import pkg3d.game.states.GameState;
import pkg3d.engine.State;
import pkg3d.game.input.KeyManager;
import pkg3d.game.input.MouseManager;

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
    
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
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
        
        //input
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
        
        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
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
        
        g.clipRect(0, 0, width, height);
        g.setColor(new Color(140, 180, 180));
        g.fillRect(0, 0, width, height);
        State.getState().render(g);
        
	bs.show();
	g.dispose();
    }
    
    //getter
    public int getScreenWidth(){return width;}
    public int getScreenHeight(){return height;}
    
    public KeyManager getKeyManager(){return keyManager;}
    public MouseManager getMouseManager(){return mouseManager;}
}
