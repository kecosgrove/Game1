package Tron;

import javalib.worldimages.WorldImage;

/**
 * Created by User on 10/6/2014.
 */
public class EmptySquare implements PlayingSquare {

    public boolean isFilled() {
        return false;
    }

    public WorldImage getImage() {
        return null;
    }

}
