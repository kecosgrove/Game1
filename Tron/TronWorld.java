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
    private static final int rowSize = 20;
    private static final int blockSide = 18;
    private static final int borderWidth = 10;
    private static final Color blockColor = new Color(0, 0, 0);
    private final Color boardColor = new Color(51, 102, 255);
    private final WorldImage image = new RectangleImage(new Posn(0, 0),
                                                            screenSide*2, screenSide*2, boardColor);

    Border[] borders = new Border[4];
    PlayingSquare[][] board = new PlayingSquare[blockRows][blockRows];
    int activeX;
    int activeY;
    int rotation;

    public TronWorld(int activeX, int activeY, PlayingSquare[][] board, int rotation) {
        super();
        this.activeX = activeX;
        this.activeY = activeY;
        this.board = board;
        this.rotation = rotation;
        for (int i = 0; i < borders.length; i++) {
            borders[0] = new Border(new Posn(screenSide / 2, 0), blockColor, screenSide, borderWidth*2);
            borders[1] = new Border(new Posn(screenSide / 2, screenSide), blockColor, screenSide, borderWidth*2);
            borders[2] = new Border(new Posn(0, screenSide / 2), blockColor, borderWidth*2, screenSide);
            borders[3] = new Border(new Posn(screenSide, screenSide / 2), blockColor, borderWidth*2, screenSide);
        }
        this.board[activeX][activeY] = new Block(posFromArray(activeX, activeY), blockColor, blockSide);
    }

    public World onTick() {
        System.out.println("hi");
        int nextX = activeX;
        int nextY = activeY;
        switch (rotation) {
            case 0: nextY++; break;
            case 1: nextX++; break;
            case 2: nextY--; break;
            default: nextX--; break;
        }
        if(board[nextX][nextY].isFilled() || !inbounds(nextX, nextY)) return this.endOfWorld("Collision!");
        PlayingSquare[][] nextBoard = new PlayingSquare[blockRows][blockRows];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                nextBoard[i][j] = board[i][j];
            }
        }
        return new TronWorld(nextX, nextY, nextBoard, rotation);
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

    private Posn posFromArray(int x, int y) {
        int offset = borderWidth + rowSize/2;
        switch (rotation) {
            case 0: return new Posn(x*rowSize + offset, y*rowSize + offset);
            case 1: return new Posn(screenSide - (y*rowSize) - offset, x*rowSize + offset );
            case 2: return new Posn(screenSide - (x*rowSize) - offset, screenSide - (y*rowSize) - offset);
            default: return new Posn(y*rowSize + offset, screenSide - (x*rowSize) - offset);
        }
    }

    private boolean inbounds(int x, int y) {
        return x > -1 && x < blockRows && y > -1 && y < blockRows;
    }

    public static void main(String[] args) {
        PlayingSquare[][] initBoard = new PlayingSquare[blockRows][blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        TronWorld firstWorld = new TronWorld(0, 0, initBoard, 0);
        firstWorld.bigBang(screenSide, screenSide, 1);
    }

}
