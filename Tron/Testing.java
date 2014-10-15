package Tron;

import javalib.funworld.World;

import java.util.Random;

/**
 * Created by User on 10/12/2014.
 */
public class Testing {



    //From the default starting position, the snake should collide with the wall in blockRows ticks
    public static void ticksToWall(int repeat) {
        PlayingSquare[][] initBoard = new PlayingSquare[TronWorld.blockRows][TronWorld.blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        for (int r = 0; r < repeat; r++) {
            int counter = 0;
            World theWorld = new TronWorld(TronWorld.startX,
                    TronWorld.startY, initBoard, 0, 0, new Random(System.currentTimeMillis())).onTick();
            while (!theWorld.lastWorld.worldEnds) {
                counter++;
                theWorld = theWorld.onTick();
            }
            System.out.println(counter + " should be " + (TronWorld.blockRows));
        }
    }

    public static void readBlocks(int repeat) {

        PlayingSquare[][] initBoard = new PlayingSquare[TronWorld.blockRows][TronWorld.blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        for (int r = 0; r < repeat; r++) {
            int counter = 0;
            World theWorld = new TronWorld(TronWorld.startX,
                    TronWorld.startY, initBoard, 0, 0, new Random(System.currentTimeMillis())).onTick();
            while (!theWorld.lastWorld.worldEnds) {
                theWorld = theWorld.onTick();
            }
            TronWorld theTronWorld = (TronWorld) theWorld;
            PlayingSquare[][] board = theTronWorld.board;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j].collides()) {
                        counter++;
                        System.out.print("(" + i + "," + j + ")");
                    }
                }
            }
            System.out.println("");
            System.out.println(counter + " should be " + TronWorld.blockRows);
        }
    }

    public static void fullTurnTest(int repeat) {
        boolean holds = true;
        PlayingSquare[][] initBoard = new PlayingSquare[TronWorld.blockRows][TronWorld.blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        for (int r = 0; r < repeat; r++) {
            World theWorld = new TronWorld(TronWorld.startX,
                    TronWorld.startY, initBoard, 0, 0, new Random(System.currentTimeMillis())).onTick();
            TronWorld theTronWorld = (TronWorld) theWorld;
            PlayingSquare[][] firstBoard = theTronWorld.board;
            theWorld = theWorld.onKeyEvent("right");
            theWorld = theWorld.onKeyEvent("right");
            theWorld = theWorld.onKeyEvent("right");
            theWorld = theWorld.onKeyEvent("right");
            TronWorld nextTronWorld = (TronWorld) theWorld;
            PlayingSquare[][] lastBoard = nextTronWorld.board;
            for (int i = 0; i < firstBoard.length; i++) {
                for (int j = 0; j < firstBoard[0].length; j++) {
                    if (!(firstBoard[i][j].collides() == lastBoard[i][j].collides() &&
                        firstBoard[i][j].hasImage() == lastBoard[i][j].hasImage())) {
                        holds = false;
                        break;
                    }

                }
            }
            if (holds) {
                System.out.println("PASS: The board is identical before and after rotation.");
            } else {
                System.out.println("FAIL: There are inconsistencies between the board before and after rotation!");
            }
        }
    }

    public static void ticksToWall2(int repeat) {
        PlayingSquare[][] initBoard = new PlayingSquare[TronWorld.blockRows][TronWorld.blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        for (int r = 0; r < repeat; r++) {
            int counter = 0;
            World theWorld = new TronWorld(TronWorld.startX,
                    TronWorld.startY, initBoard, 0, 0, new Random(System.currentTimeMillis())).onTick().onKeyEvent("left");
            while (!theWorld.lastWorld.worldEnds) {
                counter++;
                theWorld = theWorld.onTick();
            }
            System.out.println(counter + " should be " + (TronWorld.startX + 1));
        }
    }

    public static void testBlockCollision(int repeat) {
        PlayingSquare[][] initBoard = new PlayingSquare[TronWorld.blockRows][TronWorld.blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        for (int r = 0; r < repeat; r++) {
            World theWorld = new TronWorld(TronWorld.startX,
                    TronWorld.startY, initBoard, 0, 0, new Random(System.currentTimeMillis())).onTick();
            theWorld = theWorld.onTick();
            theWorld = theWorld.onKeyEvent("right");
            theWorld = theWorld.onKeyEvent("right");
            theWorld = theWorld.onTick();
            if (theWorld.lastWorld.worldEnds) {
                System.out.println("The game ended correctly.");
            } else {
                System.out.println("The game did not end correctly.");
            }
        }
    }

    public static void testScore(int repeat) {
        PlayingSquare[][] initBoard = new PlayingSquare[TronWorld.blockRows][TronWorld.blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        initBoard[TronWorld.startX][TronWorld.startY + 1] =
                new Collectable(TronWorld.posFromArray(TronWorld.startX, TronWorld.startY + 1), TronWorld.itemColor, TronWorld.itemSize);
        for (int r = 0; r < repeat; r++) {
            World theWorld = new TronWorld(TronWorld.startX,
                    TronWorld.startY, initBoard, 1, 0, new Random(System.currentTimeMillis())).onTick();
            TronWorld theTronWorld = (TronWorld) theWorld;
            System.out.println(theTronWorld.score + " should be 1");
        }
    }

    public static void main(String[] args) {
        ticksToWall(5);
        readBlocks(5);
        fullTurnTest(5);
        ticksToWall2(5);
        testBlockCollision(5);
        testScore(5);
    }

}
