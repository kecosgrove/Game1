package Tron;

        import javalib.worldimages.WorldImage;

/**
 * Created by User on 10/6/2014.
 */
public class EmptySquare implements PlayingSquare {

    public boolean collides() {
        return false;
    }

    public boolean hasImage() {
        return false;
    }

    public WorldImage getImage() {
        throw new RuntimeException("No image in that square");
    }

}
