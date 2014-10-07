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

    private static final int screenSide = 620;
    private static final int blockRows = 30;
    private static final int blockSide = 19;
    private static final Color blockColor = new Color(0, 0, 0);
    private final Color boardColor = new Color(51, 102, 255);
    private final WorldImage image = new RectangleImage(new Posn(0, 0),
                                                            screenSide*2, screenSide*2, boardColor);

    Border[] borders = new Border[4];
    PlayingSquare[][] board = new PlayingSquare[blockRows][blockRows];
    int activeX;
    int activeY;
    int rotation = 0;

    public TronWorld(int activeX, int activeY, PlayingSquare[][] board) {
        super();
        this.activeX = activeX;
        this.activeY = activeY;
        this.board = board;
        for (int i = 0; i < borders.length; i++) {
            borders[0] = new Border(new Posn(screenSide / 2, 0), blockColor, screenSide, 20);
            borders[1] = new Border(new Posn(screenSide / 2, screenSide), blockColor, screenSide, 20);
            borders[2] = new Border(new Posn(0, screenSide / 2), blockColor, 20, screenSide);
            borders[3] = new Border(new Posn(screenSide, screenSide / 2), blockColor, 20, screenSide);
        }
    }

    public World onTick() {
        PlayingSquare[][] nextBoard = new PlayingSquare[blockRows][blockRows];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                nextBoard[i][j] = board[i][j];
            }
        }
        //Next Step: do hit detection, modify nextBoard and select inputs for activeX activeY to
        //represent this modification
        return new TronWorld(activeX, activeY, nextBoard);
    }

    public World onKeyEvent(String ke) {
        return this;
    }

    public World onMouseClicked(Posn p) {return this;}

    public WorldImage makeImage() {
        WorldImage image = this.image;
        for (int i = 0; i < borders.length; i++) {
            image = new OverlayImages(image, borders[i].getImage());
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].isFilled()) image = new OverlayImages(image, board[i][j].getImage());
            }
        }
        return image;
    }

    private static Posn posFromArray(int x, int y) {
        return new Posn(20*(x+1), 20*(y+1));
    }

    public static void main(String[] args) {
        PlayingSquare[][] initBoard = new PlayingSquare[blockRows][blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        initBoard[0][0] = new Block(posFromArray(0, 0), blockColor, blockSide);
        TronWorld firstWorld = new TronWorld(0, 0, initBoard);
        firstWorld.bigBang(screenSide, screenSide, 1);
    }

}
