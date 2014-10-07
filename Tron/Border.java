package Tron;

import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import javalib.worldimages.RectangleImage;

import java.awt.*;

/**
 * Created by User on 10/2/2014.
 */
public class Border {

    private Posn position;
    private Color color;
    private int vertSide;
    private int horiSide;

    public Border(Posn position, Color color, int vertSide, int horiSide) {
        this.position = position;
        this.color = color;
        this.vertSide = vertSide;
        this.horiSide = horiSide;
    }

    public WorldImage getImage() {
        return new RectangleImage(position, vertSide, horiSide, color);
    }

    public Border setPos(Posn newPos) {
        Border newBorder = this;
        newBorder.position = newPos;
        return newBorder;
    }

    public Posn getPos() {
        Posn position = this.position;
        return position;
    }

}
