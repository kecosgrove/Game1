package Tron;

import javalib.worldimages.CircleImage;
import javalib.worldimages.OvalImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

import java.awt.*;

/**
 * Created by User on 10/11/2014.
 */
public class Collectable implements PlayingSquare {

    Posn position;
    Color color;
    int radius;

    public Collectable(Posn position, Color color, int radius) {
        this.position = position;
        this.color = color;
        this.radius = radius;
    }

    public boolean collides() {
        return false;
    }

    public boolean hasImage() {
        return true;
    }

    public WorldImage getImage() {
        return new OvalImage(position, radius, radius, color);
    }
}
