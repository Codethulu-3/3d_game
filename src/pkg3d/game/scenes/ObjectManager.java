package pkg3d.game.scenes;

import java.util.ArrayList;
import pkg3d.engine.objects.DPolygon;

/**
 *
 * @author Alex
 */
public class ObjectManager {
    
    private ArrayList<DPolygon> objects = new ArrayList<>();
    
    public void add(DPolygon dpoly){
        objects.add(dpoly);
    }
    
    
}
