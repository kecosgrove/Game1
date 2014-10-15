package Tron;

import javalib.funworld.World;
import java.util.Random;

/**
 * Created by User on 10/12/2014.
 */
public class TronWorldWrapper {

    public static void main(String[] args) {
        PlayingSquare[][] initBoard = new PlayingSquare[TronWorld.blockRows][TronWorld.blockRows];
        for (int i = 0; i < initBoard.length; i++) {
            for (int j = 0; j < initBoard[0].length; j++) {
                initBoard[i][j] = new EmptySquare();
            }
        }
        World firstWorld = new TronWorld(TronWorld.startX,
                TronWorld.startY, initBoard, 0, 0, new Random(System.currentTimeMillis())).onTick();
        firstWorld.bigBang(TronWorld.screenSide, TronWorld.screenSide, 1/TronWorld.tickRate);
    }

}
