package pkg3d.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Alex
 */
public class KeyManager implements KeyListener{
    
    private boolean[] oldKeys, curKeys;
    public boolean escapePressed=false;
    
    public KeyManager(){
        oldKeys = new boolean[256];
        curKeys = new boolean[256];
    }
    
    public void update(){
        oldKeys = curKeys.clone();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        curKeys[e.getKeyCode()] = true;
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            escapePressed=!escapePressed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        curKeys[e.getKeyCode()] = false;
    }
    
    public boolean getWPressed(){return curKeys[KeyEvent.VK_W];}
    public boolean getWTapped() {
        return curKeys[KeyEvent.VK_W] && curKeys[KeyEvent.VK_W] != oldKeys[KeyEvent.VK_W];
    }
    
    public boolean getAPressed(){return curKeys[KeyEvent.VK_A];}
    public boolean getATapped() {
        return curKeys[KeyEvent.VK_A] && curKeys[KeyEvent.VK_A] != oldKeys[KeyEvent.VK_A];
    }
    
    public boolean getSPressed(){return curKeys[KeyEvent.VK_S];}
    public boolean getSTapped() {
        return curKeys[KeyEvent.VK_S] && curKeys[KeyEvent.VK_S] != oldKeys[KeyEvent.VK_S];
    }
    
    public boolean getDPressed(){return curKeys[KeyEvent.VK_D];}
    public boolean getDTapped() {
        return curKeys[KeyEvent.VK_D] && curKeys[KeyEvent.VK_D] != oldKeys[KeyEvent.VK_D];
    }
    
    public boolean getSpacePressed(){return curKeys[KeyEvent.VK_SPACE];}
    public boolean getSpaceTapped() {
        return curKeys[KeyEvent.VK_SPACE] && curKeys[KeyEvent.VK_SPACE] != oldKeys[KeyEvent.VK_SPACE];
    }
}
