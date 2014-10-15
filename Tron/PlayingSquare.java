package Tron;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 10/6/2014.
 */
public interface PlayingSquare {
    public boolean collides();
    //getImage is only defined if hasImage is true
    public boolean hasImage();
    public WorldImage getImage();
}
