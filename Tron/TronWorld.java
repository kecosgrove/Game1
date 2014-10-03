package Tron;

import javalib.funworld.World;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import javalib.worldimages.RectangleImage;

import java.awt.*;

/**
 * Created by User on 9/25/2014.
*/
public class TronWorld extends World {

    static final int screenWidth = 600;
    static final int screenHeight = 600;
    public WorldImage image;
    WorldObject[] borders;
    WorldObject block;

    public TronWorld(WorldObject block, WorldObject[] borders) {
        super();
        image = new RectangleImage(new Posn(0, 0), screenWidth*2, screenHeight*2, new Color(51, 102, 255));
        this.block = block;
        this.borders = borders;
    }

    public World onTick() {
        WorldObject[] newBorders = new Border[4];
        for (int i = 0; i < newBorders.length; i++) {
            newBorders[i] = borders[i].onTick();
        }
        return new TronWorld(block.onTick(), newBorders);
    }

    public World onKeyEvent(String ke) {
        return this;
    }

    public World onMouseClicked(Posn p) {return this;
    }

    public WorldImage makeImage() {
        WorldImage image = this.image;
        for (int i = 0; i < borders.length; i++) {
            image = new OverlayImages(image, borders[i].getImage());
        }
        return new OverlayImages(image, block.getImage());
    }

    public static void main(String[] args) {
        Border[] borders = new Border[4];
        Color borderColor = new Color(255, 80, 80);
        borders[0] = new Border(new Posn(screenWidth/2, 0), borderColor, screenWidth, 20);
        borders[1] = new Border(new Posn(screenWidth/2, screenHeight), borderColor, screenWidth, 20);
        borders[2] = new Border(new Posn(0, screenHeight/2), borderColor, 20, screenHeight);
        borders[3] = new Border(new Posn(screenWidth, screenHeight/2), borderColor, 20, screenHeight);
        TronWorld firstWorld = new TronWorld(new Block(new Posn(screenWidth/2, 20), new Color(0, 0, 0), 20), borders);
        firstWorld.bigBang(screenWidth, screenHeight);
    }

}
