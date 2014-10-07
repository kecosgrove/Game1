package Tron;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 10/6/2014.
 */
public interface PlayingSquare {
    public boolean isFilled();
    //getImage is only defined if isFilled is true
    public WorldImage getImage();
}
