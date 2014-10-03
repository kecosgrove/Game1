package Tron;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import javalib.worldimages.RectangleImage;

import java.awt.*;

/**
 * Created by User on 10/2/2014.
 */
public class Border implements WorldObject {

    Posn position;
    Color color;
    int vertSide;
    int horiSide;

    public Border(Posn position, Color color, int vertSide, int horiSide) {
        this.position = position;
        this.color = color;
        this.vertSide = vertSide;
        this.horiSide = horiSide;
    }

    public WorldImage getImage() {
        return new RectangleImage(position, vertSide, horiSide, color);
    }

    public Border onTick() {
        Border newBorder = this;
        return newBorder;
    }

}
