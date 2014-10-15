package Tron;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import javalib.worldimages.RectangleImage;

import java.awt.*;

/**
 * Created by User on 9/30/2014.
 */
public class Block implements PlayingSquare {

    Posn position;
    Color color;
    int side;

    public Block(Posn position, Color color, int side) {
        this.position = position;
        this.color = color;
        this.side = side;
    }

    public boolean collides() {
        return true;
    }

    public boolean hasImage() { return true; }

    public WorldImage getImage() {
        return new RectangleImage(position, side, side, color);
    }

}
