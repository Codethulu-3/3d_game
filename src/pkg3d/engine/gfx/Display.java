package pkg3d.engine.gfx;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Alex
 */
public class Display {
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width, height;
    
    /**
     * Creates a new display with a specific width and height
     * @param title The title of the window
     * @param width The width of the window in pixels
     * @param height  The height of the window in pixels
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }
    
    /**
     * Creates a Display 
     */
    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        //frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }
    
    public Canvas getCanvas() {return canvas;}
    public JFrame getFrame() {return frame;}
}
