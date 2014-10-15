package Tron;

import javalib.funworld.World;
import javalib.worldimages.*;

import java.awt.*;
import java.util.Random;

/**
 * Created by User on 9/25/2014.
*/
public class TronWorld extends World {

    protected static final int startX = 15;
    protected static final int startY = 0;
    protected static final int screenSide = 620;
    protected static final int blockRows = 30;
    protected static final float tickRate = 5;

    protected static final int rowSize = 20;
    protected static final int blockSide = 18;
    protected static final int borderWidth = 10;
    protected static final int itemSize = 16;
    protected static final int scoreSize = 200;
    protected static final Color blockColor = new Color(0, 0, 0);
    protected static final Color itemColor = new Color(255, 102, 51);
    protected static final Color boardColor = new Color(51, 102, 255);
    protected static final Color scoreColor = new Color(0, 30, 80);
    protected static final WorldImage image = new RectangleImage(new Posn(0, 0),
                                                               screenSide*2, screenSide*2, boardColor);
    protected static final int initNumItems = 10;

    Border[] borders = new Border[4];
    PlayingSquare[][] board = new PlayingSquare[blockRows][blockRows];
    int activeX;
    int activeY;
    int numItems;
    int score;
    Random random;

    public TronWorld(int activeX, int activeY, PlayingSquare[][] board, int numItems, int score, Random random) {
        super();
        this.activeX = activeX;
        this.activeY = activeY;
        this.board = board;
        this.numItems = numItems;
        this.score = score;
        this.random = random;
        for (int i = 0; i < borders.length; i++) {
            borders[0] = new Border(new Posn(screenSide / 2, 0), blockColor, screenSide, borderWidth*2);
            borders[1] = new Border(new Posn(screenSide / 2, screenSide), blockColor, screenSide, borderWidth*2);
            borders[2] = new Border(new Posn(0, screenSide / 2), blockColor, borderWidth*2, screenSide);
            borders[3] = new Border(new Posn(screenSide, screenSide / 2), blockColor, borderWidth*2, screenSide);
        }
        if (!this.board[activeX][activeY].collides())
            this.board[activeX][activeY] = new Block(posFromArray(activeX, activeY), blockColor, blockSide);
    }

    public World onTick() {
        int nextX = activeX;
        int nextY = activeY + 1;
        int nextNumItems = numItems;
        int nextScore = score;
        if ((nextY >= blockRows) || board[nextX][nextY].collides()) return this.endOfWorld("Collision!");
        if (board[nextX][nextY].hasImage()) {
            nextNumItems--;
            nextScore++;
        }
        PlayingSquare[][] nextBoard = new PlayingSquare[blockRows][blockRows];
        if (nextNumItems == 0) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    nextBoard[i][j] = new EmptySquare();
                }
            }
            for (int i = 0; i < initNumItems; i++) {
                int randX = random.nextInt(blockRows);
                int randY = random.nextInt(blockRows);
                if (randX == startX && randY == startY && !nextBoard[randX][randY].hasImage()) {
                    i--;
                } else {
                    nextBoard[randX][randY] = new Collectable(posFromArray(randX, randY), itemColor, itemSize);
                }
            }
            return new TronWorld(startX, startY, nextBoard, initNumItems, nextScore, random);
        } else {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    nextBoard[i][j] = board[i][j];
                }
            }
            return new TronWorld(nextX, nextY, nextBoard, nextNumItems, nextScore, random);
        }
    }

    public World onKeyEvent(String ke) {
        if (ke.equals("right")) {
            return new TronWorld(blockRows - activeY - 1, activeX, rotateBoard(board, true), numItems, score, random);
        } else if (ke.equals("left")) {
            return new TronWorld(activeY, blockRows - activeX - 1, rotateBoard(board, false), numItems, score, random);
        } else {
            return this;
        }
    }

    public World onMouseClicked(Posn p) {return this;}

    public WorldImage makeImage() {
        WorldImage image = this.image;
        for (int i = 0; i < borders.length; i++) {
            image = new OverlayImages(image, borders[i].getImage());
        }
        image = new OverlayImages(image, scoreImage());
        for (int i = 0; i < board.length; i++) {
             for (int j = 0; j < board[0].length; j++) {
                  if (board[i][j].hasImage()) {
                      image = new OverlayImages(image, board[i][j].getImage());
                  }
    }
}
        return image;
    }

    //Takes the 2d array index of an object in the board array and returns the position it should occupy in the window.
    protected static Posn posFromArray(int x, int y) {
        int offset = borderWidth + rowSize/2;
        return new Posn(x*rowSize + offset, y*rowSize + offset);
    }

    //Takes a board and a direction (either right or not right) and returns a new board rotated in the given direction.
    protected static PlayingSquare[][] rotateBoard(PlayingSquare[][] board, boolean right) {
        PlayingSquare[][] newBoard = new PlayingSquare[blockRows][blockRows];
        if (right) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[j][blockRows-1-i].collides()) {
                        newBoard[i][j] = new Block(posFromArray(i, j), blockColor, blockSide);
                    } else if (board[j][blockRows-1-i].hasImage()){
                        newBoard[i][j] = new Collectable(posFromArray(i, j), itemColor, itemSize);
                    } else {
                        newBoard[i][j] = new EmptySquare();
                    }
                }
            }
        } else {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[blockRows-1-j][i].collides()) {
                        newBoard[i][j] = new Block(posFromArray(i, j), blockColor, blockSide);
                    } else if (board[blockRows-1-j][i].hasImage()) {
                        newBoard[i][j] = new Collectable(posFromArray(i, j), itemColor, itemSize);
                    } else {
                        newBoard[i][j] = new EmptySquare();
                    }
                }
            }
        }
        return newBoard;
    }

    //Generates a text image of the score from the current score.
    protected WorldImage scoreImage() {
        Integer scoreCopy = score;
        Posn center = new Posn(screenSide/2, screenSide/2);
        return new TextImage(center, scoreCopy.toString(), scoreSize, scoreColor);
    }
}
