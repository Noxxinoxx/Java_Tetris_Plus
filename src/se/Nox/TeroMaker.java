package se.liu.noaan869.LabbTetris;

import java.util.Random;

public class TeroMaker {

    private final Random random = new Random();

    private int getNumberOfTypes() {
        return SquareType.values().length ;
    }

    private SquareType[][] squares = null;
    private int lenOfPoly;

    public Poly getPoly(int n) {
        return switch (n) {
            case 0 -> polyI();
            case 1 -> polyJ();
            case 2 -> polyL();
            case 3 -> polyO();
            case 4 -> polyS();
            case 5 -> polyT();
            case 6 -> polyZ();
            default -> throw new IllegalArgumentException("Invalid index: " + n);
        };

    }

    public Poly getRandomPoly(){
        
        return getPoly(random.nextInt(getNumberOfTypes()-2));
    }


    private Poly polyI() {
        lenOfPoly = 4;
        squares = new SquareType[4][4];
        for (int i = 0; i < lenOfPoly; i++) {
            for (int j = 0; j < lenOfPoly; j++) {
                squares[i][j] = SquareType.EMPTY;
                squares[1][j] = SquareType.I;
            }
        }
        return new Poly(squares);
    }


    private Poly polyJ() {
        squares = new SquareType[3][3];
        lenOfPoly = 3;
        for (int i = 0; i < lenOfPoly; i++) {
            for (int j = 0; j < lenOfPoly; j++) squares[i][j] = SquareType.EMPTY;
        }
        squares[0][1] = SquareType.J;
        squares[1][1] = SquareType.J;
        squares[2][1] = SquareType.J;
        squares[2][0] = SquareType.J;
        return new Poly(squares);
    }


    private Poly polyL() {
        squares = new SquareType[3][3];
        lenOfPoly = 3;
        for (int i = 0; i < lenOfPoly; i++) {
            for (int j = 0; j < lenOfPoly; j++) {
                squares[i][j] = SquareType.EMPTY;
            }
        }
        squares[0][1] = SquareType.L;
        squares[1][1] = SquareType.L;
        squares[2][1] = SquareType.L;
        squares[2][2] = SquareType.L;
        return new Poly(squares);
    }


    private Poly polyO() {
        squares = new SquareType[2][2];
        lenOfPoly = 2;
        for (int i = 0; i < lenOfPoly; i++) {
            for (int j = 0; j < lenOfPoly; j++) {
                squares[i][j] = SquareType.O;
            }
        }
        return new Poly(squares);
    }


    private Poly polyS() {
        squares = new SquareType[3][3];
        lenOfPoly = 3;
        for (int i = 0; i < lenOfPoly; i++) {
            for (int j = 0; j < lenOfPoly; j++) {
                squares[i][j] = SquareType.EMPTY;
            }
        }
        squares[0][1] = SquareType.S;
        squares[0][2] = SquareType.S;
        squares[1][0] = SquareType.S;
        squares[1][1] = SquareType.S;
        return new Poly(squares);
    }


    private Poly polyT() {
        squares = new SquareType[3][3];
        lenOfPoly = 3;
        for (int i = 0; i < lenOfPoly; i++) {
            for (int j = 0; j < lenOfPoly; j++) {
                squares[i][j] = SquareType.EMPTY;
                squares[1][j] = SquareType.T;
                squares[0][1] = SquareType.T;

            }
        }
        return new Poly(squares);
    }


    private Poly polyZ() {
        squares = new SquareType[3][3];
        lenOfPoly = 3;
        for (int i = 0; i < lenOfPoly; i++) {
            for (int j = 0; j < lenOfPoly; j++) {
                squares[i][j] = SquareType.EMPTY;

            }
        }
        squares[0][0] = SquareType.Z;
        squares[0][1] = SquareType.Z;
        squares[1][1] = SquareType.Z;
        squares[1][2] = SquareType.Z;
        return new Poly(squares);
    }
}
