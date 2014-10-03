package Tron;

import javalib.worldimages.WorldImage;


/**
 * Created by User on 10/2/2014.
 */
public interface WorldObject {
    public WorldImage getImage();
    public WorldObject onTick();
}
