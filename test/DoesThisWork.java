package test;

import javalib.colors.Blue;
import javalib.funworld.World;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

/**
 * Created by User on 9/25/2014.
*/
public class DoesThisWork extends World {

    public World onTick() {
        return this;
    }

    public World onKeyEvent(String ke) {
        return this;
    }

    public World onMouseClicked(Posn p) {
        return this;
    }

    public WorldImage makeImage() {
        return null;
    }

}
