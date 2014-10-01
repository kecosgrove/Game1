package Tron;

import javalib.funworld.World;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;
import javalib.worldimages.RectangleImage;
import javalib.colors.Black;

import java.awt.*;

/**
 * Created by User on 9/25/2014.
*/
public class TronWorld extends World {

    static final int screenWidth = 600;
    static final int screenHeight = 600;
    public WorldImage image;
    Block block;

    public TronWorld(Block block) {
        super();
        image = new RectangleImage(new Posn(0, 0), screenWidth*2, screenHeight*2, new Color(51, 102, 255));
        this.block = block;
    }

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
        return new OverlayImages(image, block.blockImage());
    }

    public static void main(String[] args) {
        TronWorld firstWorld = new TronWorld(new Block(new Posn(20, 20), new Color(0, 0, 0), 20));
        firstWorld.bigBang(screenWidth, screenHeight);
    }

}
